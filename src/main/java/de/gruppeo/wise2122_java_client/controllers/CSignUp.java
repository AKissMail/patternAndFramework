package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Configuration;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.Validation;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.fxml.FXML;

public class CSignUp {
    ViewLoader loader;
    Validation validation;
    Configuration config;

    ArrayList<Boolean> list =  new ArrayList<Boolean>();
    private int requiredFields = 3;

    @FXML private TextField textField_signUp_username;
    @FXML private TextField textField_signUp_password1;
    @FXML private TextField textField_signUp_password2;
    @FXML private Label label_signUp_username;
    @FXML private Label label_signUp_password1;
    @FXML private Label label_signUp_password2;
    @FXML private BorderPane mainPane;
    @FXML private Button button_signUp_signUp;

    /**
     * Reserviert drei Speicherplätze
     * im Zwischenspeicher.
     */
    public CSignUp() throws Exception {
        loader = new ViewLoader();
        validation = new Validation();
        config = new Configuration();

        for (int i = 1; i <= requiredFields; i++) {
            list.add(false);
        }
    }

    /**
     * Klick auf "Registrieren"-Button
     * speichert Daten in Datenbank.
     */
    public void onMouseClicked_signUp() throws Exception {
        Connection signUp = new Connection("/auth/register");
        Connection logIn = new Connection("/auth/login");

        String username = textField_signUp_username.getText();
        String password = textField_signUp_password1.getText();

        try {
            // Registriert neuen Spieler
            signUp.postData("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }");

            // Sendet JSON-Anfrage mit Zugangsdaten an Server
            logIn.postData("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }");

            // Speichert Token in Config-Datei
            config.writeProperty("privateToken", logIn.getServerResponse());

            // Leitet zum Hauptmenü
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(loader.getScene("main"));
            stage.show();
        } catch (Exception e) {
            int responseCode = signUp.getConnection().getResponseCode();

            // @TODO Fehlermeldungen auf GUI anzeigen
            switch (responseCode) {
                case 400:
                    System.out.println("Benutzername bereits vergeben: " + responseCode);
                    break;
                case 401:
                    System.out.println("Fehlermeldung: " + responseCode);
                    break;
            }
        }
    }

    /**
     * Prüft, ob der Benutzername
     * den Richtlinien entspricht.
     */
    public void onKeyTyped_username() {
        String username = textField_signUp_username.getText();
        Color labelColor;

        if (validation.isValidUsername(username) && validation.isUsernameAvailable(new MPlayer())) {
            list.set(0, true);
            labelColor = Color.BLACK;
        } else {
            list.set(0, false);
            labelColor = Color.RED;
        }
        validation.checkInputValidation(label_signUp_username, labelColor, list, button_signUp_signUp);
    }

    /**
     * Prüft, ob das erste Passwort
     * den Richtlinien entspricht.
     */
    public void onKeyTyped_password1() {
        String password1 = textField_signUp_password1.getText();
        Color labelColor;

        if (validation.isValidPassword(password1)) {
            list.set(1, true);
            labelColor = Color.BLACK;
        } else {
            list.set(1, false);
            labelColor = Color.RED;
        }
        validation.checkInputValidation(label_signUp_password1, labelColor, list, button_signUp_signUp);
    }

    /**
     * Prüft, ob das erste und das
     * zweite Passwort übereinstimmen.
     */
    public void onKeyTyped_password2() {
        String password1 = textField_signUp_password1.getText();
        String password2 = textField_signUp_password2.getText();
        Color labelColor;

        if (password1.equals(password2)) {
            list.set(2, true);
            labelColor = Color.BLACK;
        } else {
            list.set(2, false);
            labelColor = Color.RED;
        }
        validation.checkInputValidation(label_signUp_password2, labelColor, list, button_signUp_signUp);
    }

    /**
     * Klick auf "Zurück"-Button
     * navigiert auf die logIn-Maske.
     */
    public void onMouseClicked_back() {
        Pane pane = loader.getPane("logIn");
        mainPane.setRight(pane);
    }
}
package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.helper.*;
import de.gruppeo.wise2122_java_client.model.MConfig;
import de.gruppeo.wise2122_java_server.security.JwtTokenProvider;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.fxml.FXML;

public class CSignUp {
    private final Loader loader;
    private final Validation validation;
    private final JwtTokenProvider tokenProvider;
    private final ArrayList<Boolean> list;

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
    public CSignUp() {
        loader = new Loader();
        validation = new Validation();
        list =  new ArrayList<>();
        tokenProvider = new JwtTokenProvider();

        for (int i = 1; i <= 3; i++) {
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
            signUp.signUpPlayer(username, password);

            // Meldet neuen Spieler an
            logIn.logInPlayer(username, password);

            // Speichert Token und Usernamen in Config-Objekt
            MConfig.getInstance().setPrivateToken(logIn.getServerResponse());
            MConfig.getInstance().setUsername(tokenProvider.getUsernameFromToken(logIn.getServerResponse(), MConfig.getInstance().getJwtSecret()));

            // Leitet zum Hauptmenü
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(loader.getScene("main"));
            stage.show();

        } catch (Exception e) {
            switch (signUp.getConnection().getResponseCode()) {
                case 400:
                    System.out.println("Response Code: 400");
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Der eingegebene Benutzername ist bereits vergeben. Bitte versuche es erneut.", ButtonType.OK);
                    alert.showAndWait();
                    textField_signUp_username.clear();
                    textField_signUp_username.requestFocus();
                    button_signUp_signUp.setDisable(true);
                    break;
                case 401:
                    System.out.println("Response Code: 401");
                    alert = new Alert(Alert.AlertType.WARNING, "Ein unerwarteter Fehler ist aufgetreten. Bitte versuche es später erneut.", ButtonType.OK);
                    alert.showAndWait();
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

        if (validation.isValidUsername(username)) {
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
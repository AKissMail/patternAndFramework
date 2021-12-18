package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.Validation;
import de.gruppeo.wise2122_java_client.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.fxml.FXML;

public class CSignUp extends Validation {

    private ViewLoader loader = new ViewLoader();
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
    public CSignUp() {
        for (int i = 1; i <= requiredFields; i++) {
            list.add(false);
        }
    }

    /**
     * @TODO Datenbank-API
     * Klick auf "Registrieren"-Button
     * speichert Daten in Datenbank.
     */
    public void onMouseClicked_signUp() {
        // Code, der neuen Benutzer in DB speichert
        // Code, der eine neue Session startet
        // Code, der zum Hauptmenü leitet

        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }

    /**
     * Prüft, ob der Benutzername
     * den Richtlinien entspricht.
     */
    public void onKeyTyped_username() {
        String username = textField_signUp_username.getText();
        Color labelColor;

        if (isValidUsername(username) && isUsernameAvailable(new MPlayer())) {
            list.set(0, true);
            labelColor = Color.BLACK;

        } else {
            list.set(0, false);
            labelColor = Color.RED;
        }
        checkInputValidation(label_signUp_username, labelColor, list, button_signUp_signUp);
    }

    /**
     * Prüft, ob das erste Passwort
     * den Richtlinien entspricht.
     */
    public void onKeyTyped_password1() {
        String password1 = textField_signUp_password1.getText();
        Color labelColor;

        if (isValidPassword(password1)) {
            list.set(1, true);
            labelColor = Color.BLACK;
        } else {
            list.set(1, false);
            labelColor = Color.RED;
        }
        checkInputValidation(label_signUp_password1, labelColor, list, button_signUp_signUp);
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
        checkInputValidation(label_signUp_password2, labelColor, list, button_signUp_signUp);
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
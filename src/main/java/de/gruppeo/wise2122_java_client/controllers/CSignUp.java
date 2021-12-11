package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.Validation;
import de.gruppeo.wise2122_java_client.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class CSignUp extends Validation {

    private ViewLoader loader = new ViewLoader();
    private int checkSum = 0;

    @FXML private TextField textField_signUp_username;
    @FXML private TextField textField_signUp_password1;
    @FXML private TextField textField_signUp_password2;
    @FXML private Label label_signUp_username;
    @FXML private Label label_signUp_password1;
    @FXML private Label label_signUp_password2;
    @FXML private BorderPane mainPane;
    @FXML private Button button_signUp_signUp;

    // Zugriff auf Eingabewerte
    private String username = textField_signUp_username.getText();
    private String password1 = textField_signUp_password1.getText();
    private String password2 = textField_signUp_password2.getText();

    /**
     * Aktiviert den "Registrieren"-Button, wenn alle
     * drei Felder vollständig und korrekt ausgefüllt
     * wurden.
     *
     * @param checkSum wird erhöht oder verringert
     */
    private void checkInputValidation(int checkSum) {
        this.checkSum = checkSum;
        System.out.println("CheckSum: " + checkSum);

        if (checkSum == 3) {
            button_signUp_signUp.setDisable(false);
        } else {
            button_signUp_signUp.setDisable(true);
        }
    }

    /**
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
     * Wird bei jeder Tastatureingabe in das Feld "Username"
     * ausgeführt und prüft, ob die Benutzereingabe den
     * Richtlinien entspricht und ob der Benutzername noch
     * verfügbar ist oder in der DB bereits vergeben wurde.
     */
    public void onKeyPressed_username() {
        if (isValidUsername(username) && isPlayerAvailable(new MPlayer())) {
            label_signUp_username.setTextFill(Color.BLACK);
            checkInputValidation(+1);
        } else {
            label_signUp_username.setTextFill(Color.RED);
            checkInputValidation(-1);
        }
    }

    /**
     * Wird bei jeder Tastatureingabe in das Feld "Password1"
     * ausgeführt und prüft, ob das eingegebene Password den
     * Richtlinien entspricht.
     */
    public void onKeyPressed_password1() {
        if (isValidPassword(password1)) {
            label_signUp_password1.setTextFill(Color.BLACK);
            checkInputValidation(+1);
        } else {
            label_signUp_password1.setTextFill(Color.RED);
            checkInputValidation(-1);
        }

        System.out.println("VALID PW1: " + isValidPassword(password1));
    }

    /**
     * Wird bei jeder Tastatureingabe in das Feld
     * "Password2" ausgeführt und prüft, ob beide
     * Passwörter übereinstimmen.
     */
    public void onKeyPressed_password2() {
        if (password2.equals(password1)) {
            label_signUp_password2.setTextFill(Color.BLACK);
            checkInputValidation(+1);
        } else {
            label_signUp_password2.setTextFill(Color.RED);
            checkInputValidation(-1);
        }
    }

    /**
     * Klick auf "Zurück"-Button
     * navigiert auf die logIn-Maske.
     */
    public void onMouseClicked_back() {
        Pane pane = loader.getPane("logIn");
        mainPane.setRight(pane);
    }


    /**
     * Diese ist die Fehlernachricht, sie infomiert den Nutzer*innen über eine falsche eingaben
     * @param error Das ist die Fehlernachricht.

    public void errorprint(String error){
    System.out.println(error);
    Alert alert = new Alert(Alert.AlertType.WARNING, error, ButtonType.OK);
    alert.showAndWait();
    }*/
}
package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.Validation;
import de.gruppeo.wise2122_java_client.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.fxml.FXML;

public class CSettingsChangePassword extends Validation {

    private ViewLoader loader = new ViewLoader();
    private MPlayer player = new MPlayer();
    private int checkSum = 0;

    @FXML private Label label_settings_currentPassword;
    @FXML private Label label_settings_newPassword1;
    @FXML private Label label_settings_newPassword2;
    @FXML private TextField textField_settings_currentPassword;
    @FXML private TextField textField_settings_newPassword1;
    @FXML private TextField textField_settings_newPassword2;
    @FXML private Button button_settings_changePassword;

    // Zugriff auf Eingabewerte
    private String currentPassword = textField_settings_currentPassword.getText();
    private String newPassword1 = textField_settings_newPassword1.getText();
    private String newPassword2 = textField_settings_newPassword2.getText();

    public void onMouseClicked_changePassword() {
        // Speichert neues Passwort in DB
    }

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
            button_settings_changePassword.setDisable(false);
        } else {
            button_settings_changePassword.setDisable(true);
        }
    }

    /**
     * Wird bei jeder Tastatureingabe in das Feld "Password1"
     * ausgeführt und prüft, ob das eingegebene Password den
     * Richtlinien entspricht.
     */
    public void onKeyPressed_currentPassword() {
        if (isValidCurrentPassword(currentPassword)) {
            label_settings_currentPassword.setTextFill(Color.BLACK);
            checkInputValidation(+1);
        } else {
            label_settings_currentPassword.setTextFill(Color.RED);
            checkInputValidation(-1);
        }
    }

    public void onKeyPressed_newPassword1() {
        if (isValidPassword(newPassword1)) {
            label_settings_newPassword1.setTextFill(Color.BLACK);
            checkInputValidation(+1);
        } else {
            label_settings_newPassword1.setTextFill(Color.RED);
            checkInputValidation(-1);
        }
    }

    public void onKeyPressed_newPassword2() {
        if (isValidPassword(newPassword2)) {
            label_settings_newPassword2.setTextFill(Color.BLACK);
            checkInputValidation(+1);
        } else {
            label_settings_newPassword2.setTextFill(Color.RED);
            checkInputValidation(-1);
        }
    }
}

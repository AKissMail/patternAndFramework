package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Validation;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.fxml.FXML;

public class CSettingsChangePassword extends Validation {

    private ViewLoader loader = new ViewLoader();
    private int checkSum = 0;

    ArrayList<Boolean> list =  new ArrayList<Boolean>();

    @FXML private Label label_settings_currentPassword;
    @FXML private Label label_settings_newPassword1;
    @FXML private Label label_settings_newPassword2;
    @FXML private TextField textField_settings_currentPassword;
    @FXML private TextField textField_settings_newPassword1;
    @FXML private TextField textField_settings_newPassword2;
    @FXML private Button button_settings_changePassword;

    /**
     * Reserviert drei Speicherplätze
     * im Zwischenspeicher.
     */
    public CSettingsChangePassword() {
        for (int i = 1; i <= 3; i++) {
            list.add(false);
        }
    }

    /**
     * Wird bei jeder Tastatureingabe in das Feld "Aktuelles Passwort"
     * ausgeführt und prüft, ob das eingegebene Password den Richtlinien entspricht.
     */
    public void onKeyTyped_currentPassword() {
        String currentPassword = textField_settings_currentPassword.getText();
        Color labelColor;

        if (isValidPassword(currentPassword)) {
            list.set(0, true);
            labelColor = Color.BLACK;
        } else {
            list.set(0, false);
            labelColor = Color.RED;
        }
        checkInputValidation(label_settings_currentPassword, labelColor, list, button_settings_changePassword);
    }

    /**
     *
     */
    public void onKeyTyped_newPassword() {
        String password1 = textField_settings_newPassword1.getText();
        Color labelColor;

        if (isValidPassword(password1)) {
            list.set(1, true);
            labelColor = Color.BLACK;
        } else {
            list.set(1, false);
            labelColor = Color.RED;
        }
        checkInputValidation(label_settings_newPassword1, labelColor, list, button_settings_changePassword);
    }

    /**
     *
     */
    public void onKeyTyped_confirmedPassword() {
        String password2 = textField_settings_newPassword2.getText();
        Color labelColor;

        if (isValidPassword(password2)) {
            list.set(2, true);
            labelColor = Color.BLACK;
        } else {
            list.set(2, false);
            labelColor = Color.RED;
        }
        checkInputValidation(label_settings_newPassword2, labelColor, list, button_settings_changePassword);
    }

    /**
     * @TODO Datenbank-API
     * Speichert neues Passwort in der Datenbank
     */
    public void onMouseClicked_changePassword() {

    }
}
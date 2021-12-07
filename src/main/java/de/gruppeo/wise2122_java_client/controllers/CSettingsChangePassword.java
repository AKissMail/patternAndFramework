package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.Validation;
import de.gruppeo.wise2122_java_client.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CSettingsChangePassword extends Validation {

    @FXML private TextField textField_settings_currentPassword;
    @FXML private TextField textField_settings_newPassword1;
    @FXML private TextField textField_settings_newPassword2;

    private ViewLoader loader = new ViewLoader();
    private MPlayer player = new MPlayer();

    public void onMouseClicked_changePassword() {
        String currentPassword = textField_settings_currentPassword.getText();
        String newPassword1 = textField_settings_newPassword1.getText();
        String newPassword2 = textField_settings_newPassword2.getText();

        // Eingabefelder validieren
        if (isValidPassword(newPassword1)) {

        }
    }
}

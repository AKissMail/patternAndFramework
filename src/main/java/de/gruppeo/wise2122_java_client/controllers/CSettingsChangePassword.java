package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.Validation;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MConfig;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.fxml.FXML;

public class CSettingsChangePassword extends Validation {

    private ViewLoader loader;
    private int checkSum;
    private ArrayList<Boolean> list;

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
        loader = new ViewLoader();
        checkSum = 0;
        list =  new ArrayList<Boolean>();

        for (int i = 1; i <= 3; i++) {
            list.add(false);
        }
    }

    /**
     * Wird bei jeder Tastatureingabe in das Feld
     * "Aktuelles Passwort" ausgeführt. Überprüft,
     * ob etwas eingegen wurde.
     */
    public void onKeyTyped_currentPassword() {
        String currentPassword = textField_settings_currentPassword.getText();
        Color labelColor;

        if (!currentPassword.isEmpty()) {
            list.set(0, true);
            labelColor = Color.BLACK;
        } else {
            list.set(0, false);
            labelColor = Color.RED;
        }
        checkInputValidation(label_settings_currentPassword, labelColor, list, button_settings_changePassword);
    }

    /**
     * Wird bei jeder Tastatureingabe in das Feld
     * "Neues Passwort" ausgeführt und prüft, ob das
     * eingegebene Password den Richtlinien entspricht.
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
     * Wird bei jeder Tastatureingabe in das Feld
     * "Neues Passwort" ausgeführt und prüft, ob das
     * eingegebene Password mit dem neuen Passwort
     * übereinstimmt.
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
        String username = MConfig.getInstance().getUsername();
        String currentPassword = textField_settings_currentPassword.getText();
        String newPassword = textField_settings_newPassword2.getText();

        try {
            // Etabliert neue Serververbindung
            Connection connection = new Connection("/auth/changepassword");

            // Sendet JSON-Anfrage mit Zugangsdaten an Server
            connection.postData("{ \"username\": \"" + username + "\", \"password\": \"" + currentPassword + "\", \"newpassword\": \"" + newPassword + "\" }");

            // Speichert Token in Config-Objekt
            MConfig.getInstance().setPrivateToken(connection.getServerResponse());

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Das Password für " + username + " wurde erfolgreich geändert.", ButtonType.OK);
            alert.showAndWait();

            textField_settings_newPassword1.clear();
            textField_settings_newPassword2.clear();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.helper.Validation;
import de.gruppeo.wise2122_java_client.helper.Loader;
import de.gruppeo.wise2122_java_client.model.MConfig;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class CSettingsChangePassword extends Validation {

    private final Loader loader;
    private final ArrayList<Boolean> list;

    @FXML private BorderPane mainPane;
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
        loader = new Loader();
        list =  new ArrayList<>();

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
     * Aktualisiert das Passwort des angemeldeten Benutzers
     * und speichert den neuen Token in Config-Objekt.
     */
    public void onMouseClicked_changePassword() throws Exception {
        // Speichert Config-Daten in lokale Variablen
        String username = MConfig.getInstance().getUsername();
        String currentPassword = textField_settings_currentPassword.getText();
        String newPassword = textField_settings_newPassword2.getText();

        Connection con = new Connection("/auth/updatepassword");

        try {
            // Aktualisiert das Passwort des Users
            con.updatePassword(username, currentPassword, newPassword);

            // Überschreibt Token mit leerem String
            MConfig.getInstance().setPrivateToken("");

            // Zeigt Erfolgsmeldung an
            Alert success = new Alert(Alert.AlertType.INFORMATION, "Das Password wurde erfolgreich geändert.", ButtonType.OK);
            success.showAndWait();

            // Leitet zum Hauptmenü
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(loader.getScene("logIn"));
            stage.show();
        } catch (Exception e) {
            switch (con.getConnection().getResponseCode()) {
                case 401:
                    System.out.println("Response Code: 401");
                    Alert failure = new Alert(Alert.AlertType.WARNING, "Das aktuelle Passwort ist nicht korrekt.", ButtonType.OK);
                    failure.showAndWait();

                    clearTextfields(new TextField[]{textField_settings_currentPassword});
                    break;
            }
        }
    }

    /**
     * Bereinigt alle übergebenen Textfelder.
     *
     * @param textFields Textfelder
     */
    private void clearTextfields(TextField[] textFields) {
        for (TextField textField : textFields) {
            textField.clear();
        }
    }
}
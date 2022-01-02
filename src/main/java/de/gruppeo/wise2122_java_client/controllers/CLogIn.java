package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Configuration;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.Validation;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class CLogIn {
    ViewLoader loader;
    Validation validation;
    Configuration config;

    @FXML private BorderPane mainPane;
    @FXML private TextField textField_logIn_username;
    @FXML private TextField textField_logIn_password;
    @FXML private Button button_logIn_logIn;

    public CLogIn() throws Exception {
        loader = new ViewLoader();
        validation = new Validation();
        config = new Configuration();
    }

    /**
     * Klick auf "Anmelden"-Button
     * leitet den Anmeldevorgang ein.
     */
    public void onMouseClicked_logIn() throws Exception {
        Connection connection = new Connection("/auth/login");

        String username = textField_logIn_username.getText();
        String password = textField_logIn_password.getText();

        try {
            // Sendet JSON-Anfrage mit Zugangsdaten an Server
            connection.postData("{ \"username\": \"" + username + "\", \"password\": \"" + password + "\" }");

            // Speichert Token in Config-Datei
            config.writeProperty("privateToken", connection.getServerResponse());

            // Lädt Hauptmenü
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(loader.getScene("main"));
            stage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Die eingegebenen Anmeldedaten sind nicht korrekt. Bitte versuche es erneut.", ButtonType.OK);
            alert.showAndWait();
            textField_logIn_username.clear();
            textField_logIn_password.clear();
            button_logIn_logIn.setDisable(true);
        }
    }

    /**
     * Klick auf "Registrieren"-Button
     * navigiert auf die signUp-Maske.
     */
    public void onMouseClicked_signUp() {
        Pane pane = loader.getPane("signUp");
        mainPane.setRight(pane);
    }

    public void onKeyTyped_username() {
        enableLogInButton();
    }

    public void onKeyTyped_password() {
        enableLogInButton();
    }

    private void enableLogInButton() {
        int usernameLength = textField_logIn_username.getText().length();
        int passwordLength = textField_logIn_password.getText().length();

        if (usernameLength > 0 && passwordLength > 0) {
            button_logIn_logIn.setDisable(false);
        } else {
            button_logIn_logIn.setDisable(true);
        }
    }
}
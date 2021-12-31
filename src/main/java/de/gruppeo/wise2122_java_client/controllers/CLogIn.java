package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Configuration;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.Validation;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
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
}
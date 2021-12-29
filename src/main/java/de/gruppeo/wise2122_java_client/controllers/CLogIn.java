package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.Connection;
import de.gruppeo.wise2122_java_client.Validation;
import de.gruppeo.wise2122_java_client.ViewLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class CLogIn extends Validation {
    ViewLoader loader;
    Connection connection;

    @FXML private BorderPane mainPane;
    @FXML private TextField textField_logIn_username;
    @FXML private TextField textField_logIn_password;

    public CLogIn() throws Exception {
        loader = new ViewLoader();
        connection = new Connection("/auth/login");
    }

    /**
     * @TODO Datenbank-API
     * Klick auf "Anmelden"-Button
     * leitet den Anmeldevorgang ein.
     */
    public void onMouseClicked_logIn() throws Exception {
        String username = textField_logIn_username.getText();
        String password = textField_logIn_password.getText();

        if (isLoginDataValid(username, password, connection)) {
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(loader.getScene("main"));
            stage.show();
        } else {
            // Fehlermeldung anzeigen
            System.out.println("Falsche Eingaben");
            Alert alert = new Alert(Alert.AlertType.WARNING, "Ungültige Eingaben. Bitte prüfe deine Zugangsdaten.", ButtonType.OK);
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
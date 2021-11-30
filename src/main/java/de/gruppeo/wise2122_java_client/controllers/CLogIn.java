package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CLogIn {

    private ViewLoader loader = new ViewLoader();

    @FXML
    private BorderPane mainPane;

    @FXML
    private TextField textField_logIn_username;

    @FXML
    private TextField textField_logIn_password;

    /**
     * Klick auf "Registrieren"
     * navigiert auf die signUp-Maske.
     */
    public void onMouseClicked_signUp() {
        Pane pane = loader.getPane("signUp");
        mainPane.setRight(pane);
    }

    /**
     * Klick auf "Anmelden"
     * leitet den Anmeldevorgang ein.
     */
    public void onMouseClicked_logIn() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
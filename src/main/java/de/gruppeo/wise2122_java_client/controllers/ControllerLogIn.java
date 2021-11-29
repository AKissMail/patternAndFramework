package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogIn {

    @FXML
    private BorderPane mainPane;
    private ViewLoader loader = new ViewLoader();

    /**
     * Klick auf "Registrieren"
     * navigiert auf die signUp-Maske.
     */
    public void onMouseClicked_signUp() {
        Pane pane = loader.getPane("signUp");
        mainPane.setRight(pane);
    }

    /**
     *
     */
    public void onMouseClicked_logIn() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
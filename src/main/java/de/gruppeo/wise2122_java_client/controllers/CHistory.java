package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Loader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CHistory implements Initializable {
    Loader loader;

    @FXML private BorderPane mainPane;

    public CHistory() {
        loader = new Loader();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onMouseClicked_showHighscore() {
        // Wechselt Maske
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("highscore"));
        stage.show();
    }
}
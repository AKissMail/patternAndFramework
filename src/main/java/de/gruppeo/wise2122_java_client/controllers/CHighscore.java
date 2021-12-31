package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class CHighscore {

    private ViewLoader loader = new ViewLoader();

    @FXML
    private BorderPane mainPane;

    public void onMouseClicked_showMain() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
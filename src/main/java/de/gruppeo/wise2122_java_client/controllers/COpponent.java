package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.ViewLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class COpponent {

    private ViewLoader loader = new ViewLoader();

    @FXML
    private BorderPane mainPane;

    public void onMouseClicked_startQuiz() {

    }

    public void onMouseClicked_back() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("category"));
        stage.show();
    }

    public void onMouseClicked_joinQuiz() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("lobby"));
        stage.show();
    }
}
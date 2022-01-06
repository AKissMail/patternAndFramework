package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class CLobby {

    private ViewLoader loader = new ViewLoader();

    @FXML
    private BorderPane mainPane;


    public void onMouseClicked_startQuiz() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("fxml/quiz"));
        stage.show();
    }

    public void onMouseClicked_back() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("fxml/category"));
        stage.show();
    }

    public void onMouseClicked_chooseOpponent() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("fxml/opponent"));
        stage.show();
    }
}
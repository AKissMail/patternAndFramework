package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.ViewLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class CSettings {

    private ViewLoader loader = new ViewLoader();

    @FXML
    private BorderPane mainPane;


    public void onMouseClicked_changePicture() {
        Pane pane = loader.getPane("settings_changePicture");
        mainPane.setRight(pane);
    }

    public void onMouseClicked_changePassword() {
        Pane pane = loader.getPane("settings_changePassword");
        mainPane.setRight(pane);
    }

    public void onMouseClicked_resetScore() {
        Pane pane = loader.getPane("settings_resetScore");
        mainPane.setRight(pane);
    }

    public void onMouseClicked_back() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
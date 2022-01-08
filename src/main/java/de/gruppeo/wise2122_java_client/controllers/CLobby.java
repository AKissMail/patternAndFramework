package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MConfig;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class CLobby implements Initializable {
    private ViewLoader loader;

    @FXML private BorderPane mainPane;

    public CLobby() {
        loader = new ViewLoader();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Etabliert neue Serververbindungen zum Ändern des Playerstatus
            Connection changeStatus = new Connection("/player/changeplayerstatus");

            // Sendet JSON-Anfrage mit neuem Status an Server
            changeStatus.postData("{ \"status\": \"" + "searching" + "\", \"token\": \"" + MConfig.getInstance().getPrivateToken() + "\" }");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onMouseClicked_startQuiz() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("quiz"));
        stage.show();
    }

    public void onMouseClicked_back() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("category"));
        stage.show();
    }

    public void onMouseClicked_chooseOpponent() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("opponent"));
        stage.show();
    }
}
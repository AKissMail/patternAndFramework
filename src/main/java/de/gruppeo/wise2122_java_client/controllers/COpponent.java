package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.Connection;
import de.gruppeo.wise2122_java_client.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MCategory;
import de.gruppeo.wise2122_java_client.models.MOpponent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class COpponent {

    ViewLoader loader;
    MOpponent opponent;

    @FXML private BorderPane mainPane;
    @FXML private ScrollPane scrollPane_opponent_opponents;

    public COpponent() throws Exception {
        this.loader = new ViewLoader();

        Connection connection = new Connection("/category", "GET" ,"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYXgiLCJpYXQiOjE2NDAwMDE3ODQsImV4cCI6MTY0MDYwNjU4NH0.nHm43HtiJdS3UG_ccxw-sg6DPsq-4LpQo8yYbMy4BMDCRHIbcqNECjlyl3Y2udL1bbhOQfLwQwgOy4epnN1NYQ");
        this.opponent = new MOpponent(connection.getServerResponse());
    }

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
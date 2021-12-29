package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.Connection;
import de.gruppeo.wise2122_java_client.parsers.PCategory;
import de.gruppeo.wise2122_java_client.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MOpponent;
import de.gruppeo.wise2122_java_client.parsers.POpponent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class COpponent {
    ViewLoader loader;
    Connection connection;
    POpponent mapper;

    @FXML private BorderPane mainPane;
    @FXML private ScrollPane scrollPane_opponent_opponents;
    @FXML private Label label_opponent_foundOpponents;

    public COpponent() throws Exception {
        loader = new ViewLoader();
        connection = new Connection("/player/all" ,"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMaXNhIiwiaWF0IjoxNjQwNzc2ODU2LCJleHAiOjE2NDEzODE2NTZ9.K2rgEl5mS2AiZfuIDtXlHblcIGxxnqVNtiOlbpQ-Rr6S7KlDkXtcbF7HogYs3tH7eWIcURL_4KVnSVye09dk4Q");
        mapper = new POpponent(connection);
    }

    /**
     * Wird beim Aufruf der aktuellen Maske
     * ausgeführt. Befüllt das Menü mit Kategorien.
     */
    @FXML public void initialize() {
        for (MOpponent opponent : mapper.getList()) {
            System.out.println("Player: " + opponent.getUsername());
        }

        // Label zur Anzeige der Anzahl der gefundenen Spieler
        label_opponent_foundOpponents.setText(mapper.getList().size() + " Spieler gefunden");
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
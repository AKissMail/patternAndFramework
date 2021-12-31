package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Configuration;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MOpponent;
import de.gruppeo.wise2122_java_client.parsers.POpponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class COpponent {
    ViewLoader loader;
    Connection connection;
    Configuration config;
    POpponent mapper;

    @FXML private BorderPane mainPane;
    @FXML private ScrollPane scrollPane_opponent_opponents;
    @FXML private Label label_opponent_foundOpponents;
    @FXML private ListView listView_opponent_list;

    /**
     *
     * @throws Exception
     */
    public COpponent() throws Exception {
        loader = new ViewLoader();
        config = new Configuration();
        connection = new Connection("/player/all", config.readProperty("privateToken"));
        mapper = new POpponent(connection);
    }

    /**
     * Wird beim Aufruf der aktuellen Maske
     * ausgeführt. Befüllt das Menü mit Kategorien.
     */
    @FXML public void initialize() {
        for (MOpponent opponent : mapper.getList()) {
            listView_opponent_list.getItems().addAll(opponent.getUsername());
        }

        listView_opponent_list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("Selected Item: " + newValue);
            }
        });

        // Label zur Anzeige der Anzahl der gefundenen Spieler
        label_opponent_foundOpponents.setText(mapper.getList().size() + " Spieler sind online");
    }

    /**
     *
     */
    public void onMouseClicked_startQuiz() {

    }

    /**
     *
     */
    public void onMouseClicked_back() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("category"));
        stage.show();
    }

    /**
     *
     */
    public void onMouseClicked_joinQuiz() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("lobby"));
        stage.show();
    }
}
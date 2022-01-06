package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Configuration;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MConfiguration;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import de.gruppeo.wise2122_java_client.parsers.POpponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class COpponent {
    ViewLoader loader;
    POpponent mapper;
    Configuration config;

    @FXML private BorderPane mainPane;
    @FXML private Label label_opponent_foundOpponents;
    @FXML private ListView listView_opponent_list;
    @FXML private Button button_opponent_startQuiz;

    /**
     *
     * @throws Exception
     */
    public COpponent() throws Exception {
        loader = new ViewLoader();
        mapper = new POpponent(new Connection("/player/all"));
        config = new Configuration();
    }

    /**
     * Wird beim Aufruf der aktuellen Maske
     * ausgeführt. Befüllt das Menü mit Kategorien.
     */
    @FXML public void initialize() {
        for (MPlayer opponent : mapper.getList()) {
            listView_opponent_list.getItems().addAll(opponent.getUsername());
        }

        listView_opponent_list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    button_opponent_startQuiz.setDisable(false);
                } else {
                    button_opponent_startQuiz.setDisable(true);
                }
                System.out.println("Selected Player: " + newValue);

                // Schreibt ausgewählte Anzahl in Config
                MConfiguration.getInstance().setPlayer(newValue);
                new Configuration().writeConfiguration();
            }
        });

        // Label zur Anzeige der Anzahl der gefundenen Spieler
        label_opponent_foundOpponents.setText(mapper.getList().size() + " Spieler sind online");
    }

    /**
     * Zeigt das Quiz an.
     */
    public void onMouseClicked_startQuiz() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("fxml/quiz"));
        stage.show();
    }

    /**
     * Zeigt die Maske zur Wahl der Kategorie an.
     */
    public void onMouseClicked_back() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("fxml/category"));
        stage.show();
    }

    /**
     * Zeigt den Wartebereich an.
     */
    public void onMouseClicked_joinQuiz() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("fxml/lobby"));
        stage.show();
    }
}
package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MConfig;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import de.gruppeo.wise2122_java_client.parsers.POpponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

public class COpponent implements Initializable {
    ViewLoader loader;
    POpponent mapper;

    @FXML private BorderPane mainPane;
    @FXML private Label label_opponent_foundOpponents;
    @FXML private ListView listView_opponent_list;
    @FXML private Button button_opponent_startQuiz;

    /**
     * Initialisiert globale Objekte.
     *
     * @throws Exception
     */
    public COpponent() throws Exception {
        loader = new ViewLoader();
        mapper = new POpponent(new Connection("/player?status=online"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Befüllt Auswahlmenü mit Spielern, die online sind
        for (MPlayer opponent : mapper.getList()) {
            listView_opponent_list.getItems().addAll(opponent.getUsername());
        }

        listView_opponent_list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldOpponent, String newOpponent) {
                if (newOpponent != null) {
                    button_opponent_startQuiz.setDisable(false);
                } else {
                    button_opponent_startQuiz.setDisable(true);
                }
                // Schreibt ausgewählte Anzahl in Objekt
                MConfig.getInstance().setOpponent(listView_opponent_list.getSelectionModel().getSelectedItem());
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
        stage.setScene(loader.getScene("quiz"));
        stage.show();
    }

    /**
     * Zeigt die Maske zur Wahl der Kategorie an.
     */
    public void onMouseClicked_back() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("category"));
        stage.show();
    }

    /**
     * Zeigt den Wartebereich an.
     */
    public void onMouseClicked_joinQuiz() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("lobby"));
        stage.show();
    }
}
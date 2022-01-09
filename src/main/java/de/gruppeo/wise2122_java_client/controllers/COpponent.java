package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import de.gruppeo.wise2122_java_client.parsers.POpponent;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
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

    public COpponent() throws Exception {
        loader = new ViewLoader();
        mapper = new POpponent(new Connection("/player?status=waiting"));

        // Ändert Status auf 'Searching'
        System.out.println(new Connection("/player/changeplayerstatus").changePlayerStatus("searching"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /*Thread thread = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("HEy hey");


                    listView_opponent_list.getItems().clear();

                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }
            }
        });
        thread.start();*/

        // Befüllt Auswahlmenü mit Spielern, die auf 'Waiting' gestellt sind
        for (MPlayer opponent : mapper.getList()) {
            listView_opponent_list.getItems().addAll(opponent.getUsername());
        }

        // Setzt Labeltext
        setLabelWaitingPlayers();

        listView_opponent_list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldOpponent, String newOpponent) {
                if (newOpponent != null) {
                    button_opponent_startQuiz.setDisable(false);
                } else {
                    button_opponent_startQuiz.setDisable(true);
                }

                // Schreibt ausgewählte Anzahl in Objekt
                button_opponent_startQuiz.setText(listView_opponent_list.getSelectionModel().getSelectedItem() + " herausfordern");
            }
        });
    }

    /**
     * Setzt den Labeltext für die Anzahl
     * der wartenden Spieler.
     */
    private void setLabelWaitingPlayers() {
        String status;
        int players = mapper.getList().size();

        if (players == 0) {
            status = "Keine wartenden Spieler gefunden";
        } else if (players == 1) {
            status = "Einen wartenden Spieler gefunden";
        } else {
            status = players + " wartende Spieler gefunden";
        }
        label_opponent_foundOpponents.setText(status);
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
}
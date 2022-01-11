package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MConfig;
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
import java.util.*;

public class COpponent implements Initializable {
    ViewLoader loader;
    POpponent mapper;
    List<String> newOpponents;

    public static Timer timer;

    @FXML private BorderPane mainPane;
    @FXML private Label label_opponent_foundOpponents;
    @FXML private ListView listView_opponent_list;
    @FXML private Button button_opponent_startQuiz;

    public COpponent() throws Exception {
        loader = new ViewLoader();
        timer = new Timer();
        newOpponents = new ArrayList<>();

        // Ändert Status auf 'searching'
        System.out.println(new Connection("/player/changeplayerstatus").changePlayerStatus("searching"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<String> temp = new ArrayList<>();


        /**
         * TimerTask fragt den Server kontinuierlich nach neuen Gegnern.
         * Wenn neue Gegner gefunden wurden, werden sie der Liste hinzugefügt
         * und die GUI aktualisiert. Die Refreshrate wird im Config-Objekt gespeichert.
         */
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    try {
                        // Lädt neue Gegner vom Server
                        mapper = new POpponent(new Connection("/player?status=waiting"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Setzt Labeltext
                    setLabelWaitingPlayers();

                    List<String> serverOpponents = new ArrayList<>();

                    // Befüllt Auswahlliste mit Spielern, die auf 'waiting' gestellt sind
                    for (MPlayer opponent : mapper.getList()) {
                        serverOpponents.add(opponent.getUsername());
                    }

                    System.out.println("Server: " + serverOpponents);

                    for (String item : serverOpponents) {

                        // Wenn der aktuelle Username noch nicht in der Liste enthalten ist ...
                        if (!listView_opponent_list.getItems().contains(item)) {

                            // ... dann füge den Username des aktuellen Gegners hinzu
                            listView_opponent_list.getItems().add(item);
                            temp.add(item);
                        }
                    }
                    System.out.println("Temp: " + temp + " -- ListView: " + listView_opponent_list.getItems().toString());
                    System.out.println("Anzahl Server: " + serverOpponents.size() + " - Anzahl ListView: " + listView_opponent_list.getItems().size());

                    if (serverOpponents.size() != listView_opponent_list.getItems().size()) {

                        int index = 0;

                        for (int a = 0; a < listView_opponent_list.getItems().size(); a++) {

                            if (!serverOpponents.contains(listView_opponent_list.getItems().get(a))) {
                                System.out.println("index: " + a);

                                index = a;
                            }
                        }

                            //temp.removeAll(serverOpponents);
                            //System.out.println("Removed: " + temp);

                            listView_opponent_list.getItems().remove(listView_opponent_list.getItems().get(index));
                    }

                    System.out.println("==============");

                });
            }
        };
        timer.scheduleAtFixedRate(task, 0, MConfig.getInstance().getRefreshrateOpponents());

        /**
         * ChangeListener wacht über die Gegnerliste, registriert, wenn
         * ein Item ausgewählt wurde und sorgt sich um das Aktivieren
         * und Deaktivieren des Buttons.
         */
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
     * @TODO Kontaktaufnahme mit Gegner muss implementiert werden
     * Fordert den ausgewählten Gegner heraus.
     */
    public void onMouseClicked_startQuiz() {
        timer.cancel();

    }

    /**
     * Zeigt das Hauptmenü an.
     */
    public void onMouseClicked_back() {
        timer.cancel();

        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
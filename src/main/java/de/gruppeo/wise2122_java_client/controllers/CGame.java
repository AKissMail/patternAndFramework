package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MConfig;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import de.gruppeo.wise2122_java_client.parsers.PPlayer;
import javafx.application.Platform;
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
import java.util.*;

public class CGame implements Initializable {
    ViewLoader loader;
    PPlayer mapper;

    public static Timer gameTimer;

    @FXML private BorderPane mainPane;
    @FXML private Label label_game_foundGames;
    @FXML private ListView listView_game_gamelist;
    @FXML private Button button_game_joinGame;

    public CGame() {
        loader = new ViewLoader();
        gameTimer = new Timer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /**
         * TimerTask fragt den Server kontinuierlich nach neuen Gegnern.
         * Wenn neue Gegner gefunden wurden, werden sie der Liste hinzugefügt.
         * Wenn ein angezeigter Gegner die Lobby (Wartebereich) verlässt, wird
         * er von der Liste entfernt. Die Refreshrate wird im Config-Objekt gespeichert.
         */
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    try {
                        // Lädt neue Spiele vom Server
                        mapper = new PPlayer(new Connection("/player?status=WAITING"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Setzt Labeltext
                    setLabelAvailableGames();

                    List<String> serverOpponents = new ArrayList<>();

                    // Befüllt Auswahlliste mit Spielern, die auf 'WAITING' gestellt sind
                    for (MPlayer opponent : mapper.getPlayers()) {
                        serverOpponents.add(opponent.getUsername());
                    }

                    for (String item : serverOpponents) {

                        // Wennn Username nicht in Liste enthalten ist
                        if (!listView_game_gamelist.getItems().contains(item)) {

                            // Dann füge neuen Username hinzu
                            listView_game_gamelist.getItems().add(item);
                        }
                    }

                    if (serverOpponents.size() != listView_game_gamelist.getItems().size()) {

                        int index = 0;

                        for (int i = 0; i < listView_game_gamelist.getItems().size(); i++) {
                            if (!serverOpponents.contains(listView_game_gamelist.getItems().get(i))) {
                                index = i;
                            }
                        }
                        listView_game_gamelist.getItems().remove(listView_game_gamelist.getItems().get(index));
                    }
                });
            }
        };
        gameTimer.scheduleAtFixedRate(task, 0, MConfig.getInstance().getRefreshrate());

        /**
         * ChangeListener wacht über die Spieleliste, registriert, wenn
         * ein Spiel ausgewählt wurde und sorgt sich um das Aktivieren
         * und Deaktivieren des Buttons.
         */
        listView_game_gamelist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldOpponent, String newOpponent) {
                if (newOpponent != null) {
                    button_game_joinGame.setDisable(false);
                } else {
                    button_game_joinGame.setDisable(true);
                }
            }
        });
    }

    /**
     * @TODO Muss implementiert werden
     * Fügt den Username des Beitretenden dem
     * ausgewählten Spiel (PlayerTwo) hinzu.
     */
    public void onMouseClicked_joinGame() {
        gameTimer.cancel();

        System.out.println("Selected Game: " + listView_game_gamelist.getSelectionModel().getSelectedItem());

        try {
            // Etabliert neue Serververbindung
            Connection game = new Connection("/games/update");

            // Sendet JSON-Anfrage mit zweitem Spieler an Server
            game.postData("{ \"gamesid\": \"" + "PLATZHALTER" + "\", \"username\": \"" + "PLATZHALTER" + "\", \"status\": \"" + "PLATZHALTER" + "\" }");
        } catch (Exception e) {
            System.out.println("Spiel konnte nicht aktualisiert werden: " + e);
        }
    }

    /**
     * Setzt den Labeltext für die
     * Anzahl der verfügbaren Spiele.
     */
    private void setLabelAvailableGames() {
        String status;
        int players = mapper.getPlayers().size();

        if (players == 0) {
            status = "Keine offenen Spiele gefunden";
        } else if (players == 1) {
            status = "Ein offenes Spiel gefunden";
        } else {
            status = players + " offene Spiele gefunden";
        }
        label_game_foundGames.setText(status);
    }

    /**
     * Zeigt das Hauptmenü an.
     */
    public void onMouseClicked_back() {
        gameTimer.cancel();

        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
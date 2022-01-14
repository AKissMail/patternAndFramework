package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MConfig;
import de.gruppeo.wise2122_java_client.models.MGame;
import de.gruppeo.wise2122_java_client.parsers.PGame;
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
    PGame mapper;

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
                        mapper = new PGame(new Connection("/games/open"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Setzt Labeltext
                    setLabelAvailableGames();

                    List<String> serverGames = new ArrayList<>();

                    // Befüllt Auswahlliste mit offenen Spielen
                    for (MGame game : mapper.getGames()) {
                        String username = game.getPlayerone().getUsername().toUpperCase();
                        String category = game.getCategory().getCategoryname();
                        int rounds = game.getRounds().getRounds();

                        serverGames.add(username + " (" + category + " - " + rounds + " Runden)");
                    }
                    System.out.println("Server: " + serverGames);

                    for (String item : serverGames) {
                        // Wennn Username nicht in Liste enthalten ist
                        if (!listView_game_gamelist.getItems().contains(item)) {
                            // Dann füge neuen Username hinzu
                            listView_game_gamelist.getItems().add(item);
                        }
                    }

                    // Verringert Auswahlliste, wenn Spiele nicht mehr existieren
                    if (serverGames.size() != listView_game_gamelist.getItems().size()) {

                        int index = 0;

                        for (int i = 0; i < listView_game_gamelist.getItems().size(); i++) {
                            if (!serverGames.contains(listView_game_gamelist.getItems().get(i))) {
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
            public void changed(ObservableValue<? extends String> observable, String oldGame, String newGame) {
                if (newGame != null) {
                    button_game_joinGame.setDisable(false);
                } else {
                    button_game_joinGame.setDisable(true);
                }
            }
        });
    }

    /**
     * Fügt den Username des Beitretenden dem
     * ausgewählten Spiel (PlayerTwo) hinzu.
     */
    public void onMouseClicked_joinGame() throws Exception {
        MConfig.getInstance().setJoinedGameID(mapper.getGames().get(listView_game_gamelist.getSelectionModel().getSelectedIndex()).getId());
        String username = MConfig.getInstance().getUsername();

        // Aktualisiert das ausgewählte Spiel
        Connection update = new Connection("/games/update");
        update.updateGame(MConfig.getInstance().getJoinedGameID(), username, "JOINED");
    }

    /**
     * Setzt den Labeltext für die
     * Anzahl der verfügbaren Spiele.
     */
    private void setLabelAvailableGames() {
        String status;
        int games = mapper.getGames().size();

        if (games == 0) {
            status = "Keine offenen Spiele gefunden";
        } else if (games == 1) {
            status = "Ein offenes Spiel gefunden";
        } else {
            status = games + " offene Spiele gefunden";
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
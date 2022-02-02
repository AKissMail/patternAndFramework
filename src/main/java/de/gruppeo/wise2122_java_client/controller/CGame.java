package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.helper.Loader;
import de.gruppeo.wise2122_java_client.model.MConfig;
import de.gruppeo.wise2122_java_client.model.MGame;
import de.gruppeo.wise2122_java_client.parser.PGame;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.*;

public class CGame implements Initializable {
    private final Loader loader;
    private PGame mapper;
    private Alert alert;

    public static Timer gameTimer;
    public static TimerTask joinTask;
    public static TimerTask opponentTask;

    @FXML private BorderPane mainPane;
    @FXML private Label label_game_foundGames;
    @FXML private ListView listView_game_gamelist;
    @FXML private Button button_game_joinGame;

    public CGame() {
        loader = new Loader();
        gameTimer = new Timer();
    }

    /**
     * TimerTask fragt den Server kontinuierlich nach neuen Gegnern.
     * Wenn neue Gegner gefunden wurden, werden sie der Liste hinzugefügt.
     * Wenn ein angezeigter Gegner die Lobby (Wartebereich) verlässt, wird
     * er von der Liste entfernt. Die Refreshrate wird im Config-Objekt gespeichert.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        opponentTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    try {
                        // Lädt neue Spiele vom Server
                        mapper = new PGame(new Connection("/games/open"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    setLabelAvailableGames();
                    List<String> serverGames = new ArrayList<>();

                    // Befüllt Auswahlliste mit offenen Spielen
                    for (MGame game : mapper.getGames()) {
                        String username = game.getPlayerone().getUsername().toUpperCase();
                        String category = game.getCategory().getCategoryname();
                        int rounds = game.getRounds().getRounds();

                        serverGames.add(username + " (" + category + " - " + rounds + " Runden)");
                    }

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
        gameTimer.scheduleAtFixedRate(opponentTask, 0, MConfig.getInstance().getRefreshrate());

        /**
         * ChangeListener wacht über die Spieleliste, registriert, wenn
         * ein Spiel ausgewählt wurde und sorgt sich um das Aktivieren
         * und Deaktivieren des Buttons.
         */
        listView_game_gamelist.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observable, oldGame, newGame) -> {
            if (newGame != null) {
                button_game_joinGame.setDisable(false);
            } else {
                button_game_joinGame.setDisable(true);
            }
        });
    }

    /**
     * Fügt den Username des Beitretenden dem
     * ausgewählten Spiel (PlayerTwo) hinzu und
     * ändert den Spielstatus auf 'beigetreten'.
     */
    public void onMouseClicked_joinGame() {
        // Speichert SpielID des ausgewählten Spiels in Config-Objekt
        MConfig.getInstance().setJoinedGameID(mapper.getGames().get(listView_game_gamelist.getSelectionModel().getSelectedIndex()).getId());

        // Speichert Config-Daten in lokalen Variablen
        int joinedGameID = MConfig.getInstance().getJoinedGameID();
        String playerTwo = MConfig.getInstance().getUsername();

        // Aktualisiert das ausgewählte Spiel
        Connection addPlayerTwo = new Connection("/games/update");
        addPlayerTwo.updateGame(joinedGameID, "", playerTwo, "JOINED");

        joinTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (isSelectedGameRunning()) {
                        gameTimer.cancel();
                        alert.close();

                        // Wechselt die Maske
                        Stage stage = (Stage) mainPane.getScene().getWindow();
                        stage.setScene(loader.getScene("quiz"));
                        stage.show();
                    }
                });
            }
        };
        gameTimer.scheduleAtFixedRate(joinTask, 0, MConfig.getInstance().getRefreshrate());

        // Zeigt Meldung an
        ButtonType RELOAD = new ButtonType("Spiel wechseln");
        alert = new Alert(Alert.AlertType.CONFIRMATION, "Warte auf den Start des Spiels", RELOAD);
        alert.showAndWait();

        /**
         * Wenn sich Spieler 2 entscheidet, nicht auf
         * Spieler 1 zu warten, wird der Status des
         * ausgewählten Spiels auf OFFEN gestellt und
         * der eingetragene PlayerTwo entfernt.
         */
        if (alert.getResult() == RELOAD) {
            Connection removePlayerTwo = new Connection("/games/update");
            removePlayerTwo.updateGame(joinedGameID, "", "null", "OPEN");
        }
    }

    /**
     * Prüft, ob der Spielstatus des ausgewählten Spiels
     * auf RUNNING geändert wurde. Wenn Spieler 1 das
     * Spiel startet, wird der Status von JOINED auf
     * RUNNING gewechselt.
     *
     * @return boolean
     */
    private boolean isSelectedGameRunning() {
        boolean isSelectedGameRunning = false;

        try {
            PGame mapperGame = new PGame(new Connection("/games/" + MConfig.getInstance().getJoinedGameID()));

            for (MGame game : mapperGame.getGames()) {
                if (game.getGamestatus().equals("RUNNING")) {
                    isSelectedGameRunning = true;
                }
            }
        } catch (Exception e) {
            alert.close();
        }
        return isSelectedGameRunning;
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
     * Zeigt das Hauptmenü an, entfernt Spieler 2
     * aus dem beigetretenen Spiel und öffnet das
     * Spiel wieder für andere Herausforderer.
     */
    public void onMouseClicked_back() throws Exception {
        // Beendet den Timer
        gameTimer.cancel();

        int gameID = MConfig.getInstance().getJoinedGameID();

        if (gameID != 0) {
            // Entfernt Spieler 2 aus dem beigetretenen Spiel
            Connection removePlayerTwo = new Connection("/games/update");
            removePlayerTwo.updateGame(gameID, "", "null", "OPEN");
        }

        // Wechselt die Maske
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
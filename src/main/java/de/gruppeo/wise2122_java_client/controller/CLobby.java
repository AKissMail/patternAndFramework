package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.helper.Loader;
import de.gruppeo.wise2122_java_client.model.MConfig;
import de.gruppeo.wise2122_java_client.model.MGame;
import de.gruppeo.wise2122_java_client.parser.PGame;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.util.*;

public class CLobby {
    private final Loader loader;

    public static Timer lobbyTimer;
    public static TimerTask registerTask;

    private String playerTwo;
    private final String playerOne = MConfig.getInstance().getUsername();
    private final String category = MConfig.getInstance().getCategory().toString();
    private final int rounds = (int) MConfig.getInstance().getRounds();

    @FXML private BorderPane mainPane;
    @FXML private Button button_lobby_startQuiz;
    @FXML private Label label_lobby_searchingNetwork;
    @FXML private Label label_lobby_playerOne;
    @FXML private Label label_lobby_playerTwo;
    @FXML private Circle circle_lobby_playerOne;
    @FXML private Circle circle_lobby_playerTwo;

    public CLobby() {
        loader = new Loader();
        lobbyTimer = new Timer();
    }

    /**
     * Registriert ein neues Spiel mit der zuvor getätigten
     * Quizkonfiguration und prüft regelmäßig, ob ein Gegner
     * beigetreten ist. Dann wird der Spielername und das
     * Profilbild des Gegners angezeigt.
     *
     * @throws Exception Gegner konnte nicht hinzugefügt werden
     */
    public void initialize() throws Exception {
        String playername = MConfig.getInstance().getUsername();

        registerNewGame();
        loader.loadThumbnail(circle_lobby_playerOne, playername);
        label_lobby_playerOne.setText(playername);

        registerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    // Prüft Verfügbarkeit des Gegners
                    if (isChallengerAvailable()) {
                        label_lobby_searchingNetwork.setText(playerTwo + " ist deinem Spiel beigetreten");

                        // Zeigt Profilbild des Gegners an
                        loader.loadThumbnail(circle_lobby_playerTwo, playerTwo);
                        label_lobby_playerTwo.setText(playerTwo);

                        button_lobby_startQuiz.setDisable(false);
                    } else {
                        label_lobby_searchingNetwork.setText(category + " (" + rounds + " Runden) bereitgestellt");

                        // Zeigt Platzhalter an, solange kein Gegner beigetreten ist
                        loader.setAnyThumbnail(circle_lobby_playerTwo, "/questionmark.png");
                        label_lobby_playerTwo.setText("");

                        button_lobby_startQuiz.setDisable(true);
                    }
                });
            }
        };
        lobbyTimer.scheduleAtFixedRate(registerTask, 0, MConfig.getInstance().getRefreshrate());
    }

    /**
     * Etabliert neue Serververbindung zur
     * Registrierung eines neuen Spiels mit
     * der zuvor ausgewählten Quizkategorie,
     * der Rundenzahl und dem Benutzer, der
     * das Spiel gestartet hat.
     *
     * @throws Exception Spiel konnte nicht registriert werden
     */
    private void registerNewGame() throws Exception {
        // Erstellt neues Spiel
        Connection con = new Connection("/games/create");
        con.createGame(playerOne, category, rounds);

        // Speichert SpielID in Config-Objekt
        PGame mapperCreate = new PGame(con.getServerResponse());
        MConfig.getInstance().setRegisteredGameID(mapperCreate.getGames().get(0).getId());
    }

    /**
     * Etabliert neue Serververbindung zur
     * Abfrage des Spiels, welches vom in der
     * Lobby wartenden Spieler registriert
     * wurde. Es wird geprüft, ob ein Heraus-
     * forderer gefunden wurde.
     */
    private boolean isChallengerAvailable() {
        boolean isChallengerAvailable = false;

        try {
            PGame mapperGame = new PGame(new Connection("/games/" + MConfig.getInstance().getRegisteredGameID()));

            for (MGame game : mapperGame.getGames()) {
                if (game.getPlayertwo() != null) {
                    isChallengerAvailable = true;
                    playerTwo = game.getPlayertwo().getUsername();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isChallengerAvailable;
    }

    /**
     * Startet das Quiz und ändert den Status
     * des von Spieler 1 erstellten Spiels auf
     * RUNNING, damit es nicht mehr in der
     * List der offenen Spiele angezeigt wird.
     */
    public void onMouseClicked_startQuiz() {
        // Beendet den Timer
        lobbyTimer.cancel();

        // Speichert Congig-Daten in lokalen Variablen
        String username = MConfig.getInstance().getUsername();
        int registeredGameID = MConfig.getInstance().getRegisteredGameID();

        // Aktualisiert den Status des Spiels
        Connection con = new Connection("/games/update");
        con.updateGame(registeredGameID, username, "", "RUNNING");

        // Welchselt die Maske
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("quiz"));
        stage.show();
    }

    /**
     * Zeigt die Quiz-Kategorien an und löscht
     * das von Spieler 1 erstellte Spiel wieder,
     * damit eine neue Spiel-Konfiguration vorgenommen
     * werden kann.
     */
    public void onMouseClicked_back() {
        // Beendet den Timer
        lobbyTimer.cancel();

        // Löscht das erstellte Spiel
        Connection con = new Connection("/games/update");
        con.deleteGame(MConfig.getInstance().getRegisteredGameID());

        // Wechselt die Maske
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("category"));
        stage.show();
    }
}
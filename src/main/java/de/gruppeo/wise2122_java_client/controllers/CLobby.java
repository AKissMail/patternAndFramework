package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.Validation;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MConfig;
import de.gruppeo.wise2122_java_client.models.MGame;
import de.gruppeo.wise2122_java_client.parsers.PGame;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.*;

public class CLobby {
    ViewLoader loader;
    Validation validation;

    public static Timer lobbyTimer;
    private String playerTwo;

    private String playerOne = MConfig.getInstance().getUsername();
    private String category = MConfig.getInstance().getCategory().toString();
    private int rounds = (int) MConfig.getInstance().getRounds();

    @FXML private BorderPane mainPane;
    @FXML private Button button_lobby_startQuiz;
    @FXML private Label label_lobby_searchingNetwork;

    public CLobby() {
        loader = new ViewLoader();
        validation = new Validation();
        lobbyTimer = new Timer();
    }

    public void initialize() throws Exception {
        registerNewGame();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    // Prüft Verfügbarkeit des Gegners
                    if (isChallengerAvailable()) {
                        label_lobby_searchingNetwork.setText(playerTwo + " ist deinem Spiel beigetreten");

                        // @TODO Profilbild und Username des beigetretenen Gegners auf GUI anzeigen

                        button_lobby_startQuiz.setDisable(false);
                    } else {
                        label_lobby_searchingNetwork.setText(category + " (" + rounds + " Runden) bereitgestellt");
                        button_lobby_startQuiz.setDisable(true);
                    }
                });
            }
        };
        lobbyTimer.scheduleAtFixedRate(task, 0, MConfig.getInstance().getRefreshrate());
    }

    /**
     * Etabliert neue Serververbindung zur
     * Registrierung eines neuen Spiels mit
     * der zuvor ausgewählten Quizkategorie,
     * der Rundenzahl und dem Benutzer, der
     * das Spiel gestartet hat.
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
    public void onMouseClicked_startQuiz() throws Exception {
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
     * Zeigt die Quiz-Kategorien an und
     * löscht das von Spieler 1 erstellte
     * Spiel wieder, damit eine neue Spiel-
     * Konfiguration vorgenommen werden kann.
     */
    public void onMouseClicked_back() throws Exception {
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
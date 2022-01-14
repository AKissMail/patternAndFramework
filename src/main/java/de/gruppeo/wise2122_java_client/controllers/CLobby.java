package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.Validation;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MConfig;
import de.gruppeo.wise2122_java_client.models.MGame;
import de.gruppeo.wise2122_java_client.parsers.PGame;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.*;

public class CLobby implements Initializable {
    ViewLoader loader;
    Validation validation;

    public static Timer lobbyTimer;
    private String playerTwo;

    // Spielkonfiguration
    String username = MConfig.getInstance().getUsername();
    String category = MConfig.getInstance().getCategory().toString();
    int rounds = (int) MConfig.getInstance().getRounds();

    @FXML private BorderPane mainPane;
    @FXML private Button button_lobby_startQuiz;
    @FXML private Label label_lobby_searchingNetwork;

    public CLobby() {
        loader = new ViewLoader();
        validation = new Validation();
        lobbyTimer = new Timer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Registriert neues Spiel beim Server
        registerNewGame();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    // Prüft nach Verfügbarkeit des Gegners
                    if (isChallengerAvailable()) {
                        label_lobby_searchingNetwork.setText(playerTwo + " ist deinem Spiel beigetreten");



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
    private void registerNewGame() {
        try {
            Connection create = new Connection("/games/create");
            create.createGame("{ \"username\": \"" + username + "\", \"category\": \"" + category + "\", \"rounds\": \"" + rounds + "\" }");

            PGame mapperCreate = new PGame(create.getServerResponse());
            MConfig.getInstance().setRegisteredGameID(mapperCreate.getGames().get(0).getId());
        } catch (Exception e) {
            System.out.println("Neues Spiel konnte nicht registriert werden: " + e);
        }
    }

    /**
     * Etabliert neue Serververbindung zur
     * Abfrage des Spiels, welches vom in der
     * Lobby wartenden Spieler registriert
     * wurde. Es wird geprüft, ob ein Heraus-
     * forderer gefunden wurde.
     *
     * PlayerONE = Spielerzeuger (CLobby)
     * PlayerTWO = Beitretender Spieler (CGame)
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
     * Startet das Quiz.
     */
    public void onMouseClicked_startQuiz() {
        lobbyTimer.cancel();

        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("quiz"));
        stage.show();
    }

    /**
     * Zeigt die Quiz-Kategorien an.
     */
    public void onMouseClicked_back() {
        lobbyTimer.cancel();

        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("category"));
        stage.show();
    }
}
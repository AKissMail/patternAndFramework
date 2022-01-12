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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.*;

public class CLobby implements Initializable {
    ViewLoader loader;
    Validation validation;
    PGame mapper;
    Alert alert;

    public static Timer lobbyTimer;
    private int gameIndex;
    private String playerTwo;

    @FXML private BorderPane mainPane;
    @FXML private Button button_lobby_startQuiz;

    public CLobby() {
        loader = new ViewLoader();
        validation = new Validation();
        lobbyTimer = new Timer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Registriert neues Spiel beim Server
        //registerNewGame();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {

                    // Prüft nach Verfügbarkeit des Gegners
                    if (isChallengerAvailable()) {
                        alert = new Alert(Alert.AlertType.CONFIRMATION, "Einladung von " + playerTwo + " annehmen?", ButtonType.YES, ButtonType.NO);
                        alert.showAndWait();

                        if (alert.getResult() == ButtonType.YES) {
                            button_lobby_startQuiz.setDisable(false);
                        } else {
                            button_lobby_startQuiz.setDisable(true);
                        }
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

        String username = MConfig.getInstance().getUsername();
        String category = MConfig.getInstance().getCategory().toString();
        String rounds = MConfig.getInstance().getRounds().toString();

        try {
            // Etabliert neue Serververbindungen
            Connection game = new Connection("/games/create");

            // Sendet JSON-Anfrage mit Spielkonfiguration an Server
            game.postData("{ \"username\": \"" + username + "\", \"category\": \"" + category + "\", \"rounds\": \"" + rounds + "\" }");

            System.out.println("Response: " + game.getServerResponse());
            mapper = new PGame(game.getServerResponse());

            for (MGame item : mapper.getGames()) {
                System.out.println("Spielindex: " + item.getId());
                gameIndex = item.getId();
                playerTwo = item.getPlayertwo().getUsername();
            }

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
     * PlayerONE = Spielerzeuger (Lobby)
     * PlayerTWO = Herausforderer (Gegnerliste)
     */
    private boolean isChallengerAvailable() {
        boolean isChallengerAvailable = false;

        /*try {
            mapper = new PGame(new Connection("/games/" + gameIndex));

            for (MGame game : mapper.getGames()) {
                if (game.getPlayertwo() != null) {
                    isChallengerAvailable = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
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
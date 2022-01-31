package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.helper.Loader;
import de.gruppeo.wise2122_java_client.model.MConfig;
import de.gruppeo.wise2122_java_client.parser.PGame;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXML;

public class CResult {

    Loader loader;
    TimerTask resultTask;
    public static Timer resultTimer;

    int gameID;
    int waitingResult;
    String gameStatus;
    int scorePlayerOne;
    int scorePlayerTwo;
    String playerOne;
    String playerTwo;

    @FXML private BorderPane mainPane;
    @FXML private Label label_result_resultText;
    @FXML private Label label_result_points;
    @FXML private Circle circle_result_loser;
    @FXML private Circle circle_result_winner;

    public CResult() {
        loader = new Loader();
        resultTimer = new Timer();
        waitingResult = 30;

        int gameIDP_playerOne = MConfig.getInstance().getRegisteredGameID();
        int gameID_playerTwo = MConfig.getInstance().getJoinedGameID();

        if (gameIDP_playerOne == 0) {
            gameID = gameID_playerTwo;
        } else {
            gameID = gameIDP_playerOne;
        }
    }

    public void initialize() {
        // Zeigt Platzhalter an, solange das Spiel des Gegners nicht beendet ist
        loader.setAnyThumbnail(circle_result_loser, "/questionmark.png");
        loader.setAnyThumbnail(circle_result_winner, "/questionmark.png");

        resultTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    PGame mapper = new PGame(new Connection("/games/" + gameID));
                    gameStatus = mapper.getGames().get(0).getGamestatus();
                    scorePlayerOne = mapper.getGames().get(0).getPlayeronescore();
                    scorePlayerTwo = mapper.getGames().get(0).getPlayertwoscore();
                    playerOne = mapper.getGames().get(0).getPlayerone().getUsername();
                    playerTwo = mapper.getGames().get(0).getPlayertwo().getUsername();

                    if (gameStatus.equals("CLOSE")) {
                        resultTimer.cancel();
                        setResultText();
                    }
                });
            }
        };
        resultTimer.scheduleAtFixedRate(resultTask, 0, MConfig.getInstance().getRefreshrate());
    }

    /**
     * Aktualisiert den Ergebnistext, setzt
     * die Profilbilder der Kontrahenten und
     * zeigt die jeweiligen Punkte auf GUI an.
     */
    private void setResultText() {
        String nameCurrentPlayer;
        if (scorePlayerOne > scorePlayerTwo) {
            loader.loadThumbnail(circle_result_winner, playerOne);
            loader.loadThumbnail(circle_result_loser, playerTwo);
            label_result_points.setText(scorePlayerTwo + " Punkte vs. " + scorePlayerOne + " Punkte");
            nameCurrentPlayer = playerOne;
        } else {
            loader.loadThumbnail(circle_result_winner, playerTwo);
            loader.loadThumbnail(circle_result_loser, playerOne);
            label_result_points.setText(scorePlayerOne + " Punkte vs. " + scorePlayerTwo + " Punkte");
            nameCurrentPlayer = playerTwo;
        }

        String resulttext;
        if (scorePlayerOne == scorePlayerTwo) {
            resulttext = "Das Spiel ist unentschieden!";
        } else {
            resulttext = nameCurrentPlayer + " hat gewonnen!";
        }
        label_result_resultText.setText(resulttext);
    }

    /**
     * Zeigt den Highscore an.
     */
    public void onMouseClicked_showHighscore() {
        // Timer beeden
        resultTimer.cancel();

        // Wechselt Maske
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("highscore"));
        stage.show();
    }

    /**
     * Zeigt das Hauptmenü an.
     */
    public void onMouseClicked_showMain() {
        // Timer beeden
        resultTimer.cancel();

        // Wechselt Maske
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}

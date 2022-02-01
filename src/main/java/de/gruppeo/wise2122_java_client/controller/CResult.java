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
    TimerTask showResultTask;

    public static Timer resultTimer;
    public static Timer showResultTimer;

    int gameID;
    String gameStatus;
    int scorePlayerOne;
    int scorePlayerTwo;
    int rounds;
    String playerOne;
    String playerTwo;
    boolean showResults;
    double showResultsSeconds;

    @FXML private BorderPane mainPane;
    @FXML private Label label_result_resultText;
    @FXML private Label label_result_points;
    @FXML private Circle circle_result_loser;
    @FXML private Circle circle_result_winner;

    public CResult() {
        loader = new Loader();
        showResults = false;
        resultTimer = new Timer();
        showResultTimer = new Timer();

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
                    rounds = mapper.getGames().get(0).getRounds().getRounds();

                    if (gameStatus.equals("CLOSE") || isWaiting()) {
                        resultTimer.cancel();
                        setResultText();
                    }
                });
            }
        };
        resultTimer.scheduleAtFixedRate(resultTask, 0, MConfig.getInstance().getRefreshrate());
    }

    /**
     * Gibt zurück, ob noch auf den
     * Gegner gewartet werden muss oder
     * nicht. Notwendig, um sicherzustellen,
     * dass ein Spieler sein Ergebnis angezeigt
     * bekommt, obwohl der Gegner vorzeitig
     * ausgestiegen ist.
     *
     * @return Warten?
     */
    private boolean isWaiting() {
        showResultsSeconds = getWaitingSeconds();
        showResultTask = new TimerTask() {

            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (showResultsSeconds <= 0) {
                        showResultTask.cancel();
                        showResults = true;
                    } else {
                        showResultsSeconds--;
                    }
                });
            }
        };
        showResultTimer.scheduleAtFixedRate(showResultTask, 0, 1000);
        return showResults;
    }

    /**
     * Berechnet die maximale Wartezeit
     * auf das Spielergebnis.
     *
     * @return (Spielrunden * (Countdown + NextQuestion-Timer))
     */
    private double getWaitingSeconds() {
        return rounds * (MConfig.getInstance().getDefaultCountdown() + MConfig.getInstance().getDefaultNextQuestion());
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
        showResultTimer.cancel();

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
        showResultTimer.cancel();

        // Wechselt Maske
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
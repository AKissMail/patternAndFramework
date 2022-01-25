package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.Loader;
import de.gruppeo.wise2122_java_client.models.MConfig;
import de.gruppeo.wise2122_java_client.models.MQuestion;
import de.gruppeo.wise2122_java_client.models.MCountdown;
import de.gruppeo.wise2122_java_client.parsers.PGame;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.util.*;

import javafx.fxml.FXML;

public class CQuiz implements Initializable {
    Loader loader;
    MCountdown countdown;
    TimerTask countdownTask;
    TimerTask questionTask;
    Button[] buttons;

    private final int gameID;
    private int questionNumber;

    private final String playerOne;
    private final String playerTwo;

    private double seconds;
    private double totalSeconds;
    private int nextQuestion;

    public static Timer quizTimer;
    public static Timer questionTimer;

    private final ArrayList<MQuestion> questions;
    private String correctAnswer;

    @FXML private BorderPane mainPane;
    @FXML private ProgressBar progressBar_quiz_progress;
    @FXML private Label label_quiz_timer;
    @FXML private Label label_quiz_numberQuestion;
    @FXML private Label label_quiz_question;
    @FXML private Button button_quiz_answerA;
    @FXML private Button button_quiz_answerB;
    @FXML private Button button_quiz_answerC;
    @FXML private Button button_quiz_answerD;
    @FXML private Button button_quiz_nextQuestion;
    @FXML private Label label_quiz_pointsOp1;
    @FXML private Label label_quiz_pointsOp2;
    @FXML private Label label_quiz_nameOp1;
    @FXML private Label label_quiz_nameOp2;

    public CQuiz() {
        loader = new Loader();
        quizTimer = new Timer();
        questionTimer = new Timer();
        questionNumber = 0;
        nextQuestion = MConfig.getInstance().getDefaultNextQuestion();

        int gameIDP_playerOne = MConfig.getInstance().getRegisteredGameID();
        int gameID_playerTwo = MConfig.getInstance().getJoinedGameID();

        if (gameIDP_playerOne == 0) {
            gameID = gameID_playerTwo;
        } else {
            gameID = gameIDP_playerOne;
        }

        questions = new ArrayList<>();
        PGame mapper = new PGame(new Connection("/games/" + gameID));
        playerOne = mapper.getGames().get(0).getPlayerone().getUsername();
        playerTwo = mapper.getGames().get(0).getPlayertwo().getUsername();

        for (MQuestion question : mapper.getGames().get(0).getQuestions()) {
            questions.add(question);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.buttons = new Button[] {button_quiz_answerA, button_quiz_answerB, button_quiz_answerC, button_quiz_answerD};
        this.countdown = new MCountdown();
        runQuizRound();
    }

    /**
     * Enthält alle Methoden, die für eine
     * Spielrunde benötigt werden.
     */
    private void runQuizRound() {
        try {
            questionTask.cancel();
        } catch (Exception e) {
            System.out.println("Timer ist null");
        }

        if (getCurrentRound() == getTotalRounds()) {
            countdownTask.cancel();
            Stage stage = (Stage) mainPane.getScene().getWindow();
            stage.setScene(loader.getScene("result"));
            stage.show();
        } else {
            setDefaultAnswersColor();
            setPlayerNames();
            disableAnswers(false);
            setNumberQuestions();
            setQuestion();
            setAnswers();
            startCountdown();

            button_quiz_nextQuestion.setText("Nächste Frage");
            button_quiz_nextQuestion.setDisable(true);
            countdown.resetSeconds();
            questionNumber++;
        }
    }

    /**
     * Prüft, ob die angeklickte Antwort korrekt
     * ist und sendet das Ergebnis, zusammen mit
     * der berechneten Punkzahl, an den Server.
     *
     * @param isTimeUp Countdown abgelaufen?
     * @param clickedButton Angeklickte Antwort
     */
    private void checkAnswer(boolean isTimeUp, Button clickedButton) {
        boolean isCorrect = false;
        countdownTask.cancel();
        nextQuestion = 5;

        // Deaktiviert Antworten
        disableAnswers(true);

        // Prüft, ob die Zeit abgelaufen ist
        if (!isTimeUp) {
            // Prüft, ob angeklickte Antwort korrekt ist
            if (clickedButton.getText().equals(correctAnswer)) {
                isCorrect = true;
            }
        }

        // Färbt Antworten
        for (Button button : buttons) {
            if (button.getText().equals(correctAnswer)) {
                button.setStyle("-fx-background-color: #047b06; ");
            } else {
                button.setStyle("-fx-background-color: #ff0000; ");
            }
        }

        // Sendet Antwort an Server
        Connection con = new Connection("/games/dropanswer");
        con.dropAnswer(gameID, isPlayerOne(), isCorrect, getTime());

        // Aktualisiert den Spielerscore
        setPoints();

        // Startet Countdown für nächste Runde
        startCountdownNextQuestion();
    }

    /**
     * Startet nächste Spielrunde automatisch,
     * sodass der Gegner nicht zu lange auf
     * das Endergebnis warten muss.
     */
    private void startCountdownNextQuestion() {
        button_quiz_nextQuestion.setDisable(false);

        questionTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    String buttonText;

                    if (nextQuestion <= 0) {
                        buttonText = "Nächste Frage";
                        runQuizRound();
                    } else {
                        // Ändert die Button-Beschriftung
                        if (getCurrentRound() == getTotalRounds()) {
                            buttonText = "Ergebnis anzeigen (" + nextQuestion + ")";
                        } else {
                            buttonText = "Nächste Frage (" + nextQuestion + ")";
                        }
                    }
                    nextQuestion--;
                    button_quiz_nextQuestion.setText(buttonText);
                });
            }
        };
        questionTimer.scheduleAtFixedRate(questionTask, 0, 1000);
    }

    /**
     * Berechnet die Punkte einer Spielrunde.
     *
     * @return Time
     */
    private int getTime() {
        return (int) seconds * 1000;
    }

    /**
     * Gibt die aktuelle Rundenzahl zurück.
     *
     * @return aktuelle Rundenzahl
     */
    private int getCurrentRound() {
        PGame mapper;
        int currentRound = 0;

        try {
            mapper = new PGame(new Connection("/games/" + gameID));
            currentRound = mapper.getGames().get(0).getPlayertworound();

            if (isPlayerOne()) {
                currentRound = mapper.getGames().get(0).getPlayeroneround();
            }
        } catch (Exception e) {
            deleteCurrentGame();
        }
        return currentRound;
    }

    /**
     * Gibt die Gesamtrundenzahl des Spiels zurück.
     *
     * @return Gesamtrundenzahl
     */
    private int getTotalRounds() {
        PGame mapper;
        int totalRounds = 0;

        try {
            mapper = new PGame(new Connection("/games/" + gameID));
            totalRounds = mapper.getGames().get(0).getRounds().getRounds();
        } catch (Exception e) {
            deleteCurrentGame();
        }
        return totalRounds;
    }

    /**
     * Sendet einen POST an den Server
     * und löscht damit das aktuelle
     * Spiel. In der Datenbank wird
     * es weiterhin angezeigt - der
     * Status ändert sich auf CLOSE.
     */
    private void deleteCurrentGame() {
        try {
            // Löscht das erstellte Spiel
            Connection con = new Connection("/games/update");
            con.deleteGame(MConfig.getInstance().getRegisteredGameID());
            System.out.println("Spiel wurde gelöscht");
        } catch (Exception es) {
            System.out.println("Spiel konnte nicht gelöscht werden");
        }
    }

    /**
     * Setzt die aktuelle Fragennummer.
     */
    private void setNumberQuestions() {
        label_quiz_numberQuestion.setText("Frage " + (getCurrentRound()+1) + " von " + getTotalRounds());
    }

    /**
     * Fordert eine neue Frage beim Server an
     * und präsentiert sie auf der GUI.
     */
    private void setQuestion() {
        label_quiz_question.setText(questions.get(questionNumber).getQuestion());
    }

    /**
     * Fordert insgesamt vier Antworten, eine Lösung und
     * drei falsche Antworten beim Server an und präsentiert
     * sie auf der GUI.
     */
    private void setAnswers() {
        ArrayList<String> answers = new ArrayList<>();
        int buttonNumber = 0;

        // Speichert Antworten der übergebenen Frage
        answers.add(questions.get(questionNumber).getCorrectAnswer());
        answers.add(questions.get(questionNumber).getFalseAnswer1());
        answers.add(questions.get(questionNumber).getFalseAnswer2());
        answers.add(questions.get(questionNumber).getFalseAnswer3());

        // Speichert korrekte Antwort in globaler Variable
        correctAnswer = questions.get(questionNumber).getCorrectAnswer();

        // Mischt die Liste aller Antworten
        Collections.shuffle(answers);

        // Zeigt Antworten auf GUI an
        for (Button button : buttons) {
            button.setText(answers.get(buttonNumber++));
        }
    }

    /**
     * Setzt die Namen der beiden Spieler.
     */
    private void setPlayerNames() {
        label_quiz_nameOp1.setText(playerOne);
        label_quiz_nameOp2.setText(playerTwo);
    }

    /**
     * Setzt die Punktzahl beider Spieler
     */
    private void setPoints() {
        int playerOneScore;
        int playerTwoScore;

        PGame mapper = new PGame(new Connection("/games/" + gameID));
        playerOneScore = mapper.getGames().get(0).getPlayeronescore();
        playerTwoScore = mapper.getGames().get(0).getPlayertwoscore();

        label_quiz_pointsOp1.setText(playerOneScore + " P");
        label_quiz_pointsOp2.setText(playerTwoScore + " P");
    }

    /**
     * Gibt zurück, ob sich bei dem handelnden
     * Spieler um Spieler 1 handelt oder nicht.
     *
     * @return isPlayerOne
     */
    private boolean isPlayerOne() {
        boolean isPlayerOne = false;

        if (MConfig.getInstance().getUsername().equals(playerOne)) {
            isPlayerOne = true;
        }
        return isPlayerOne;
    }

    /**
     * Deaktiviert alle Antworten.
     *
     * @param isDisabled Button deaktivieren?
     */
    private void disableAnswers(boolean isDisabled) {
        for (Button button : buttons) {
            button.setDisable(isDisabled);
        }
    }

    /**
     * Färbt Buttons in Standardfarbe.
     */
    private void setDefaultAnswersColor() {
        for (Button button : buttons) {
            button.setStyle("-fx-background-color: #008ae6;");
        }
    }

    /**
     * Startet einen Countdown, der einen
     * Ladebalken und die zugehörige Beschriftung
     * im Sekundentakt aktualisiert.
     */
    private void startCountdown() {
        countdownTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    seconds = countdown.getCurrentSeconds();
                    totalSeconds = countdown.getTotalSeconds();

                    progressBar_quiz_progress.setProgress(seconds / totalSeconds);
                    label_quiz_timer.setText(String.format("%.0f", seconds));
                    countdown.decreaseSeconds(1);

                    Color color;
                    if (seconds > totalSeconds / 2) {
                        color = Color.WHITE;
                    } else {
                        color = Color.BLACK;
                    }
                    label_quiz_timer.setTextFill(color);

                    if (seconds == 0.0) {
                        checkAnswer(true, button_quiz_answerA);
                    }
                });
            }
        };
        quizTimer.scheduleAtFixedRate(countdownTask, 0, 1000);
    }

    /**
     * Wird bei Klick auf Antwort A ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerA() {
        checkAnswer(false, button_quiz_answerA);
    }

    /**
     * Wird bei Klick auf Antwort B ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerB() {
        checkAnswer(false, button_quiz_answerB);
    }

    /**
     * Wird bei Klick auf Antwort C ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerC() {
        checkAnswer(false, button_quiz_answerC);
    }

    /**
     * Wird bei Klick auf Antwort D ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerD() {
        checkAnswer(false, button_quiz_answerD);
    }

    /**
     * Zeigt die nächste Frage an oder wechselt
     * zur Ergebnis-Maske.
     */
    public void onMouseClicked_nextQuestion() {
        runQuizRound();
    }

    /**
     * Klick auf "Beenden"-Button beendet
     * das aktuell laufende Quiz und navigiert
     * anschließend zum Hauptmenü.
     */
    public void onMouseClicked_quitGame() {
        // Beendet Timer
        quizTimer.cancel();

        // Wechselt Maske
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
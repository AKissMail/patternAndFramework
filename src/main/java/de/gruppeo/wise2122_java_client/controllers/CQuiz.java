package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MConfig;
import de.gruppeo.wise2122_java_client.models.MQuestion;
import de.gruppeo.wise2122_java_client.models.MTimer;
import de.gruppeo.wise2122_java_client.parsers.PQuestion;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class CQuiz implements Initializable {
    ViewLoader loader;
    PQuestion mapper;
    MTimer timer;

    private int questionID;
    private int points;
    private String correctAnswer;
    private ArrayList<MQuestion> questions;
    private ArrayList<String> answers;

    @FXML private BorderPane mainPane;
    @FXML private ProgressBar progressBar_quiz_progress;
    @FXML private Label label_quiz_timer;
    @FXML private Label label_quiz_numberQuestion;
    @FXML private Label label_quiz_question;
    @FXML private Button button_quiz_answerA;
    @FXML private Button button_quiz_answerB;
    @FXML private Button button_quiz_answerC;
    @FXML private Button button_quiz_answerD;
    @FXML private Label label_quiz_pointsOp1;
    @FXML private Label label_quiz_pointsOp2;
    @FXML private Label label_quiz_nameOp1;
    @FXML private Label label_quiz_nameOp2;

    public CQuiz() throws Exception {
        loader = new ViewLoader();
        timer = new MTimer();
        mapper = new PQuestion(new Connection("/questions?category=" + MConfig.getInstance().getCategory().toString()));
        points = 0;

        questions = new ArrayList<>();
        for(MQuestion question : mapper.getQuestions()) {
            questions.add(question);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        questionID = 0;

        startCountdown();
        setNumberQuestions();
        setQuestion();
        setAnswers();
    }

    /**
     * Prüft, ob die angeklickte Antwort korrekt
     * ist und sendet das Ergebnis, zusammen mit
     * der berechneten Punkzahl, an den Server.
     */
    private void checkAnswer() throws Exception {
        Button[] buttons = new Button[] {button_quiz_answerA, button_quiz_answerB, button_quiz_answerC, button_quiz_answerD};

        // Speichert Config-Daten in lokale Variablen
        int gameID = MConfig.getInstance().getRegisteredGameID();

        // Färbt Antworten
        for (Button button : buttons) {
            if (button.getText().equals(correctAnswer)) {
                System.out.println(button.getText());
                button.setStyle("-fx-background-color: #047b06; ");
            } else {
                button.setStyle("-fx-background-color: #ff0000; ");
            }
        }

        // Sendet Antwort an Server
        Connection con = new Connection("/dropAnswer");
        //con.dropAnswer(gameID, playerOne, answer, timer);

        // Deaktiviert Antworten
        disableAnswers();
    }

    /**
     * Startet einen Countdown, der einen
     * Ladebalken und die zugehörige Beschriftung
     * im Sekundentakt aktualisiert.
     */
    private void startCountdown() {
        label_quiz_timer.setText(String.format("%.0f", timer.getSeconds()));

        Thread thread = new Thread(() -> {
            while (timer.getSeconds() > 0) {
                try {
                    Thread.sleep(1000);
                    timer.decreaseSeconds(1);
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }

                Platform.runLater(() -> {
                    Color color;
                    if (timer.getSeconds() > 5.0) {
                        color = Color.WHITE;
                    } else {
                        color = Color.BLACK;
                    }

                    label_quiz_timer.setTextFill(color);
                    label_quiz_timer.setText(String.format("%.0f", timer.getSeconds()));
                    progressBar_quiz_progress.setProgress(timer.getSeconds() / 10.0);
                });
            }
            disableAnswers();
        });
        thread.start();
    }

    /**
     * Setzt die aktuelle Fragennummer.
     */
    private void setNumberQuestions() {
        int number = 1;
        label_quiz_numberQuestion.setText("Frage " + questionID + 1 + " von " + number);
    }

    /**
     * Fordert eine neue Frage beim Server an
     * und präsentiert sie auf der GUI.
     */
    private void setQuestion() {
        label_quiz_question.setText(questions.get(questionID).getQuestion());
    }

    /**
     * Fordert insgesamt vier Antworten, eine Lösung und
     * drei falsche Antworten beim Server an und präsentiert
     * sie auf der GUI.
     */
    private void setAnswers() {
        answers = new ArrayList<>();

        // Speichert Antworten der übergebenen Frage
        answers.add(questions.get(questionID).getCorrectAnswer());
        answers.add(questions.get(questionID).getFalseAnswer1());
        answers.add(questions.get(questionID).getFalseAnswer2());
        answers.add(questions.get(questionID).getFalseAnswer3());

        // Speichert korrekte Antwort in globaler Variable
        correctAnswer = questions.get(questionID).getCorrectAnswer();

        // Mischt die Liste aller Antworten
        Collections.shuffle(answers);

        // Zeigt Antworten auf GUI an
        button_quiz_answerA.setText(answers.get(0));
        button_quiz_answerB.setText(answers.get(1));
        button_quiz_answerC.setText(answers.get(2));
        button_quiz_answerD.setText(answers.get(3));
    }

    /**
     * Deaktiviert alle Antworten.
     */
    private void disableAnswers() {
        button_quiz_answerA.setDisable(true);
        button_quiz_answerB.setDisable(true);
        button_quiz_answerC.setDisable(true);
        button_quiz_answerD.setDisable(true);
    }

    /**
     * Wird bei Klick auf Antwort A ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerA() throws Exception {
        checkAnswer();
    }

    /**
     * Wird bei Klick auf Antwort B ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerB() throws Exception {
        checkAnswer();
    }

    /**
     * Wird bei Klick auf Antwort C ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerC() throws Exception {
        checkAnswer();
    }

    /**
     * Wird bei Klick auf Antwort D ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerD() throws Exception {
        checkAnswer();
    }

    /**
     * Klick auf "Beenden"-Button beendet
     * das aktuell laufende Quiz und navigiert
     * anschließend zum Hauptmenü.
     */
    public void onMouseClicked_quitGame() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
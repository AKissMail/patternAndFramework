package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MTimer;
import de.gruppeo.wise2122_java_client.parsers.PQuestion;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.fxml.FXML;

public class CQuiz {
    ViewLoader loader;
    MTimer timer;
    PQuestion mapper;

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
        //mapper = new PQuestion(new Connection("/question"));

        /**
         * Leerer Konstruktor wird aktuell nur zu
         * Testzwecken verwendet. Es wird auf die lokale
         * JSON-Datei 'questions.JSON' zugegriffen.
         */
        mapper = new PQuestion();
    }

    /**
     * Wird beim Maskenaufruf ausgeführt und initialisiert
     * den Ladebalken und die zugehörige Beschriftung mit Startwerten.
     *
     * @throws MalformedURLException
     */
    @FXML public void initialize() throws MalformedURLException {
        startCountdown();
        setNumberQuestions();
        setQuestion(0);
        setAnswers(0);
        setPoints();

        /*while (isPlaying()) {

        }*/
    }

    /**
     * TODO Status vom Server erhalten, ob noch eine Frage gespielt wird
     * Fordert den Status, ob noch eine Frage gespielt wird,
     * vom Server an und gibt den boolschen Wert zurück.
     *
     * @return Status
     */
    private boolean isPlaying() {
        // Serveranfrage implementieren ...
        return true;
    }

    private void setNumberQuestions() {
        label_quiz_numberQuestion.setText("Frage 1 von 10");
    }

    /**
     * TODO Neue Frage beim Server anfragen
     * Fordert eine neue Frage beim Server an
     * und präsentiert sie auf der GUI.
     */
    private void setQuestion(int id) {
        label_quiz_question.setText(mapper.getList().get(id).getQuestion());
    }

    /**
     * TODO Vier Antworten beim Server anfragen
     * Fordert insgesamt vier Antworten, eine Lösung und
     * drei falsche Antworten beim Server an und präsentiert
     * sie auf der GUI.
     */
    private void setAnswers(int id) {
        List<String> answers = new ArrayList<>();
        String correctAnswer;

        // Fordert Antworten vom Server an
        String[] serverResponse = mapper.getList().get(id).getAnswers();

        // Speichert Array in Liste
        for(String item : serverResponse) {
            answers.add(item);
        }

        // Speichert korrekte Antwort
        correctAnswer = answers.get(0);

        // Mischt die Antworten
        Collections.shuffle(answers);

        System.out.println("Richtig: " + correctAnswer);

        // Zeigt Antworten auf GUI
        button_quiz_answerA.setText(answers.get(0));
        button_quiz_answerB.setText(answers.get(1));
        button_quiz_answerC.setText(answers.get(2));
        button_quiz_answerD.setText(answers.get(3));
    }

    /**
     * TODO Punkte eines Spielers beim Server anfragen
     * Fordert die aktuelle Gesamtpunktzahl eines Spielers
     * beim Server an und präsentiert sie auf der GUI.
     */
    private void setPoints() {

    }

    /**
     *
     */
    private void calculatePoints() {

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
     * Deaktiviert alle Antworten.
     */
    private void disableAnswers() {
        button_quiz_answerA.setDisable(true);
        button_quiz_answerB.setDisable(true);
        button_quiz_answerC.setDisable(true);
        button_quiz_answerD.setDisable(true);
    }

    /**
     * TODO Antwort A an Server senden
     * Wird bei Klick auf Antwort A ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerA() {
        // Serververbindung muss implementiert werden ...
        System.out.println("Answer A was klicked");
    }

    /**
     * TODO Antwort B an Server senden
     * Wird bei Klick auf Antwort B ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerB() {
        // Serververbindung muss implementiert werden ...
        System.out.println("Answer B was klicked");
    }

    /**
     * TODO Antwort C an Server senden
     * Wird bei Klick auf Antwort C ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerC() {
        // Serververbindung muss implementiert werden ...
        System.out.println("Answer C was klicked");
    }

    /**
     * TODO Antwort D an Server senden
     * Wird bei Klick auf Antwort D ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerD() {
        // Serververbindung muss implementiert werden ...
        System.out.println("Answer D was klicked");
    }

    /**
     * Klick auf "Beenden"-Button beendet
     * das aktuell laufende Quiz und navigiert
     * anschließend zum Hauptmenü.
     */
    public void onMouseClicked_quitGame() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("fxml/main"));
        stage.show();
    }
}
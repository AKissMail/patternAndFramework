package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MTimer;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.MalformedURLException;
import javafx.fxml.FXML;

public class CQuiz {

    private ViewLoader loader = new ViewLoader();
    private MTimer timer = new MTimer();

    @FXML private BorderPane mainPane;

    // Countdown
    @FXML private ProgressBar progressBar_quiz_progress;
    @FXML private Label label_quiz_timer;

    // Frage
    @FXML private Label label_quiz_numberQuestion;
    @FXML private Label label_quiz_question;

    // Antworten
    @FXML private Button button_quiz_answerA;
    @FXML private Button button_quiz_answerB;
    @FXML private Button button_quiz_answerC;
    @FXML private Button button_quiz_answerD;

    // Punkte
    @FXML private Label label_quiz_pointsOp1;
    @FXML private Label label_quiz_pointsOp2;
    @FXML private Label label_quiz_nameOp1;
    @FXML private Label label_quiz_nameOp2;

    /**
     * Wird beim Maskenaufruf ausgeführt und initialisiert
     * den Ladebalken und die zugehörige Beschriftung mit Startwerten.
     *
     * @throws MalformedURLException
     */
    @FXML public void initialize() throws MalformedURLException {
        startCountdown();
        setNumberQuestions();
        setQuestion();
        setAnswers();
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
    private void setQuestion() {

        label_quiz_question.setText("Hier steht eine schwierige Frage ...");
    }

    /**
     * TODO Vier Antworten beim Server anfragen
     * Fordert insgesamt vier Antworten, eine Lösung und
     * drei falsche Antworten beim Server an und präsentiert
     * sie auf der GUI.
     */
    private void setAnswers() {

        button_quiz_answerA.setText("Antwort A");
        button_quiz_answerB.setText("Antwort B");
        button_quiz_answerC.setText("Antwort C");
        button_quiz_answerD.setText("Antwort D");
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
     * Wird bei Klick auf Antwort A ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerB() {
        // Serververbindung muss implementiert werden ...
        System.out.println("Answer B was klicked");
    }

    /**
     * TODO Antwort C an Server senden
     * Wird bei Klick auf Antwort A ausgeführt.
     * Sendet Antwort an den Server, der diese mit
     * der Lösung der aktuellen Frage vergleicht.
     */
    public void onMouseClicked_answerC() {
        // Serververbindung muss implementiert werden ...
        System.out.println("Answer C was klicked");
    }

    /**
     * TODO Antwort D an Server senden
     * Wird bei Klick auf Antwort A ausgeführt.
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
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
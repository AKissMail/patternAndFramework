package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MTimer;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.net.MalformedURLException;
import javafx.fxml.FXML;

public class CQuiz {

    private ViewLoader loader = new ViewLoader();
    private MTimer timer = new MTimer();

    @FXML private BorderPane mainPane;
    @FXML private Label label_quiz_timer;
    @FXML private ProgressBar progressBar_quiz_progress;

    @FXML public void initialize() throws MalformedURLException {
        label_quiz_timer.setText(timer.getSeconds() + " s");
        progressBar_quiz_progress.setProgress(timer.getSeconds() / timer.defaultSeconds);

        startTimer();
    }

    public void onMouseClicked_answerA() {
        System.out.println("Answer A was klicked");
    }

    public void onMouseClicked_answerB() {
        System.out.println("Answer B was klicked");
    }

    public void onMouseClicked_answerC() {
        System.out.println("Answer C was klicked");
    }

    public void onMouseClicked_answerD() {
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

    private void startTimer() {
        Thread thread = new Thread(() -> {
            while (timer.getSeconds() > 0) {
                try {
                    System.out.println("TimerInWhile: " + timer.getSeconds());
                    Thread.sleep(1000);
                    timer.decreaseSeconds(1);
                } catch (Exception e) {
                    System.out.println("Error: " + e);
                }

                Platform.runLater(() -> {
                    label_quiz_timer.setText(timer.getSeconds() + " s");
                    System.out.println("Progress: " + timer.getSeconds() / 10.0);
                    progressBar_quiz_progress.setProgress(timer.getSeconds() / 10.0);
                });
            }
        });
        thread.start();
    }
}
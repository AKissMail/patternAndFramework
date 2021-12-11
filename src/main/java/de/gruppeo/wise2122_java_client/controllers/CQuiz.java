package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MTimer;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.net.MalformedURLException;

public class CQuiz {

    private ViewLoader loader = new ViewLoader();

    @FXML private BorderPane mainPane;
    @FXML private Label label_quiz_timer;

    @FXML public void initialize() throws MalformedURLException {
        MTimer timer = new MTimer(10, "TIMER1", label_quiz_timer);
        timer.start();

        //MTimer timer1 = new MTimer(10, "TIMER2", label_quiz_timer);
        //timer1.start();
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
}
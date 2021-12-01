package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CQuiz {

    private ViewLoader loader = new ViewLoader();

    @FXML
    private BorderPane mainPane;


    public void onMouseClicked_answerA() {

    }

    public void onMouseClicked_answerB() {

    }

    public void onMouseClicked_answerC() {

    }

    public void onMouseClicked_answerD() {

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
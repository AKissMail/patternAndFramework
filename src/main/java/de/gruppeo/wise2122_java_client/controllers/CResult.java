package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Loader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import java.net.URL;
import java.util.ResourceBundle;

public class CResult implements Initializable {

    Loader loader;

    @FXML private BorderPane mainPane;
    @FXML private Button button_result_main;
    @FXML private Button button_result_showHighscore;
    @FXML private Label label_result_resultText;
    @FXML private Label label_result_points;
    @FXML private Circle circle_result_loser;
    @FXML private Circle circle_result_winner;

    public CResult() {
        loader = new Loader();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     * Zeigt den Highscore an.
     */
    public void onMouseClicked_showHighscore() {

    }

    /**
     * Zeigt das Hauptmenü an.
     */
    public void onMouseClicked_showMain() {

    }
}

package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Configuration;
import de.gruppeo.wise2122_java_client.helpers.Loader;
import de.gruppeo.wise2122_java_client.models.MConfig;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class CMain implements Initializable {
    Loader loader;
    Configuration config;

    @FXML private BorderPane mainPane;
    @FXML private Circle circle_main_picture;
    @FXML private Label label_main_username;

    public CMain() {
        loader = new Loader();
        config = new Configuration();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String playername = MConfig.getInstance().getUsername();

        // Lädt das Profilbild
        loader.loadThumbnail(circle_main_picture, playername);

        // Setzt den Playernamen
        label_main_username.setText(playername);
    }

    /**
     * Zeigt die Quiz-Kategorien an.
     */
    public void onMouseClicked_startQuiz() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("category"));
        stage.show();
    }

    /**
     * Zeigt die Liste der registrierten Spiele an.
     */
    public void onMouseClicked_joinGame() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("game"));
        stage.show();
    }

    /**
     * Zeigt die Einstellungen an.
     */
    public void onMouseClicked_showSettings() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("settings"));
        stage.show();
    }

    /**
     * Zeigt den Highscore an.
     */
    public void onMouseClicked_showHighscore() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("highscore"));
        stage.show();
    }

    /**
     * Zeigt die Spielhistorie an.
     */
    public void onMouseClicked_showHistory() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("history"));
        stage.show();
    }

    /**
     * Zeigt die LogIn-Maske an und
     * überschreibt das private Token.
     */
    public void onMouseClicked_logOut() {
        // Überschreibt Token mit leerem String
        MConfig.getInstance().setPrivateToken("");

        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("logIn"));
        stage.show();
    }
}
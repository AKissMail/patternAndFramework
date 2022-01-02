package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Configuration;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.net.MalformedURLException;

public class CMain {
    ViewLoader loader;
    Configuration config;

    @FXML private BorderPane mainPane;
    @FXML private Circle circle_main_picture;
    @FXML private Label label_main_username;

    public CMain() {
        loader = new ViewLoader();
        config = new Configuration();
    }

    @FXML public void initialize() throws MalformedURLException {
        circle_main_picture.setFill(new ImagePattern(loader.loadImage("PICTURE")));
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
     * Beendet die aktuell laufende
     * Session und navigiert zur Anmeldung.
     */
    public void onMouseClicked_logOut() throws MalformedURLException {
        // Überschreibt Token in Config-Datei mit leerer Zeichenkette
        config.writeProperty("privateToken", "");

        // Wechselt zur LogIn-Maske
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("logIn"));
        stage.show();
    }
}
package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Configuration;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MConfig;
import de.gruppeo.wise2122_java_server.security.JwtTokenProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class CMain implements Initializable {
    ViewLoader loader;
    Configuration config;
    JwtTokenProvider tokenProvider;

    @FXML private BorderPane mainPane;
    @FXML private Circle circle_main_picture;
    @FXML private Label label_main_username;

    public CMain() {
        loader = new ViewLoader();
        config = new Configuration();
        tokenProvider = new JwtTokenProvider();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Ändert Status auf 'Online'
            System.out.println(new Connection("/player/changeplayerstatus").changePlayerStatus("online"));

            circle_main_picture.setFill(new ImagePattern(loader.loadImage("PICTURE")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception es) {
            es.printStackTrace();
        }

        // Setzt den Playernamen
        label_main_username.setText(tokenProvider.getUsernameFromToken(MConfig.getInstance().getPrivateToken(), MConfig.getInstance().getJwtSecret()));
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
     * Zeigt die Liste der wartendne Gegner an.
     */
    public void onMouseClicked_inviteOpponents() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("opponent"));
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
     * Zeigt die LogIn-Maske an und
     * überschreibt das private Token.
     */
    public void onMouseClicked_logOut() throws Exception {
        // Ändert Status auf 'Offline'
        System.out.println(new Connection("/player/changeplayerstatus").changePlayerStatus("offline"));

        // Überschreibt Token mit leerem String
        MConfig.getInstance().setPrivateToken("");

        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("logIn"));
        stage.show();
    }
}
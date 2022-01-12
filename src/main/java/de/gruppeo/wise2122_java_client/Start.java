package de.gruppeo.wise2122_java_client;

import de.gruppeo.wise2122_java_client.controllers.CLobby;
import de.gruppeo.wise2122_java_client.controllers.CGame;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("fxml/logIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Quizzz");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Wird nach Programmende ausgeführt
        stage.setOnCloseRequest(event -> {
                try {
                    // Beendet alle Timer
                    CGame.gameTimer.cancel();
                    CLobby.lobbyTimer.cancel();
                } catch (Exception e) {
                    System.out.println("Keine laufende Timer");
                }
            }
        );
    }

    public static void main(String[] args) {
        launch();
    }
}
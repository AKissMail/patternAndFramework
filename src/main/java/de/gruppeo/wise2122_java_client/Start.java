package de.gruppeo.wise2122_java_client;

import de.gruppeo.wise2122_java_client.controller.CLobby;
import de.gruppeo.wise2122_java_client.controller.CGame;
import de.gruppeo.wise2122_java_client.controller.CQuiz;
import de.gruppeo.wise2122_java_client.controller.CResult;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Start extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("fxml/logIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);

        // Fügt App-Icon hinzu (nur Windows)
        Image icon = new Image("file:quizzzIcon.png)");
        stage.getIcons().add(icon);

        // Setzt Fensterparameter
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
                    CQuiz.quizTimer.cancel();
                    CQuiz.questionTimer.cancel();
                    CResult.resultTimer.cancel();
                } catch (Exception e) {}
            }
        );
    }

    public static void main(String[] args) {
        launch();
    }
}
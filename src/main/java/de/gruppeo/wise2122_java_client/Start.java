package de.gruppeo.wise2122_java_client;

import de.gruppeo.wise2122_java_client.controller.CLobby;
import de.gruppeo.wise2122_java_client.controller.CGame;
import de.gruppeo.wise2122_java_client.controller.CQuiz;
import de.gruppeo.wise2122_java_client.controller.CResult;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Timer;

public class Start extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("fxml/logIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);

        // Setzt Fensterparameter
        stage.setTitle("Quizzz");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Wird nach Programmende ausgeführt
        stage.setOnCloseRequest(event -> {

            // Liste aller im Spiel verwendeten Timer
            ArrayList<Timer> timers = new ArrayList<>();
            timers.add(CGame.gameTimer);
            timers.add(CLobby.lobbyTimer);
            timers.add(CQuiz.quizTimer);
            timers.add(CQuiz.questionTimer);
            timers.add(CResult.resultTimer);
            timers.add(CResult.showResultTimer);

                for (Timer timer : timers) {
                    if (timer != null) {
                        timer.cancel();
                    }
                }
            }
        );
    }

    public static void main(String[] args) {
        launch();
    }
}
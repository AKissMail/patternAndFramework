package de.gruppeo.wise2122_java_client;

import de.gruppeo.wise2122_java_client.controllers.CLobby;
import de.gruppeo.wise2122_java_client.controllers.CGame;
import de.gruppeo.wise2122_java_client.controllers.CQuiz;
import de.gruppeo.wise2122_java_client.controllers.CResult;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.models.MConfig;
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
                    CQuiz.quizTimer.cancel();
                    CResult.resultTimer.cancel();

                    // Löscht das erstellte Spiel
                    Connection con = new Connection("/games/update");
                    con.deleteGame(MConfig.getInstance().getRegisteredGameID());
                } catch (Exception e) {}
            }
        );
    }

    public static void main(String[] args) {
        launch();
    }
}
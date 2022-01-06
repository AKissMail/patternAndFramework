package de.gruppeo.wise2122_java_client;

import de.gruppeo.wise2122_java_client.helpers.Configuration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

    // Ermöglicht Zugriff auf Löschmethode
    Configuration config = new Configuration();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("fxml/logIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Quizzz");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        // Wird nach Programmende ausgeführt
        stage.setOnCloseRequest(event ->
                config.deleteFile()
        );
    }

    public static void main(String[] args) {
        launch();
    }
}
package de.gruppeo.wise2122_java_client;

import de.gruppeo.wise2122_java_client.helpers.Configuration;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Start extends Application {

    Configuration config = new Configuration();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("logIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Quizzz");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(event ->
                // Überschreibt Token in Config-Datei mit leerer Zeichenkette
                config.writeProperty("privateToken", "")
        );
    }

    public static void main(String[] args) {
        launch();
    }
}
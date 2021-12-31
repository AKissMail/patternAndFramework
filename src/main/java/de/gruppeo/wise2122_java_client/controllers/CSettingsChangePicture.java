package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;

public class CSettingsChangePicture {

    private ViewLoader loader = new ViewLoader();
    private FileChooser fileChooser;
    private File file;

    @FXML private Circle circle_settings_picture;

    @FXML
    public void initialize() throws MalformedURLException {
        circle_settings_picture.setFill(new ImagePattern(loader.loadImage("PICTURE")));
    }

    public void onAction_uploadPicture(ActionEvent event) throws MalformedURLException {
        // Stage wird für den FileChooser benötigt
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Initialisierung des FileChoosers zur Auswahl eines Dateipfads
        fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle ein Profilbild aus");
        this.file = fileChooser.showOpenDialog(stage);

        // Speichert Dateipfad lokal in Pref-XML
        loader.saveData("PICTURE", file.getPath());

        // Setzt Image in Kreis ein
        circle_settings_picture.setFill(new ImagePattern(loader.loadImage("PICTURE")));
    }
}
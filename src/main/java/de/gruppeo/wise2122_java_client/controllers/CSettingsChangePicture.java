package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class CSettingsChangePicture implements Initializable {

    private ViewLoader loader;
    private FileChooser fileChooser;
    private File file;

    @FXML private Circle circle_settings_picture;

    public CSettingsChangePicture() {
        loader = new ViewLoader();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            circle_settings_picture.setFill(new ImagePattern(loader.loadImage("PICTURE")));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Zeigt Maske zur Auswahl
     * eines neuen Profilbildes an.
     *
     * @param event
     * @throws MalformedURLException
     */
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
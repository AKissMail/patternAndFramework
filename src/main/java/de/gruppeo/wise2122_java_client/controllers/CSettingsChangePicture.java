package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.Converter;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MConfig;
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
    public void onAction_uploadPicture(ActionEvent event) throws Exception {
        String playername = MConfig.getInstance().getUsername();

        // Stage wird für den FileChooser benötigt
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Initialisierung des FileChoosers zur Auswahl eines Dateipfads
        fileChooser = new FileChooser();
        fileChooser.setTitle("Wähle ein Profilbild aus");
        this.file = fileChooser.showOpenDialog(stage);

        // Speichert enkodiertes Image in Datenbank
        Connection upload = new Connection("/player/uploadthumbnail");
        upload.uploadThumbnail(Converter.encodeImage(file.getPath()), playername);

        // Erhält enkodiertes Image aus Datenbank
        Connection download = new Connection("/player/getthumbnailbyname?playername=" + playername);
        System.out.println("Bild: " + Converter.decodeImage(download.getServerResponse()));

        // Setzt Image in Kreis ein
        //circle_settings_picture.setFill(new ImagePattern(Converter.decodeImage(download.getServerResponse()));
    }
}
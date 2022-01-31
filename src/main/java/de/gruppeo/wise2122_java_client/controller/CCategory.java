package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.helper.Validation;
import de.gruppeo.wise2122_java_client.model.MConfig;
import de.gruppeo.wise2122_java_client.model.MRounds;
import de.gruppeo.wise2122_java_client.parser.PCategory;
import de.gruppeo.wise2122_java_client.helper.Loader;
import de.gruppeo.wise2122_java_client.model.MCategory;
import de.gruppeo.wise2122_java_client.parser.PRounds;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class CCategory implements Initializable {
    Loader loader;
    Validation validation;
    PCategory mapperCategory;
    PRounds mapperRounds;

    @FXML private BorderPane mainPane;
    @FXML private ComboBox combo_category_selectedCategory;
    @FXML private ComboBox combo_category_selectedRounds;
    @FXML private Button button_category_showLobby;

    /**
     * Initialisiert das ViewLoader-Objekt für
     * den Maskenwechsel und etabliert eine neue
     * Serververbindung zur Bereitstellung der
     * Quiz-Kategorien.
     */
    public CCategory() {
        loader = new Loader();
        validation = new Validation();
        mapperCategory = new PCategory(new Connection("/category"));
        mapperRounds = new PRounds(new Connection("/rounds"));
    }

    /**
     * Wird beim Aufruf der aktuellen Maske
     * ausgeführt. Befüllt das Menü mit Kategorien
     * und Spielrunden.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Aktiviert oder deaktiviert Buttons
        validation.disableButtons(new Button[] {button_category_showLobby}, setLoadedValues());

        // Befüllt Auswahlmenü mit Kategorien
        for (MCategory category : mapperCategory.getCategories()) {
            combo_category_selectedCategory.getItems().add(category.getCategoryname());
        }

        // Befüllt Auswahlmenü mit Rundenzahlen
        for (MRounds rounds : mapperRounds.getRounds()) {
            combo_category_selectedRounds.getItems().add(rounds.getRounds());
        }

        // Setzt Startwert für Anzahl der Spielrunden
        combo_category_selectedRounds.getSelectionModel().select(MConfig.getInstance().getIndexRounds());
        MConfig.getInstance().setRounds(combo_category_selectedRounds.getItems().get(MConfig.getInstance().getIndexRounds()));

        // Gibt die ausgewählte Kategorie zurück
        combo_category_selectedCategory.getSelectionModel().selectedItemProperty().addListener((options, oldCategory, newCategory) -> {

            // Schreibt ausgewählte Kategorie in Objekt
            MConfig.getInstance().setCategory(newCategory);
            validation.disableButtons(new Button[] {button_category_showLobby}, false);
        });

        // Gibt die ausgewählte Anzahl der zu spielenden Fragen zurück
        combo_category_selectedRounds.getSelectionModel().selectedItemProperty().addListener((options, oldRounds, newRounds) -> {

            // Schreibt ausgewählte Rundenzahl in Objekt
            MConfig.getInstance().setIndexRounds(combo_category_selectedRounds.getSelectionModel().getSelectedIndex());
            MConfig.getInstance().setRounds(newRounds);

        });
    }

    /**
     * Liest das Config-Objekt aus und
     * versucht GUI-Zustände zu setzen.
     */
    private boolean setLoadedValues() {
        boolean disableButton = true;

        Object comboCategory = MConfig.getInstance().getCategory();
        Object comboRounds = MConfig.getInstance().getRounds();

        if (comboCategory != null || comboRounds != null) {
            combo_category_selectedCategory.getSelectionModel().select(comboCategory);
            combo_category_selectedRounds.getSelectionModel().select(comboRounds);
            disableButton = false;
        }
        return disableButton;
    }

    /**
     * Zeigt den Wartebereich an.
     */
    public void onMouseClicked_showLobby() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("lobby"));
        stage.show();
    }

    /**
     * Wird beim Klick auf "Zurück"-Button ausgeführt
     * und wechselt die Maske zum Hauptmenü.
     */
    public void onMouseClicked_back() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
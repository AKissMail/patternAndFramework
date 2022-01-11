package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.models.MConfig;
import de.gruppeo.wise2122_java_client.models.MRounds;
import de.gruppeo.wise2122_java_client.parsers.PCategory;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MCategory;
import de.gruppeo.wise2122_java_client.parsers.PRounds;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class CCategory implements Initializable {
    ViewLoader loader;
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
     *
     * @throws Exception
     */
    public CCategory() throws Exception {
        loader = new ViewLoader();
        mapperCategory = new PCategory(new Connection("/category"));
        mapperRounds = new PRounds(new Connection("/rounds"));

        // Ändert Status auf ONLINE
        System.out.println(new Connection("/player/changeplayerstatus").changePlayerStatus("online"));
    }

    /**
     * Wird beim Aufruf der aktuellen Maske
     * ausgeführt. Befüllt das Menü mit Kategorien
     * und Spielrunden.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Aktiviert oder deaktiviert Buttons
        disableButtons(new Button[] {button_category_showLobby}, setLoadedValues());

        // Befüllt Auswahlmenü mit Kategorien
        for (MCategory category : mapperCategory.getList()) {
            combo_category_selectedCategory.getItems().add(category.getCategoryname());
        }

        // Befüllt Auswahlmenü mit Rundenzahlen
        for (MRounds rounds : mapperRounds.getList()) {
            combo_category_selectedRounds.getItems().add(rounds.getRounds() + " Runden");
        }

        // Setzt Startwert für Anzahl der Spielrunden
        combo_category_selectedRounds.getSelectionModel().select(MConfig.getInstance().getIndexRounds());

        // Gibt die ausgewählte Kategorie zurück
        combo_category_selectedCategory.getSelectionModel().selectedItemProperty().addListener((options, oldCategory, newCategory) -> {

            // Schreibt ausgewählte Kategorie in Objekt
            MConfig.getInstance().setCategory(newCategory);
            disableButtons(new Button[] {button_category_showLobby}, false);
        });

        // Gibt die ausgewählte Anzahl der zu spielenden Fragen zurück
        combo_category_selectedRounds.getSelectionModel().selectedItemProperty().addListener((options, oldRounds, newRounds) -> {

            // Schreibt ausgewählte Rundenzahl in Objekt
            MConfig.getInstance().setIndexRounds(combo_category_selectedRounds.getSelectionModel().getSelectedIndex());
            MConfig.getInstance().setRounds(newRounds);
        });
    }

    /**
     * Aktiviert oder deaktiviert
     * die übergebenen Buttons.
     *
     * @param buttons
     * @param disable
     */
    private void disableButtons(Button[] buttons, boolean disable) {
        for (Button button : buttons) {
            button.setDisable(disable);
        }
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
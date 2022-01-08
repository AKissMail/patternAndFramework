package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Configuration;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.models.MConfiguration;
import de.gruppeo.wise2122_java_client.parsers.PCategory;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MCategory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class CCategory {
    ViewLoader loader;
    PCategory mapper;

    @FXML private BorderPane mainPane;
    @FXML private ComboBox combo_category_selectedCategory;
    @FXML private ComboBox combo_category_selectedRounds;
    @FXML private Button button_category_chooseOpponent;
    @FXML private Button button_category_joinQuiz;

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
        mapper = new PCategory(new Connection("/category"));
    }

    /**
     * Wird beim Aufruf der aktuellen Maske
     * ausgeführt. Befüllt das Menü mit Kategorien.
     */
    @FXML public void initialize() {
        // Deaktiviert Buttons
        disableButtons(false);

        // Liest die XML-Config aus und versucht Werte zu setzen
        Object categoryCombo = new Configuration().readConfiguration().getCategory();
        Object roundsCombo = new Configuration().readConfiguration().getRounds();

        if (categoryCombo != null || roundsCombo != null) {
            combo_category_selectedCategory.getSelectionModel().select(categoryCombo);
            combo_category_selectedRounds.getSelectionModel().select(roundsCombo);
        } else {
            disableButtons(true);
        }

        // Befüllt Auswahlmenü mit Kategorien
        for (MCategory category : mapper.getList()) {
            combo_category_selectedCategory.getItems().add(category.getCategoryname());
        }

        // Befüllt Auswahlmenü mit Rundenzahlen und setzt Standardwert
        combo_category_selectedRounds.getItems().add("10 Runden");
        combo_category_selectedRounds.getItems().add("20 Runden");

        combo_category_selectedRounds.getSelectionModel().selectFirst();

        // Schreibt ausgewählte Kategorie in Config
        MConfiguration.getInstance().setRounds(combo_category_selectedRounds.getSelectionModel().getSelectedItem());
        new Configuration().writeConfiguration();

        // Gibt die ausgewählte Kategorie zurück
        combo_category_selectedCategory.getSelectionModel().selectedItemProperty().addListener((options, oldCategory, newCategory) -> {
            disableButtons(false);

            // Schreibt ausgewählte Kategorie in Config
            MConfiguration.getInstance().setCategory(newCategory);
            new Configuration().writeConfiguration();
        });

        // Gibt die ausgewählte Anzahl der zu spielenden Fragen zurück
        combo_category_selectedRounds.getSelectionModel().selectedItemProperty().addListener((options, oldRounds, newRounds) -> {
            // Schreibt ausgewählte Rundenzahl in Config
            MConfiguration.getInstance().setRounds(newRounds);
            new Configuration().writeConfiguration();
        });
    }

    private void disableButtons(Boolean disable) {
        button_category_joinQuiz.setDisable(disable);
        button_category_chooseOpponent.setDisable(disable);
    }

    /**
     * Wird beim Klick auf "Zurück"-Button ausgeführt
     * und wechselt die Maske zum Hauptmenü.
     */
    public void onMouseClicked_back() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("fxml/main"));
        stage.show();
    }

    /**
     * Wird beim Klick auf "Gegner wählen" ausgeführt
     * und wechselt die Maske zur Gegnerwahl.
     */
    public void onMouseClicked_selectOpponent() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("fxml/opponent"));
        stage.show();
    }

    /**
     * Wird beim Klick auf "Quiz beitreten" ausgeführt
     * und wechselt die Maske zur Lobby.
     */
    public void onMouseClicked_joinQuiz() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("fxml/lobby"));
        stage.show();
    }
}
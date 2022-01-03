package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Configuration;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.parsers.PCategory;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MCategory;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;

public class CCategory {
    ViewLoader loader;
    PCategory mapper;

    @FXML private BorderPane mainPane;
    @FXML private ComboBox combo_category_selectedCategory;
    @FXML private ToggleGroup radioGroup_category_questions;
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
        Thread thread = new Thread(() -> {
            for (MCategory category : mapper.getList()) {
                combo_category_selectedCategory.getItems().add(category.getCategoryname());
            }
        });
        thread.start();

        // Gibt die ausgewählte Kategorie zurück
        combo_category_selectedCategory.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                button_category_chooseOpponent.setDisable(false);
                button_category_joinQuiz.setDisable(false);
            }
            System.out.println(newValue);
        });

        // Gibt die ausgewählte Anzahl der zu spielenden Fragen zurück
        radioGroup_category_questions.selectedToggleProperty().addListener((obserableValue, oldToggle, newToggle) -> {
            if (radioGroup_category_questions.getSelectedToggle() != null) {
                System.out.println("Value: " + radioGroup_category_questions.getSelectedToggle());
            }
        });
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

    /**
     * Wird beim Klick auf "Gegner wählen" ausgeführt
     * und wechselt die Maske zur Gegnerwahl.
     */
    public void onMouseClicked_selectOpponent() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("opponent"));
        stage.show();
    }

    /**
     * Wird beim Klick auf "Quiz beitreten" ausgeführt
     * und wechselt die Maske zur Lobby.
     */
    public void onMouseClicked_joinQuiz() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("lobby"));
        stage.show();
    }
}
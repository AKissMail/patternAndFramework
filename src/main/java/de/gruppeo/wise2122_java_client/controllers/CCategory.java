package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.Connection;
import de.gruppeo.wise2122_java_client.parsers.PCategory;
import de.gruppeo.wise2122_java_client.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MCategory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CCategory {
    ViewLoader loader;
    Connection connection;
    PCategory mapper;

    @FXML private BorderPane mainPane;
    @FXML private ComboBox combo_category_selectedCategory;
    @FXML private RadioButton radio_category_10Questions;
    @FXML private RadioButton radio_category_20Questions;

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
        connection = new Connection("/category" ,"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMaXNhIiwiaWF0IjoxNjQwNzc2ODU2LCJleHAiOjE2NDEzODE2NTZ9.K2rgEl5mS2AiZfuIDtXlHblcIGxxnqVNtiOlbpQ-Rr6S7KlDkXtcbF7HogYs3tH7eWIcURL_4KVnSVye09dk4Q");
        mapper = new PCategory(connection);
    }

    /**
     * Wird beim Aufruf der aktuellen Maske
     * ausgeführt. Befüllt das Menü mit Kategorien.
     */
    @FXML public void initialize() throws Exception {
        for (MCategory category : mapper.getList()) {
            combo_category_selectedCategory.getItems().add(category.getCategoryname());
        }
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
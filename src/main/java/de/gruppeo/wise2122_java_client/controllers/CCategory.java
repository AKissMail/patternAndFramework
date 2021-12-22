package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.Connection;
import de.gruppeo.wise2122_java_client.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MCategory;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;

public class CCategory {

    ViewLoader loader;
    MCategory category;

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
        this.loader = new ViewLoader();

        Connection connection = new Connection("/category", "GET" ,"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYXgiLCJpYXQiOjE2NDAwMDE3ODQsImV4cCI6MTY0MDYwNjU4NH0.nHm43HtiJdS3UG_ccxw-sg6DPsq-4LpQo8yYbMy4BMDCRHIbcqNECjlyl3Y2udL1bbhOQfLwQwgOy4epnN1NYQ");
        this.category = new MCategory(connection.getServerResponse());
    }

    /**
     * Wird beim Aufruf der aktuellen Maske
     * ausgeführt. Befüllt das Menü mit Kategorien.
     */
    @FXML public void initialize() {
        for (String category : category.getCategories()) {
            combo_category_selectedCategory.getItems().add(category);
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
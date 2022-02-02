package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.helper.Loader;
import de.gruppeo.wise2122_java_client.model.MHighscore;
import de.gruppeo.wise2122_java_client.parser.PHighscore;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class CHighscore implements Initializable {
    private final Loader loader;

    @FXML private BorderPane mainPane;
    @FXML private TableView<Object> table_highscore_points;
    @FXML private TableColumn<MHighscore, Integer> column_highscore_rank;
    @FXML private TableColumn<MHighscore, String> column_highscore_player;
    @FXML private TableColumn<MHighscore, Integer> column_highscore_points;
    @FXML private TableColumn<MHighscore, String> column_highscore_date;

    public CHighscore() {
        loader = new Loader();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadHighscoreData();
    }

    /**
     * Lädt den Highscore vom Server
     * und fügt Daten in die Tabelle ein.
     */
    private void loadHighscoreData() {
        PHighscore mapperHighscore = new PHighscore(new Connection("/highscore"));

        column_highscore_rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        column_highscore_player.setCellValueFactory(new PropertyValueFactory<>("playername"));
        column_highscore_points.setCellValueFactory(new PropertyValueFactory<>("highscorepoints"));
        column_highscore_date.setCellValueFactory(new PropertyValueFactory<>("lastupdate"));

        int rank = 1;

        for (MHighscore score : mapperHighscore.getHighscores()) {
            table_highscore_points.getItems().add(new MHighscore(rank++, score.getPlayername(), score.getHighscorepoints(), score.getLastupdate()));
        }
    }

    /**
     * Setzt den Highscore des angemeldeten Spielers zurück.
     */
    public void onMouseClicked_resetHighscore() {
        Connection con = new Connection("/player/setplayerhighscore");
        con.resetHighscore();

        // Entfernt alle Einträge
        table_highscore_points.getItems().clear();

        // Lädt aktualisierte Daten
        loadHighscoreData();
    }

    /**
     * Zeigt das Hauptmenü an.
     */
    public void onMouseClicked_showMain() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
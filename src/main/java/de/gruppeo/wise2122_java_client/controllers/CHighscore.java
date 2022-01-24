package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.Loader;
import de.gruppeo.wise2122_java_client.models.MHighscore;
import de.gruppeo.wise2122_java_client.parsers.PHighscore;
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
    Loader loader;
    PHighscore mapperHighscore;

    @FXML private BorderPane mainPane;
    @FXML private TableView<Object> table_highscore_points;
    @FXML private TableColumn<MHighscore, Integer> column_highscore_rank;
    @FXML private TableColumn<MHighscore, String> column_highscore_player;
    @FXML private TableColumn<MHighscore, Integer> column_highscore_points;
    @FXML private TableColumn<MHighscore, String> column_highscore_date;

    public CHighscore() {
        loader = new Loader();
        mapperHighscore = new PHighscore(new Connection("/highscore"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Stimmt mit MPlayer-Objekt überein - baut Brücke, um Spalten zu befüllen
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
     * Zeigt die Spielhistorie an.
     */
    public void onMouseClicked_showGameHistory() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("history"));
        stage.show();
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
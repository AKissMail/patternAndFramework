package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MHighscore;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import de.gruppeo.wise2122_java_client.parsers.PHighscore;
import de.gruppeo.wise2122_java_client.parsers.PPlayer;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;

public class CHighscore implements Initializable {
    ViewLoader loader;
    PPlayer mapperPlayer;
    PHighscore mapperHighscore;

    @FXML private BorderPane mainPane;
    @FXML private Label label_highscore_gameRounds;
    @FXML private TableView<Object> table_highscore_points;
    @FXML private TableColumn<MPlayer, Integer> column_highscore_rank;
    @FXML private TableColumn<MPlayer, String> column_highscore_player;
    @FXML private TableColumn<MHighscore, Integer> column_highscore_points;

    public CHighscore() throws Exception {
        loader = new ViewLoader();
        mapperPlayer = new PPlayer(new Connection("/player/all"));
        mapperHighscore = new PHighscore(new Connection("/highscore"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Stimmt mit MPlayer-Objekt überein - baut Brücke, um Spalten zu befüllen
        column_highscore_rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        column_highscore_player.setCellValueFactory(new PropertyValueFactory<>("username"));
        column_highscore_points.setCellValueFactory(new PropertyValueFactory<>("highscorepoints"));

        // Label zur Anzeige der Anzahl der gespielten Runden
        label_highscore_gameRounds.setText("Du hast 0 Runden gespielt");

        int rank = 1;

        /*for (int i = 0; i < mapperPlayer.getPlayers().size(); i++) {
            table_highscore_points.getItems().add(new MPlayer(i, mapperPlayer.getPlayers().get(i).getUsername()));
            table_highscore_points.getItems().add(new MHighscore(mapperHighscore.getHighscores().get(i).getHighscorepoints()));
        }*/

        /* Befüllt Spalten mit Werten des MPlayer-Objekts
        for (MPlayer player : mapperPlayer.getPlayers()) {
            table_highscore_points.getItems().add(new MPlayer(rank++, player.getUsername(), player.getHighscore().getHighscorepoints()));
        }*/

        /*for (MHighscore score : mapperHighscore.getHighscores()) {
            table_highscore_points.getItems().add(new MHighscore(score.getHighscorepoints()));
        }*/
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
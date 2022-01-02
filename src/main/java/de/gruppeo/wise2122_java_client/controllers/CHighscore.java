package de.gruppeo.wise2122_java_client.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.gruppeo.wise2122_java_client.helpers.Configuration;
import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.ViewLoader;
import de.gruppeo.wise2122_java_client.models.MPlayer;
import de.gruppeo.wise2122_java_client.parsers.POpponent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import org.springframework.data.relational.core.sql.In;

import java.net.URL;
import java.util.ResourceBundle;

public class CHighscore implements Initializable {
    ViewLoader loader;
    Connection connection;
    Configuration config;
    POpponent mapper;

    @FXML private BorderPane mainPane;
    @FXML private Label label_highscore_gameRounds;
    @FXML private TableView<MPlayer> table_highscore_points;
    @FXML private TableColumn<MPlayer, String> column_highscore_player;
    @FXML private TableColumn<MPlayer, Integer> column_highscore_points;
    @FXML private TableColumn<MPlayer, Integer> column_highscore_rank;

    public CHighscore() throws Exception {
        loader = new ViewLoader();
        config = new Configuration();
        connection = new Connection("/player/all", config.readProperty("privateToken"));
        mapper = new POpponent(connection);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Stimmt mit MPlayer-Objekt überein - baut Brücke, um Spalten zu befüllen
        column_highscore_rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        column_highscore_player.setCellValueFactory(new PropertyValueFactory<>("username"));
        column_highscore_points.setCellValueFactory(new PropertyValueFactory<>("currentscore"));

        // Label zur Anzeige der Anzahl der gespielten Runden
        label_highscore_gameRounds.setText("Du hast 0 Runden gespielt");

        // Befüllt Spalten mit Werten des MPlayer-Objekts
        for (MPlayer opponent : mapper.getList()) {
            table_highscore_points.getItems().add(new MPlayer(1, opponent.getUsername(), opponent.getCurrentscore()));
        }
    }

    /**
     *
     */
    public void onMouseClicked_showMain() {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("main"));
        stage.show();
    }
}
package de.gruppeo.wise2122_java_client.controllers;

import de.gruppeo.wise2122_java_client.helpers.Connection;
import de.gruppeo.wise2122_java_client.helpers.Loader;
import de.gruppeo.wise2122_java_client.models.MConfig;
import de.gruppeo.wise2122_java_client.models.MHistory;
import de.gruppeo.wise2122_java_client.parsers.PHistory;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;

public class CHistory implements Initializable {
    Loader loader;
    PHistory mapperHistory;

    @FXML private BorderPane mainPane;
    @FXML private TableView<Object> table_history_gameHistory;
    @FXML private TableColumn<MHistory, Integer> column_history_id;
    @FXML private TableColumn<MHistory, String> column_history_player;
    @FXML private TableColumn<MHistory, Integer> column_history_playerPoints;
    @FXML private TableColumn<MHistory, Integer> column_history_opponentPoints;
    @FXML private TableColumn<MHistory, String> column_history_category;
    @FXML private TableColumn<MHistory, Integer> column_history_rounds;
    @FXML private TableColumn<MHistory, Double> column_history_average;

    public CHistory() {
        loader = new Loader();
        mapperHistory = new PHistory(new Connection("/games/history?playername=" + MConfig.getInstance().getUsername()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        column_history_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_history_player.setCellValueFactory(new PropertyValueFactory<>("playername"));
        column_history_playerPoints.setCellValueFactory(new PropertyValueFactory<>("playerscore"));
        column_history_opponentPoints.setCellValueFactory(new PropertyValueFactory<>("opponentscore"));
        column_history_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        column_history_rounds.setCellValueFactory(new PropertyValueFactory<>("rounds"));
        column_history_average.setCellValueFactory(new PropertyValueFactory<>("average"));

        for (MHistory item : mapperHistory.getHistories()) {
            table_history_gameHistory.getItems().add(new MHistory(item.getId(), item.getPlayername(), item.getPlayerscore(), item.getOpponentscore(), item.getCategory(), item.getRounds(), item.getAverage()));
        }
    }

    /**
     * Setzt die Spielhistorie zurück.
     */
    public void onMouseClicked_resetHistory() {
        // @TODO Muss noch implementiert werden
    }

    /**
     * Zeigt den Highscore an.
     */
    public void onMouseClicked_showHighscore() {
        // Wechselt Maske
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.setScene(loader.getScene("highscore"));
        stage.show();
    }
}
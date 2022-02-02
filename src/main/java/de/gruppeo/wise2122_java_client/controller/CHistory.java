package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.helper.Connection;
import de.gruppeo.wise2122_java_client.helper.Loader;
import de.gruppeo.wise2122_java_client.model.MConfig;
import de.gruppeo.wise2122_java_client.model.MHistory;
import de.gruppeo.wise2122_java_client.parser.PHistory;
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
    private final Loader loader;

    @FXML private BorderPane mainPane;
    @FXML private TableView<Object> table_history_gameHistory;
    @FXML private TableColumn<MHistory, Integer> column_history_id;
    @FXML private TableColumn<MHistory, String> column_history_player;
    @FXML private TableColumn<MHistory, Integer> column_history_playerScore;
    @FXML private TableColumn<MHistory, Integer> column_history_opponentScore;
    @FXML private TableColumn<MHistory, String> column_history_category;
    @FXML private TableColumn<MHistory, Integer> column_history_rounds;
    @FXML private TableColumn<MHistory, Double> column_history_average;

    public CHistory() {
        loader = new Loader();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadHistoryData();
    }

    /**
     * Lädt die Spielhistorie vom Server
     * und fügt Daten in die Tabelle ein.
     */
    private void loadHistoryData() {
        PHistory mapperHistory = new PHistory(new Connection("/games/history?playername=" + MConfig.getInstance().getUsername()));

        column_history_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        column_history_player.setCellValueFactory(new PropertyValueFactory<>("playername"));
        column_history_playerScore.setCellValueFactory(new PropertyValueFactory<>("playerscore"));
        column_history_opponentScore.setCellValueFactory(new PropertyValueFactory<>("opponentscore"));
        column_history_category.setCellValueFactory(new PropertyValueFactory<>("categoryname"));
        column_history_rounds.setCellValueFactory(new PropertyValueFactory<>("rounds"));
        column_history_average.setCellValueFactory(new PropertyValueFactory<>("average"));

        for (MHistory item : mapperHistory.getHistories()) {
            table_history_gameHistory.getItems().add(new MHistory(item.getId(), item.getPlayername(), item.getPlayerscore(), item.getOpponentscore(), item.getCategoryname(), item.getRounds(), item.getAverage()));
        }
    }

    /**
     * Setzt die Spielhistorie zurück.
     */
    public void onMouseClicked_resetHistory() {
        Connection con = new Connection("/games/historydeletebytoken");
        con.resetHistory();

        // Entfernt alle Einträge
        table_history_gameHistory.getItems().clear();

        // Lädt aktualisierte Daten
        loadHistoryData();
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
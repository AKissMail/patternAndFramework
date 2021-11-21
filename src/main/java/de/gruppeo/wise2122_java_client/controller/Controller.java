package de.gruppeo.wise2122_java_client.controller;

import de.gruppeo.wise2122_java_client.Start;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import java.io.IOException;

public class Controller {
    @FXML
    private Button button_signUp;

    @FXML
    public void onClickButton_signUp() throws IOException {
        Parent root = FXMLLoader.load(Start.class.getResource("signUp.fxml"));
        Scene scene = new Scene(root);

    }
}
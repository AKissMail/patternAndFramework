package de.gruppeo.wise2122_java_client.controllers;

import javafx.fxml.FXML;
import java.io.IOException;

public class CLogIn extends Controller {

    @FXML
    public void onClickButton_signUp() throws IOException {
        loadScene("signUp");
    }

    @FXML
    public void onClickButton_logIn() throws IOException {
        loadScene("main");
    }
}
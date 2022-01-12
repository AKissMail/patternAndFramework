package de.gruppeo.wise2122_java_server.request;

import lombok.Data;

@Data
public class HighscoreRequest {

    private String token;

    private Integer playerHighscore;

}

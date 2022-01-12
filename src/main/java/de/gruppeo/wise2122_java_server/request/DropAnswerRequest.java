package de.gruppeo.wise2122_java_server.request;

import lombok.Data;

@Data
public class DropAnswerRequest {

    private Long gamesid;
    private String username;
    private boolean answers;
    private int time;

}

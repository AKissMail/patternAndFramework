package de.gruppeo.wise2122_java_server.request;

import lombok.Data;


@Data
public class UpdateGameRequest {

    private Long gamesid;

    private String playerone;

    private String playertwo;

    private String status;

}

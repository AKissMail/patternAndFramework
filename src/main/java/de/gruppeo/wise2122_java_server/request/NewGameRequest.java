package de.gruppeo.wise2122_java_server.request;

import lombok.Data;

@Data
public class NewGameRequest {

    private String username;

    private String category;

    private Integer rounds;


}

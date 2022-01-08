package de.gruppeo.wise2122_java_server.request;

import lombok.Data;

@Data
public class StatusRequest {

    private String token;

    private String status;

}

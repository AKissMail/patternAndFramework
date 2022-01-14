package de.gruppeo.wise2122_java_server.request;

import lombok.Data;

@Data
public class UpdatePasswordRequest {

    private String playername;

    private String oldpassword;

    private String newpassword;

}

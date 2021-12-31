package de.gruppeo.wise2122_java_client.models;

public class MPlayer {

    private String privateToken;
    private String username;

    public MPlayer() {}

    public MPlayer(String privateToken, String username) {
        this.privateToken = privateToken;
        this.username = username;
    }

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

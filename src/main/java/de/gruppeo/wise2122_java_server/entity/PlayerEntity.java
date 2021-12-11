package de.gruppeo.wise2122_java_server.entity;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;

@Entity(name = "player")
@Table(name = "player", schema = "mibquizzz")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playerid", nullable = false, updatable = false)
    private Long playerid;

    @Column(name = "username", unique = true, updatable = false)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "currentscore")
    @NumberFormat(pattern = "0000", style = NumberFormat.Style.NUMBER)
    private Integer currentscore;

    public Integer getCurrentscore() {
        return currentscore;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    // private Image thumbnail;

    // public enum currentstatus {offline, online, quizzing, searching}

    @Column(name = "currentstatus")
    public String currentstatus;

    public String getCurrentStatus() {
        return currentstatus;
    }

    public Long getPlayerid() {
        return playerid;
    }

    public void setPlayerid(Long playerid) {
        this.playerid = playerid;
    }
}

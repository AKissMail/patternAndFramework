package de.gruppeo.wise2122_java_server.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;


@Entity(name = "highscore")
@Table(name = "highscore", schema = "mibquizzz")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class HighscoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizhighscoreid", nullable = false, updatable = false)
    private Long quizhighscoreid;

    @Column(name = "highscorepoints")
    public Integer highscorepoints;

    @UpdateTimestamp
    @Column(name = "lastupdate", nullable = false)
    private LocalDateTime lastupdate;

   public LocalDateTime getLastupdate() {
        return lastupdate;
    }

    public Integer getHighscorepoints() {
        return highscorepoints;
    }

    public Long getQuizhighscoreid() {
        return quizhighscoreid;
    }

}
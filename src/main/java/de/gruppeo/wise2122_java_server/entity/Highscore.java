package de.gruppeo.wise2122_java_server.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;


@Entity(name = "highscore")
@Table(schema = "mibquizzz")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Highscore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizhighscoreid", nullable = false)
    private Long quizhighscoreid;

    @Column(name = "highscore")
    public Integer highscore;

    @UpdateTimestamp
    @Column(name = "lastupdate", nullable = false)
    private LocalDateTime lastupdate;

   public LocalDateTime getLastupdate() {
        return lastupdate;
    }

    public Integer getHighscore() {
        return highscore;
    }

    public Long getQuizhighscoreid() {
        return quizhighscoreid;
    }

    public void setQuizhighscoreid(Long quizhighscoreid) {
        this.quizhighscoreid = quizhighscoreid;
    }
}
package de.gruppeo.wise2122_java_server.model;

import javax.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity(name = "highscore")
@Table(schema = "mibquizzz")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class HighscoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizhighscoreid", nullable = false, updatable = false)
    private Long quizhighscoreid;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "player_playerid", unique = true)
    @JsonBackReference
    private PlayerEntity player;

    @Column(name = "highscorepoints")
    public Integer highscorepoints;

    @UpdateTimestamp
    @Column(name = "lastupdate", nullable = false)
    private LocalDateTime lastupdate;

}
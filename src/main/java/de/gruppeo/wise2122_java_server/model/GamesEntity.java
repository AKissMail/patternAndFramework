package de.gruppeo.wise2122_java_server.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity(name = "games")
@Table(schema = "mibquizzz")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GamesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gameid", nullable = false, updatable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "gamestatus")
    private Gamestatus gamestatus;

    @JsonIgnore
    @Column(updatable = false)
    private LocalDateTime created;

    // PLAYERONE
    @ManyToOne
    @JoinColumn(name = "playerone")
    private PlayerEntity playerone;
    @Column(columnDefinition = "integer default 0")
    private Integer playeronescore;
    @Column(columnDefinition = "integer default 0")
    private Integer playeroneround;
    @JsonIgnore
    private LocalDateTime playerOneLastRequestTime;

    // PLAYERTWO
    @ManyToOne
    @JoinColumn(name = "playertwo")
    private PlayerEntity playertwo;
    @Column(columnDefinition = "integer default 0")
    private Integer playertwoscore;
    @Column(columnDefinition = "integer default 0")
    private Integer playertworound;
    @JsonIgnore
    private LocalDateTime playerTwoLastRequestTime;

    @ManyToOne
    @JoinColumn
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn
    private RoundsEntity rounds;

    @ManyToMany
    @JoinTable(name = "games_questions",
            joinColumns = @JoinColumn(name = "games_gameid", referencedColumnName = "gameid"),
            inverseJoinColumns = @JoinColumn(name = "questions_quizquestionid", referencedColumnName = "quizquestionid"))
    @ToString.Exclude
    private List<QuestionsEntity> questions = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        created = LocalDateTime.now();
    }
}

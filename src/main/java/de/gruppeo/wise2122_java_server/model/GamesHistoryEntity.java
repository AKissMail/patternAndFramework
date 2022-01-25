package de.gruppeo.wise2122_java_server.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@Entity(name = "gamesHistory")
@Table(schema = "mibquizzz")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class GamesHistoryEntity {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gamehistoryid", nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String playername;

    @ManyToOne(optional = false)
    @JoinColumn(name = "player", nullable = false, updatable = false)
    private PlayerEntity player;

    @ToString.Include
    @Column(nullable = false, updatable = false)
    private Integer playerscore;

    @ToString.Include
    @Column(nullable = false, updatable = false)
    private Integer opponentscore;

    @Column(nullable = false, updatable = false)
    private String categoryname;

    @Column(nullable = false, updatable = false)
    private Integer rounds;

}

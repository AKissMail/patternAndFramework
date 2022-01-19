package de.gruppeo.wise2122_java_server.model;


import lombok.*;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "playername", nullable = false, updatable = false)
    private PlayerEntity playername;

    @ToString.Include
    @Column(nullable = false, updatable = false)
    private Integer playerscore;

    @ToString.Include
    @Column(nullable = false, updatable = false)
    private Integer opponentscore;

    @ManyToOne
    @JoinColumn
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn
    private RoundsEntity rounds;

}

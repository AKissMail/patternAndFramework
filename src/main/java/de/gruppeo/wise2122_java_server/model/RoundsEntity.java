package de.gruppeo.wise2122_java_server.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "rounds")
@Table(schema = "mibquizzz")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class RoundsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizroundsid", nullable = false)
    private Long quizroundsid;

    @Column(name = "rounds", unique = true)
    public Integer rounds;

}
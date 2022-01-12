package de.gruppeo.wise2122_java_server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "player")
@Table(name = "player", schema = "mibquizzz")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playerid", nullable = false, updatable = false)
    private Long playerid;

    @Column(name = "username", unique = true, updatable = false)
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "currentscore")
    @NumberFormat(pattern = "0000", style = NumberFormat.Style.NUMBER)
    private Integer currentscore;

    // private Image thumbnail;

    @Enumerated(EnumType.STRING)
    @Column(name = "currentstatus")
    public Currentstatus currentstatus;

    @OneToOne(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    //@JsonManagedReference
    private HighscoreEntity highscore;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PlayerEntity that = (PlayerEntity) o;
        return playerid != null && Objects.equals(playerid, that.playerid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}

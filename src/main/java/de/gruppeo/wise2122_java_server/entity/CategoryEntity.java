package de.gruppeo.wise2122_java_server.entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity(name = "category")
@Table(schema = "mibquizzz")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CategoryEntity {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizcategoryid", nullable = false)
    private Long quizcategoryid;

    @ToString.Include
    @Column(unique = true)
    public String categoryname;

    public void setQuizcategoryid(Long quizcategoryid) {
        this.quizcategoryid = quizcategoryid;
    }
}
package de.gruppeo.wise2122_java_server.entity;

import javax.persistence.*;

@Entity(name = "category")
@Table(schema = "mibquizzz")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizcategoryid", nullable = false)
    private Long quizcategoryid;

    @Column(unique = true)
    public String categoryname;

    public String getCategoryname() {
        return categoryname;
    }

    public Long getQuizcategoryid() {
        return quizcategoryid;
    }

    public void setQuizcategoryid(Long quizcategoryid) {
        this.quizcategoryid = quizcategoryid;
    }
}
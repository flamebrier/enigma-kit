package com.pri.aa.enigma.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "enigma")
public class Enigma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EnigmaType enigmaType;
    @Column(length = 1000)
    private String description;
    @Size(min = 1, max = 10)
    private Short complexity;
    @ManyToMany
    private List<Kit> kits;
    @ManyToOne
    private User author;
    private Boolean isPublic;

    public Enigma() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnigmaType getEnigmaType() {
        return enigmaType;
    }

    public void setEnigmaType(EnigmaType enigmaType) {
        this.enigmaType = enigmaType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getComplexity() {
        return complexity;
    }

    public void setComplexity(Short complexity) {
        this.complexity = complexity;
    }

    public List<Kit> getKits() {
        return kits;
    }

    public void setKits(List<Kit> kits) {
        this.kits = kits;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }
}
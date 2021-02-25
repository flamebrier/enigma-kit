package com.pri.aa.enigma.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "enigma")
public class Enigma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private EnigmaType enigmaType;
    @Enumerated(EnumType.STRING)
    private KeyType keyType;
    @Column(length = 1000)
    private String description;
    private Short complexity;
    @ManyToMany
    private List<Kit> kits;
    @ManyToOne
    private User author;
    private Boolean isPublic;

    public Enigma() {
        complexity = 1;
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

    public KeyType getKeyType() {
        return keyType;
    }

    public void setKeyType(KeyType keyType) {
        this.keyType = keyType;
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
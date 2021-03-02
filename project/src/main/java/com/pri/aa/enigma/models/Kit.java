package com.pri.aa.enigma.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "kit")
public class Kit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Short rating;
    @ManyToMany
    private List<Enigma> enigmas;
    @ManyToOne
    private User user;
    private Boolean isPublic;
    private String uuid;

    public Kit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getRating() {
        return rating;
    }

    public void setRating(Short rating) {
        this.rating = rating;
    }

    public List<Enigma> getEnigmas() {
        return enigmas;
    }

    public void setEnigmas(List<Enigma> enigmas) {
        this.enigmas = enigmas;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
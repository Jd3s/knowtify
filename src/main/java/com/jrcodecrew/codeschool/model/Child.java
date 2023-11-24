package com.jrcodecrew.codeschool.model;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "child")
public class Child {

    @Id
    @Getter
    private long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_group", nullable = false)
    private AgeGroup ageGroup;


    public Child(User user, AgeGroup ageGroup) {
        this.user = user;
        this.ageGroup = ageGroup;
    }

    public Child() {}

    public User getUser() {
        return user;
    }

    public Child setUser(User user) {
        this.user = user;
        return this;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public Child setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
        return this;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

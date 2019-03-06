package com.user.realm.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Realm {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String name;

    @Length(max = 255)
    private String description;

    @Column(length = 40)
    private String key;

    public Realm() {}

    public Realm(Integer id, String name, String description, String key) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.key = key;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

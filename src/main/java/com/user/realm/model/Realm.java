package com.user.realm.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "realms")
public class Realm {

    @Id
    @GeneratedValue
    @JacksonXmlProperty(isAttribute = true)
    private Long id;

    @Column(unique = true)
    @JacksonXmlProperty(isAttribute = true)
    private String name;

    @Length(max = 255)
    private String description;

    @Column(length = 40)
    private String key;

    public Realm() {
    }

    public Realm(Long id, String name, String description, String key) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.key = key;
    }

    public Realm(String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.key = key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Realm{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}

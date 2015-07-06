package com.gmail.at.irotech.ehcache.beans.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Entity", uniqueConstraints = { @UniqueConstraint(columnNames = "ID"), @UniqueConstraint(columnNames = "EMAIL") })
public class CacheEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(name = "NAME", unique = false, nullable = false, length = 100)
    private String name;

    @Column(name = "SURNAME", unique = false, nullable = false, length = 100)
    private String surname;

    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

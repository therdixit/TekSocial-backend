package com.teksocial.application.models;

import javax.persistence.*;

@Entity
@Table(name="friends")
public class Friends {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;

    @Column(name="status")
    private boolean status;

    public Friends() {

    }

    public Friends(Long id, String name, String details, boolean status) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.status = status;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

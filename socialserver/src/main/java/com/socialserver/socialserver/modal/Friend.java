package com.socialserver.socialserver.modal;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data

public class Friend {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;


    private String details;


    private boolean status;
}
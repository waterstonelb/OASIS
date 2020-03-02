package com.example.demo.po;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "affiliation")
public class Affiliation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

}

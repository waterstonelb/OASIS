package com.example.demo.po;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "affiliation")
public class Affiliation {
    //机构id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //机构名称
    @Column
    private String name;

}

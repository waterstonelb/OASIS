package com.example.demo.po;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "affiliation")
public class Affiliation {
    //机构id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //机构名称
    @Column(nullable = false)
    private String name;

}

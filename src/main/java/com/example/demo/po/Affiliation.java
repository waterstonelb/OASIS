package com.example.demo.po;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
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

    public Affiliation() {
    }
}

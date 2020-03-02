package com.example.demo.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;


@Data
@Entity
@Table(name = "author")
public class Author {

    @Id
    private String id;

    private String affiliation;

    private String name;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}

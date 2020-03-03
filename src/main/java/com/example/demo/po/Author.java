package com.example.demo.po;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "author")
public class Author {

    //作者id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //作者ieee id
    @Column(name = "ieee_id", length = 100)
    private String ieeeId;

    //作者所在机构
    @Column(length = 200)
    private String affiliation;

    //作者姓名
    @Column(length = 100, nullable = false)
    private String name;

    //作者名字
    @Column(name = "first_name", length = 50)
    private String firstName;

    //作者姓氏
    @Column(name = "last_name", length = 50)
    private String lastName;

    public Author() {
    }
}

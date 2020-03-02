package com.example.demo.po;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;


@Data
@Entity
@Table(name = "author")
public class Author {

    //作者id
    @Id
    private String id;

    //作者所在机构
    private String affiliation;

    //作者姓名
    private String name;

    //作者名字
    @Column(name = "first_name")
    private String firstName;

    //作者姓氏
    @Column(name = "last_name")
    private String lastName;
}

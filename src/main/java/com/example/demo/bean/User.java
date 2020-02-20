package com.example.demo.bean;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder
@Setter
@Getter
@Entity
@Table(name = "user")
@GenericGenerator(name = "jpa-uuid" , strategy = "uuid")

public class User {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    private String name;

    public User() {
    }

    public User(String id, String name) {
        this.id=id;
        this.name=name;
    }
//public User(){}
}

package com.example.demo.po;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "field")
public class Field {
    @Id
    private int id;

    @Column(length = 50)
    private String name;

    @Column(length = 400)
    private String conference;
}

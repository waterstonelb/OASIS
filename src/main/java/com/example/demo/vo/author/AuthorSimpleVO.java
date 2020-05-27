package com.example.demo.vo.author;

import com.example.demo.po.Author;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorSimpleVO implements Serializable {
    int id;
    String name;
    String affiliations;

    public AuthorSimpleVO(int id, String name, String affiliations) {
        this.id = id;
        this.name = name;
        this.affiliations = affiliations;
    }

    public AuthorSimpleVO(Author author) {
        this.id=author.getId();
        this.name=author.getName();
        this.affiliations=author.getAffiliation();
    }
}

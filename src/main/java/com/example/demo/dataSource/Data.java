package com.example.demo.dataSource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Data {

    private String abst;

    private List<AuthorData> authors;

    private String  doi;

    private String keywords;

    private String publication;

    private List<RefData> ref;

    private String title;

}

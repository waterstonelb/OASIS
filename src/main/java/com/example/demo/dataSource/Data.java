package com.example.demo.dataSource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Data {

    private String abst;

    private String org_id;

    private String publish_year;

    public String pdf_link;

    private List<AuthorData> authors;

    private String  doi;

    private String keywords;

    private String publication;

    private List<RefData> ref;

    private String title;

}
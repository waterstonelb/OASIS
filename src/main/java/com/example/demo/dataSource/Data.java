package com.example.demo.dataSource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Data {

    @JsonProperty("abstract")
    private String abst;

    private String publish_year;

    private String pdf_link;

    private List<AuthorData> authors;

    private String  doi;

    private String citation_count;

    private String keywords;

    private String publication;

    private List<RefData> ref;

    private String title;

}

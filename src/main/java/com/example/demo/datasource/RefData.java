package com.example.demo.datasource;

import lombok.Data;

import java.util.List;

@Data
public class RefData {

    private List<ContextData> context;

    private String googleScholarLink;

    //refnum
    private String id;

    private LinksData links;

    //reforder
    private String order;

    private String refType;

    private String text;

    private String title;


}

package com.example.demo.dataSource;

import lombok.Data;

import java.util.List;

@Data
public class RefData {

    private List<ContextData> context;

    private String googleScholarLink;

    private String id;

    private LinksData links;

    private String order;

    private String refType;

    private String text;

    private String title;


}

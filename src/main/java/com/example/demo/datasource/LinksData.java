package com.example.demo.datasource;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LinksData {

    @JsonProperty("abstract")
    private String abst;

    private String acmLink;

    private String crossRefLink;

    private String documentLink;

    private String openUrlImgLoc;

    private String pdfLink;

    private String pdfSize;
}

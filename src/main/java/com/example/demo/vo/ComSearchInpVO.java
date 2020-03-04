package com.example.demo.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ComSearchInpVO {
    String authors;
    String institution;
    String conference;
    String keyword;
    int size;
    int page;

    public ComSearchInpVO(String authors, String institution, String conference, String keyword, int size, int page) {
        this.authors = authors;
        this.institution = institution;
        this.conference = conference;
        this.keyword = keyword;
        this.size = size;
        this.page = page;
    }


    public ComSearchInpVO() {
    }
}

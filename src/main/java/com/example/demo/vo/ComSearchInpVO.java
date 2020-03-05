package com.example.demo.vo;


import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty("页号, 从0开始")
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

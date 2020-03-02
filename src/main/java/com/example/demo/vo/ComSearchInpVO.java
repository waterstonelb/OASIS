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
}

package com.example.demo.vo;

import com.example.demo.po.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplePaperVO {
    int documentId;
    int publishYear;
    String title;
    public SimplePaperVO(Document document){
        this.documentId=document.getId();
        this.publishYear=document.getPublicationYear();
        this.title=document.getTitle();
    }
}

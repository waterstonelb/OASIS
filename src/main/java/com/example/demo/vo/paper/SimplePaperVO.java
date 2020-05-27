package com.example.demo.vo.paper;

import com.example.demo.po.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimplePaperVO implements Serializable {
    int documentId;
    int publishYear;
    String title;
    public SimplePaperVO(Document document){
        this.documentId=document.getId();
        this.publishYear=document.getPublicationYear();
        this.title=document.getTitle();
    }
}

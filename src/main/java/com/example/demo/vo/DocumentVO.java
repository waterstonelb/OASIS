package com.example.demo.vo;

import com.example.demo.po.Author;
import com.example.demo.po.Document;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentVO {

    @ApiModelProperty("作者信息")
    List<AuthorSimpleVO> authors;

    String title;
    int date;
    String publication;
    String abst;
    String keywords;
    int citations;
    int docId;
    public DocumentVO(Document document,List<Author> authorList){
        this.title=document.getTitle();
        this.date=document.getPublicationYear();
        this.abst=document.getAbst();
        this.publication=document.getPublication();
        this.citations=document.getCitationCount();
        this.keywords=document.getKeywords();
        this.docId=document.getId();
        this.authors=new ArrayList<>();
        for (Author a: authorList ) {
            this.authors.add(new AuthorSimpleVO(a));
        }
    }


}

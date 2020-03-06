package com.example.demo.vo;

import com.example.demo.po.Author;
import com.example.demo.po.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaperInfoVO {
    String title;
    String publicationTitle;
    String abst;
    String pdf_link;
    int articleCtiationCount;
    int referenceCount;
    List<AuthorSimpleVO> authors;

    public PaperInfoVO(Document document, List<Author> authorList,int referenceCount){
        this.abst=document.getAbst();
        this.title=document.getTitle();
        this.articleCtiationCount=document.getCitationCount();
        this.pdf_link=document.getPdfLink();
        this.publicationTitle=document.getPublication();
        this.referenceCount=referenceCount;
        this.authors=new ArrayList<>();
        for(Author author:authorList){
            authors.add(new AuthorSimpleVO(author));
        }
    }

}

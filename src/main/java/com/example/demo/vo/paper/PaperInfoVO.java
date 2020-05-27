package com.example.demo.vo.paper;

import com.example.demo.po.Author;
import com.example.demo.po.Document;
import com.example.demo.vo.author.AuthorSimpleVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaperInfoVO implements Serializable {
    String title;
    String publicationTitle;
    String abst;
    String pdf_link;
    String keywords;
    int year;
    int articleCtiationCount;
    int referenceCount;
    List<AuthorSimpleVO> authors;

    public PaperInfoVO(Document document, List<Author> authorList,int referenceCount){
        this.year=document.getPublicationYear();
        this.abst=document.getAbst();
        this.title=document.getTitle();
        this.articleCtiationCount=document.getCitationCount();
        this.pdf_link=document.getPdfLink();
        this.publicationTitle=document.getPublication();
        this.referenceCount=referenceCount;
        this.keywords=document.getKeywords();
        this.authors=new ArrayList<>();
        for(Author author:authorList){
            authors.add(new AuthorSimpleVO(author));
        }
    }

}

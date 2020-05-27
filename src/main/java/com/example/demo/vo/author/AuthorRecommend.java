package com.example.demo.vo.author;

import com.example.demo.po.Author;
import com.example.demo.po.Document;
import com.example.demo.vo.paper.SimplePaperVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRecommend implements Serializable {
    String name;
    int authorId;
    int citations;
    String affilication;
    List<SimplePaperVO> papers;
    public AuthorRecommend(Author author, int citations,List<Document> documents){
        this.name=author.getName();
        this.affilication=author.getAffiliation();
        this.authorId=author.getId();
        this.citations=citations;
        papers=new ArrayList<>();
        for(Document d:documents){
            papers.add(new SimplePaperVO(d));
        }
    }

}

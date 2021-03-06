package com.example.demo.vo.author;

import com.example.demo.po.Author;
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
public class AuthorVO implements Serializable {
    private String name;
    private String affiliation;
    private List<String> keywords;
    private int paperCount;
    private int hindex;
    private int citationCount;

    public AuthorVO(Author author,List<String> keywords,int paperCount,int hindex,int citationCount){
        this.name=author.getName();
        this.affiliation=author.getAffiliation();
        this.keywords=new ArrayList<>(keywords);
        this.paperCount=paperCount;
        this.hindex=hindex;
        this.citationCount=citationCount;
    }
}

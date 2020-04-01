package com.example.demo.vo.author;

import com.example.demo.po.AuthorDirectInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorMapNode implements Cloneable{
    String name;
    String authorName;
    long symbolsize;

    public Object clone(){
        AuthorMapNode a;
        try {
            a=(AuthorMapNode) super.clone();
            return a;
        } catch (CloneNotSupportedException e) {
            log.error("AuthorMapNode clone fail");
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
    public AuthorMapNode(AuthorDirectInfo a,long count){
        this.name=Integer.toString(a.getId());
        this.authorName=a.getAuthorname();
        this.symbolsize=count;
    }
}

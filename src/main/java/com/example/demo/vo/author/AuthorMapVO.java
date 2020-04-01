package com.example.demo.vo.author;

import com.example.demo.vo.author.AuthorMapLink;
import com.example.demo.vo.author.AuthorMapNode;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class AuthorMapVO {
    List<AuthorMapNode> nodes;
    List<AuthorMapLink> links;
    public AuthorMapVO(List<AuthorMapNode> nodes,List<AuthorMapLink> links){
        this.nodes=new ArrayList<>();
        this.links=new ArrayList<>();
        for (AuthorMapNode node:nodes) {
            this.nodes.add((AuthorMapNode) node.clone());
        }
        for (AuthorMapLink link:links) {
            this.links.add((AuthorMapLink) link.clone());
        }
    }
}

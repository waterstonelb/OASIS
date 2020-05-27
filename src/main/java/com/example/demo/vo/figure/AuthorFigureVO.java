package com.example.demo.vo.figure;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AuthorFigureVO implements Serializable {
    private List<String> nodes;

    private List<String> links;

    public AuthorFigureVO(List<AuthorNode> authorNodes, List<AuthorLink> authorLinks){
        Map<Integer,Integer> idToIndex = new HashMap<>();
        for(int i = 0; i < authorNodes.size(); i++)
            idToIndex.put(authorNodes.get(i).getId(), i);
        nodes = authorNodes.stream()
                .map(an -> an.getName() + "|" + an.getWeight()
                     + "|" + an.getId())
                .collect(Collectors.toList());
        links = authorLinks.stream()
                .map(al -> idToIndex.get(al.getSource()).toString()
                      + "|"  + idToIndex.get(al.getTarget()).toString()
                      + "|"  + al.getWeight())
                .collect(Collectors.toList());

    }
}

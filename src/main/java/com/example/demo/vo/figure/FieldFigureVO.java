package com.example.demo.vo.figure;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class FieldFigureVO {
    List<String> nodes;

    List<String> links;

    public FieldFigureVO(List<FieldNode> fieldNodes, List<FieldLink> fieldLinks){
        Map<Integer,Integer> idToIndex = new HashMap<>();
        for(int i = 0; i < fieldNodes.size(); i++)
            idToIndex.put(fieldNodes.get(i).getId(), i);
        nodes = fieldNodes.stream()
                .map(an -> an.getName() + "|" + an.getWeight()
                        + "|" + idToIndex.get(an.getId()).toString())
                .collect(Collectors.toList());
        links = fieldLinks.stream()
                .map(al -> idToIndex.get(al.getSource()).toString()
                        + "|"  + idToIndex.get(al.getTarget()).toString()
                        + "|"  + al.getWeight())
                .collect(Collectors.toList());
    }

}

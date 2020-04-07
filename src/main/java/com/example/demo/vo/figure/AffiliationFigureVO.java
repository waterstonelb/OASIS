package com.example.demo.vo.figure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AffiliationFigureVO {
    private List<String> nodes;

    private List<String> links;

    public AffiliationFigureVO(List<AffiliationNode> affiliationNodes,
                               List<AffiliationLink> affiliationLinks){
        Map<Integer,Integer> idToIndex = new HashMap<>();
        for(int i = 0; i < affiliationNodes.size(); i++)
            idToIndex.put(affiliationNodes.get(i).getId(), i);
        nodes = affiliationNodes.stream()
                .map(an -> an.getName() + "|" + an.getWeight()
                        + "|" + an.getId())
                .collect(Collectors.toList());
        links = affiliationLinks.stream()
                .map(al -> idToIndex.get(al.getSource()).toString()
                        + "|"  + idToIndex.get(al.getTarget()).toString()
                        + "|"  + al.getWeight())
                .collect(Collectors.toList());
    }

}

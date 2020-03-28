package com.example.demo.vo.figure;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorFigureVO {
    private List<AuthorNode> nodes;

    private List<AuthorLink> links;
}

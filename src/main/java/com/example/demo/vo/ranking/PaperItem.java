package com.example.demo.vo.ranking;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaperItem implements Serializable {
    private int paperId;

    private int cites;

    private String name;
}

package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AffiliationVO {
    String name;
    int paperCount;
    int citationCount;
    int authorCount;
    int Hindex;
    int shkbScore;
}

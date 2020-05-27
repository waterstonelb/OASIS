package com.example.demo.vo.affiliation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AffiliationVO implements Serializable {
    String name;
    int paperCount;
    int citationCount;
    int authorCount;
    int Hindex;
    int shkbScore;
}

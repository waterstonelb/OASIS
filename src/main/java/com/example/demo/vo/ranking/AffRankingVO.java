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
public class AffRankingVO implements Serializable {

    private int affiliationId;

    private String affiliation;

    private long count;

    private long citation;

    private int hIndex;

    private long shkbScore;
}

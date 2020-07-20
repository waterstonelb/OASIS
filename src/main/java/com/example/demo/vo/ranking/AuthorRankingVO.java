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
public class AuthorRankingVO implements Serializable {

    private int authorId;

    private String author;

    private long count;

    private long citation;

    private int hIndex;

    private long shkbScore;

    public AuthorRankingVO(int authorId, String author, long count, long citation, int hIndex, long shkbScore) {
        this.authorId = authorId;
        this.author = author;
        this.count = count;
        this.citation = citation;
        this.hIndex = hIndex;
        this.shkbScore = shkbScore;
        this.publicationTrend = null;
    }

    private Bar publicationTrend;


}

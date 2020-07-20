package com.example.demo.vo.ranking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRankingInpVO implements Serializable {

    private Integer startTime;

    private Integer endTime;

    private String sortBy;

    private List<String> fields;

    private int page;

    private int size;
}

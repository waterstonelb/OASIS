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
public class AffDetailVO implements Serializable {
    private Bar bar;

    private List<PieItem> pie;
}

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
public class AuthorDetailVO implements Serializable {

    private List<PieItem> pie;

    private List<PaperItem> papers;
}

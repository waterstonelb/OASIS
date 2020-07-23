package com.example.demo.vo.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AuthorPrd {

  private int authorId;
  private int rank;
  private String author;
  private long relation;
}

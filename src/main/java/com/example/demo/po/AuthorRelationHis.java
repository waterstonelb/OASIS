package com.example.demo.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorRelationHis {
  private int id;
  private String name;
  private int year;
  private Long count;
}

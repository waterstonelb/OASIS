package com.example.demo.vo.figure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldNode implements Serializable {

    private String name;

    private long weight;

    private int id;
}

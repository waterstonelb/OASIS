package com.example.demo.vo.field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldPieVO implements Serializable {
    private String name;

    private long value;

    private int id;
}

package com.example.demo.vo.figure;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FieldFigureVO {
    List<FieldNode> nodes;

    List<FieldLink> links;
}

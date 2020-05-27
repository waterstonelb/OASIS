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
public class AuthorLink implements Serializable {

    private int source;

    private int target;

    private long weight;

}

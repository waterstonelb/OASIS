package com.example.demo.vo.merge;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MergeAffFormVO implements Serializable {
    private List<String> namesBefore;

    private String namesAfter;

    private String password;
}

package com.example.demo.vo.top;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopInstitutionVO implements Serializable {

    @ApiModelProperty("机构id")
    private int institutionId;

    @ApiModelProperty("机构名称")
    private String name;

    @ApiModelProperty("机构发表文章数")
    private long paper;
}

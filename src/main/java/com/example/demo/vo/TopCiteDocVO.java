package com.example.demo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopCiteDocVO {

    @ApiModelProperty("文章id")
    private int paperId;

    @ApiModelProperty("文章标题")
    private String  title;

    @ApiModelProperty("文章被引用数")
    private long cites;
}

package com.example.demo.vo.top;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopCiteDocVO implements Serializable {

    @ApiModelProperty("文章id")
    private int paperId;

    @ApiModelProperty("文章标题")
    private String  title;

    @ApiModelProperty("文章被引用数")
    private long cites;
}

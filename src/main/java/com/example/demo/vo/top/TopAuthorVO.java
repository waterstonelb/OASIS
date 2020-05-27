package com.example.demo.vo.top;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopAuthorVO implements Serializable {

    @ApiModelProperty("作者id")
    private int authorId;

    @ApiModelProperty("作者名字")
    private String  name;

    @ApiModelProperty("作者发表文章数")
    private long paper;
}


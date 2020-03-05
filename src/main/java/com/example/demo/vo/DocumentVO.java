package com.example.demo.vo;

import com.example.demo.po.Author;
import com.example.demo.po.Document;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DocumentVO {

    @ApiModelProperty("文章信息")
    private Document document;

    @ApiModelProperty("作者信息")
    private List<Author> authors;
}

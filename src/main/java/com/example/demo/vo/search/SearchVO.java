package com.example.demo.vo.search;

import com.example.demo.vo.paper.DocumentVO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchVO {

    @ApiModelProperty("结果总数")
    private long total;

    @ApiModelProperty("搜索结果(分页)")
    private List<DocumentVO> documents;




}

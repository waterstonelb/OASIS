package com.example.demo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SearchByInstitutionInpVO {

    private String institution;

    private int size;

    @ApiModelProperty("页号, 从0开始")
    private int page;


    @ApiModelProperty("排序标准: 0最新, 1(被)引用次数最多")
    private int sortby;

    @ApiModelProperty("起始时间(年份)")
    private Integer startTime;

    @ApiModelProperty("截止时间(年份)")
    private Integer endTime;
}

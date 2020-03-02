package com.example.demo.po;



import com.example.demo.dataSource.AuthorData;
import com.example.demo.dataSource.RefData;
import lombok.Builder;
import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Data
//@Builder
@Entity
@Table(name = "document")

public class Document {

    //文章id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //摘要
    @Column(length = 10000)
    private String abst;

    //数字对象识别符
    private String  doi;

    //关键词
    private String keywords;

    //发表的会议
    private String publication;

    //标题
    private String title;



}

package com.example.demo.po;



import lombok.*;


import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
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
    @Column(length = 50)
    private String  doi;

    //关键词
    @Column(length = 400)
    private String keywords;

    //发表的会议
    @Column(length = 400)
    private String publication;

    //标题
    @Column(length = 200)
    private String title;

    //年份
    @Column(name = "publication_year")
    private int publicationYear;

    @Column(name = "citation_count")
    private int citationCount;

    @Column(name = "pdf_link", length = 200)
    private String pdfLink;

    public Document(){

    }

}

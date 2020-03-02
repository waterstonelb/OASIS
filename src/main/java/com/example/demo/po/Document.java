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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String abst;

    private String  doi;

    private String keywords;

    private String publication;

    private String title;



}

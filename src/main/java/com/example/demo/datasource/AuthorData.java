package com.example.demo.datasource;

import lombok.Data;

@Data
public class AuthorData {

    //这里是网站原始id 对应authorpo的ieee id
    private String id;

    private String affiliation;

    private String name;

    private String firstName;

    private String lastName;


}

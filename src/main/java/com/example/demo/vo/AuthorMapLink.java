package com.example.demo.vo;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
public class AuthorMapLink implements Cloneable{
    String source;
    String target;
    long value;
    public Object clone(){
        AuthorMapLink a;
        try {
            a=(AuthorMapLink)super.clone();
            return a;
        } catch (CloneNotSupportedException e) {
            log.error("AuthorMapLink clone fail");
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}

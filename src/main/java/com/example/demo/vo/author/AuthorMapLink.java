package com.example.demo.vo.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorMapLink implements Cloneable, Serializable {
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
            return new AuthorMapLink();
        }
    }
}

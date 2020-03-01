package com.example.demo.po;

import javax.persistence.*;
import java.util.Objects;



@Entity
@Table(name = "author")
public class Author {
    @Id
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    @Basic
    @Column(name = "author_name", length = 100, nullable = false)
    private String authorName;

    @Basic
    @Column(name = "author_affiliation", length = 200)
    private String authorAffiliation;



    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }


    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    public String getAuthorAffiliation() {
        return authorAffiliation;
    }

    public void setAuthorAffiliation(String authorAffiliation) {
        this.authorAffiliation = authorAffiliation;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author that = (Author) o;
        return authorId == that.authorId &&
                Objects.equals(authorName, that.authorName) &&
                Objects.equals(authorAffiliation, that.authorAffiliation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, authorName, authorAffiliation);
    }
}

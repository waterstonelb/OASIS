package com.example.demo.po;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "context", schema = "shkb")
public class Context {
    private int id;
    private int refId;
    private String txt;
    private String part;
    private String sec;

    //id
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //引用id
    @Basic
    @Column(name = "ref_id", nullable = false)
    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    //内容文本
    @Basic
    @Column(name = "txt", length = 10000)
    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    //文章第几部分
    @Basic
    @Column(name = "part", length = 50)
    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    //sec
    @Basic
    @Column(name = "sec", length = 50)
    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Context that = (Context) o;
        return id == that.id &&
                refId == that.refId &&
                Objects.equals(txt, that.txt) &&
                Objects.equals(part, that.part) &&
                Objects.equals(sec, that.sec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, refId, txt, part, sec);
    }
}

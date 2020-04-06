package com.example.demo.vo.affiliation;

import com.example.demo.po.AffiliationTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AffTableVO {
    List<Integer> xdata;
    List<Long> data;
    public AffTableVO(List<AffiliationTable> tables){
        this.data=new ArrayList<>();
        this.xdata=new ArrayList<>();
        for(AffiliationTable a : tables){
            data.add(a.getData());
            xdata.add(a.getYear());
        }
    }
}

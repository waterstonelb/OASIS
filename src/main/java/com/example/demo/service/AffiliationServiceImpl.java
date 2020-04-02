package com.example.demo.service;

import com.example.demo.dao.AffiliationDao;
import com.example.demo.po.Affiliation;
import com.example.demo.service.serviceinterface.AffiliationService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.affiliation.AffiliationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AffiliationServiceImpl implements AffiliationService {
    private AffiliationDao affiliationDao;

    @Autowired
    public AffiliationServiceImpl(AffiliationDao affiliationDao) {
        this.affiliationDao = affiliationDao;
    }

    @Override
    public ResponseVO<AffiliationVO> getAffiliationInfo(int affId) {
        try {
            log.info(Thread.currentThread().getName()+"  success");
            log.info("查询机构信息id="+affId);
            Affiliation affiliation=affiliationDao.findFirstById(affId);
            int paperCount=affiliationDao.getPaperCount(affId);
            int citationCount=affiliationDao.getCitationCount(affId);
            int authorCount=affiliationDao.getAuthorCount(affId);
            int shkbScore=citationCount*authorCount/paperCount;
            List<Integer> hlist=affiliationDao.getHindexList(affId);
            hlist.sort(Integer::compareTo);
            int len=hlist.size();
            int hindex=0;
            for (int i=0; i < len; i++) {
                if(hlist.get(i)>=len-i){
                    hindex=len-i;
                    break;
                }
            }
            log.info("查询机构信息成功");
            return ResponseVO.buildSuccess(AffiliationVO.builder()
                     .authorCount(authorCount)
                     .citationCount(citationCount)
                     .Hindex(hindex)
                     .name(affiliation.getName())
                     .paperCount(paperCount)
                     .shkbScore(shkbScore)
                     .build());
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("机构详情获取失败");
        }
    }
}

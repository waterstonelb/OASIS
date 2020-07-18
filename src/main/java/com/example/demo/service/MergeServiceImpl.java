package com.example.demo.service;

import com.example.demo.dao.AffiliationDao;
import com.example.demo.dao.AffiliationPublishDao;
import com.example.demo.dao.AuthorDao;
import com.example.demo.po.Affiliation;
import com.example.demo.po.Author;
import com.example.demo.service.serviceinterface.MergeService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.merge.MergeAffFormVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
public class MergeServiceImpl implements MergeService {

    private AffiliationDao affiliationDao;

    @Autowired
    public void setAffiliationDao(AffiliationDao affiliationDao){
        this.affiliationDao = affiliationDao;
    }

    private AffiliationPublishDao affiliationPublishDao;
    @Autowired
    public void setAffiliationPublishDao(AffiliationPublishDao affiliationPublishDao){
        this.affiliationPublishDao = affiliationPublishDao;
    }

    private AuthorDao authorDao;
    @Autowired
    public void setAuthorDao(AuthorDao authorDao){
        this.authorDao = authorDao;
    }

    @Override
    public ResponseVO<List<String>> getAffiliations() {
        try {
            List<String> affiliations = affiliationDao.getAffiliationNames();
            log.info("获取机构名称成功");
            return ResponseVO.buildSuccess(affiliations);
        }catch (Exception ex){
            log.error("获取机构名称失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    @Transactional
    public ResponseVO<List<String>> mergeAffiliations(MergeAffFormVO mergeAffFormVO) {
        try {
            String password = mergeAffFormVO.getPassword();
            if (!password.equals("123")) return ResponseVO.buildFailure("密码错误");
            List<String> namesBefore = mergeAffFormVO.getNamesBefore();
            String nameAfter = mergeAffFormVO.getNamesAfter();

            List<Integer> idList = affiliationDao.getIdsByNames(namesBefore);
            int idRepresent = idList.get(0);
            affiliationDao.saveAndFlush(Affiliation.builder()
            .id(idRepresent).name(nameAfter).build());
            List<Integer> toDel = idList.subList(1, idList.size());

            affiliationDao.deleteByIdIsIn(toDel);
            affiliationPublishDao.updateToMerged(toDel, idRepresent);
            authorDao.updateToMerge(namesBefore, nameAfter);
            log.info("合并成功");
            return getAffiliations();

        }catch (Exception ex){
            log.error("合并失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("合并失败");
        }
    }
}

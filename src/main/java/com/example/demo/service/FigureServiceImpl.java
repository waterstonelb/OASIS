package com.example.demo.service;

import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.AuthorPublishDao;
import com.example.demo.po.AuthorPublish;
import com.example.demo.service.serviceinterface.FigureService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.figure.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FigureServiceImpl implements FigureService {

    private AuthorPublishDao authorPublishDao;
    @Autowired
    public void setAuthorPublishDao(AuthorPublishDao authorPublishDao){
        this.authorPublishDao = authorPublishDao;
    }
    @Override
    public ResponseVO<AuthorFigureVO> getAuthorFigure() {
        try {
            List<AuthorNode> authorNodes = authorPublishDao.getAllAuthorNodes();
            List<AuthorLink> authorLinks = authorPublishDao.getAllAuthorLinks();
            log.info("作者关系大图建立成功");
            return ResponseVO.buildSuccess(new AuthorFigureVO(authorNodes, authorLinks));
        }catch (Exception ex){
            log.error("作者关系大图建立失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("作者关系大图建立失败");
        }

    }

    @Override
    public ResponseVO<AffiliationFigureVO> getAffiliationFigure() {
        return null;
    }

    @Override
    public ResponseVO<FieldFigureVO> getFieldFigure() {
        return null;
    }
}

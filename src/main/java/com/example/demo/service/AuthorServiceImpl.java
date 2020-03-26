package com.example.demo.service;

import com.example.demo.dao.AuthorDao;
import com.example.demo.dao.AuthorPublishDao;
import com.example.demo.dao.DocumentDao;
import com.example.demo.po.Author;
import com.example.demo.service.serviceinterface.AuthorService;
import com.example.demo.vo.AuthorVO;
import com.example.demo.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {
    final
    AuthorDao authorDao;
    final
    AuthorPublishDao authorPublishDao;
    final
    DocumentDao documentDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao, AuthorPublishDao authorPublishDao, DocumentDao documentDao) {
        this.authorDao = authorDao;
        this.authorPublishDao = authorPublishDao;
        this.documentDao = documentDao;
    }

    @Override
    public ResponseVO<AuthorVO> getAuthorInfo(int authorId) {
        try {
            Author author = authorDao.findById(authorId);
            int paperCount = authorPublishDao.countByAuthorId(authorId);
            int citationCount = authorPublishDao.sumCitationCountByAuthorId(authorId);
            List<String> keywords = authorPublishDao.findAuthorKeyWords(authorId);
            HashMap<String, Integer> sortKey = new HashMap<>();
            for (String key : keywords) {
                if (!key.isEmpty()) {
                    for (String str : key.split(",")) {
                        int count = 0;
                        if (sortKey.containsKey(str))
                            count = sortKey.get(str);
                        sortKey.put(str, count + 1);
                    }
                }
            }
            List<Map.Entry<String, Integer>> list = new ArrayList<>(sortKey.entrySet());
            if (list.size() > 0)
                list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
            List<String> authorKeys = new ArrayList<>();
            for (int i = 0; i < Math.min(list.size(), 5); i++)
                authorKeys.add(list.get(i).getKey());
            log.info("作者信息查询成功");
            return ResponseVO.buildSuccess(new AuthorVO(author, authorKeys, paperCount, citationCount));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return ResponseVO.buildFailure("查询错误，请检查您的Id");
        }
    }
}

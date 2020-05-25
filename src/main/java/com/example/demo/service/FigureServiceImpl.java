package com.example.demo.service;

import com.example.demo.dao.AffiliationPublishDao;
import com.example.demo.dao.AuthorPublishDao;
import com.example.demo.dao.DocumentDao;
import com.example.demo.po.Document;
import com.example.demo.service.serviceinterface.FigureService;
import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.figure.*;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FigureServiceImpl implements FigureService {

    private int TOP = 100;

    private AuthorPublishDao authorPublishDao;
    @Autowired
    public void setAuthorPublishDao(AuthorPublishDao authorPublishDao){
        this.authorPublishDao = authorPublishDao;
    }

    private AffiliationPublishDao affiliationPublishDao;
    @Autowired
    public void setAffiliationPublishDao(AffiliationPublishDao affiliationPublishDao){
        this.affiliationPublishDao = affiliationPublishDao;
    }

    private DocumentDao documentDao;
    @Autowired
    public void setDocumentDao(DocumentDao documentDao){
        this.documentDao = documentDao;
    }

    @Override
    @Cacheable(value = "authorFigureCache")
    public ResponseVO<AuthorFigureVO> getAuthorFigure() {
        try {
            List<AuthorNode> authorNodes = authorPublishDao
                    .getTopAuthorNodes(PageRequest.of(0, TOP)).getContent();
            List<Integer> topIds = authorNodes.stream().map(AuthorNode::getId)
                    .collect(Collectors.toList());
            List<AuthorLink> authorLinks = authorPublishDao.getTopAuthorLinks(topIds);
            //set of top ids and ids which have relations with top
            Set<Integer> idSet = new HashSet<>();
            for(AuthorLink authorLink : authorLinks){
                idSet.add(authorLink.getSource());
                idSet.add(authorLink.getTarget());
            }
            authorNodes = authorPublishDao
                    .getTopAndRelations(new ArrayList<>(idSet));


            log.info("作者关系大图建立成功");
            return ResponseVO.buildSuccess(new AuthorFigureVO(authorNodes, authorLinks));
        }catch (Exception ex){
            log.error("作者关系大图建立失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("作者关系大图建立失败");
        }

    }

    @Override
    @Cacheable(value = "affiliationFigureCache")
    public ResponseVO<AffiliationFigureVO> getAffiliationFigure() {
        try {
            List<AffiliationNode> affiliationNodes = affiliationPublishDao
                    .getTopAffiliationNodes(PageRequest.of(0, TOP)).getContent();
            List<Integer> topIds = affiliationNodes.stream().map(AffiliationNode::getId)
                    .collect(Collectors.toList());

            List<AffiliationLink> affiliationLinks = affiliationPublishDao
                    .getTopAffiliationLinks(topIds);

            //set of top100 ids and ids which have relations with top100
            Set<Integer> idSet = new HashSet<>();
            for(AffiliationLink affiliationLink : affiliationLinks){
                idSet.add(affiliationLink.getSource());
                idSet.add(affiliationLink.getTarget());
            }

            affiliationNodes = affiliationPublishDao
                    .getTopAndRelations(new ArrayList<>(idSet));

            log.info("机构关系大图建立成功");
            return ResponseVO.buildSuccess(new AffiliationFigureVO(affiliationNodes, affiliationLinks));
        }catch (Exception ex){
            log.error("机构关系大图建立失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("机构关系大图建立失败");
        }

    }

    @Override
    @Cacheable(value = "fieldFigureCache")
    public ResponseVO<FieldFigureVO> getFieldFigure() {
        try {

            List<String> docLines = documentDao.findAllKeywords();
            List<String> filteredLines = docLines.stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
            List<String> kwlist = filteredLines.stream().map(s -> s.split(",")).
                    flatMap(Arrays::stream).distinct().collect(Collectors.toList());

            Map<String, Integer> kwIdMap = new HashMap<>();
            for (int i = 0; i < kwlist.size(); i++)
                kwIdMap.put(kwlist.get(i), i);

            Map<Integer, Long> nodes = new HashMap<>();
            Map<IdLink, Long> links = new HashMap<>();

            for (String line : filteredLines) {
                String[] kws = line.split(",");
                for (String kw : kws) {
                    int kwId = kwIdMap.get(kw);
                    if (!nodes.containsKey(kwId))
                        nodes.put(kwId, 1L);
                    else
                        nodes.put(kwId, nodes.get(kwId) + 1);
                }
                for (int i = 0; i < kws.length - 1; i++) {
                    for (int j = 0; j < kws.length; j++) {
                        String s1 = kws[i];
                        String s2 = kws[j];
                        int source = Math.min(kwIdMap.get(s1), kwIdMap.get(s2));
                        int target = Math.max(kwIdMap.get(s1), kwIdMap.get(s2));
                        IdLink idLink = new IdLink(source, target);

                        if (!links.containsKey(idLink))
                            links.put(idLink, 1L);
                        else
                            links.put(idLink, links.get(idLink) + 1);
                    }
                }
            }

            List<FieldNode> fieldNodes = new ArrayList<>();
            List<FieldLink> fieldLinks = new ArrayList<>();
            for (Map.Entry<Integer, Long> entry : nodes.entrySet())
                fieldNodes.add(new FieldNode(kwlist.get(entry.getKey()),
                        entry.getValue(), entry.getKey()));
            for (Map.Entry<IdLink, Long> entry : links.entrySet())
                fieldLinks.add(new FieldLink(entry.getKey().getSource(),
                        entry.getKey().getTarget(), entry.getValue()));
            /*---------------------------------------------------------------------*/

            fieldNodes.sort(((o1, o2) -> (int)(o1.getWeight() - o2.getWeight())));

            Set<Integer> topIdSet = fieldNodes
                    .subList(fieldNodes.size() - TOP, fieldNodes.size())
                    .stream().map(FieldNode::getId).collect(Collectors.toSet());
            //set of top100 ids and ids which have relations with top100
            Set<Integer> idSet = new HashSet<>();

            for (FieldLink fieldLink: fieldLinks){
                if (topIdSet.contains(fieldLink.getSource())
                        || topIdSet.contains(fieldLink.getTarget())){
                    idSet.add(fieldLink.getSource());
                    idSet.add(fieldLink.getTarget());
                }
            }

            fieldNodes = fieldNodes.stream()
                    .filter(fn -> idSet.contains(fn.getId())).collect(Collectors.toList());
            fieldLinks = fieldLinks.stream()
                    .filter(fl -> idSet.contains(fl.getSource()) && idSet.contains(fl.getTarget()))
                    .collect(Collectors.toList());

            log.info("领域关系大图建立成功");

            return ResponseVO.buildSuccess(new FieldFigureVO(fieldNodes, fieldLinks));

        }catch (Exception ex){
            log.error("领域关系大图建立失败");
            log.error(ex.getLocalizedMessage());
            return ResponseVO.buildFailure("领域关系大图建立失败");
        }
    }
}


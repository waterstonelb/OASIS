package com.example.demo.service;

import com.example.demo.dao.*;
import com.example.demo.datasource.AuthorData;
import com.example.demo.datasource.Data;
import com.example.demo.po.*;
import com.example.demo.service.serviceinterface.DataLoadService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DataLoad implements DataLoadService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DocumentDao documentDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private AffiliationDao affiliationDao;

    @Autowired
    private AuthorPublishDao authorPublishDao;

    @Autowired
    private AffiliationPublishDao affiliationPublishDao;




    //postconstruct注解使方法在springboot初始化完成后执行该方法
    //该方法运行较慢, 所以只在需要时使用!!!!!!!!!!!!!!!
    //使用低压双核四线程cpu加nvme ssd(1300左右条原始数据)本地运行时间大约10分钟
    @PostConstruct
    @Override
    public void load(){

        /*-------------------Read Data---------------------------------------*/
        List<String> lines = new ArrayList<>();

        try {
            InputStream in = this.getClass().getResourceAsStream("/data.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            String line;
            while ((line = br.readLine()) != null){
                lines.add(line);
            }
            br.close();
        }catch (IOException ex){
            log.error(ex.getLocalizedMessage());
        }

        /*------------------Transfer json to object---------------------------*/
        List<Data> originalData = new ArrayList<>();

        try {
            for (String line : lines)
                originalData.add(objectMapper.readValue(line, Data.class));
        }catch (Exception ex){
            log.error(ex.getLocalizedMessage());
        }

        /*----------------Load data to database----------------------------*/
        int now = 0;
        int all = originalData.size();
        for (Data data : originalData) {
            System.out.println("process: " + now + "/" + all);
            now++;
            loadSingle(data);
        }

        log.info("加载原始文章数据成功");
    }

    @Override
    public void loadSingle(Data data){
        if (data.getAuthors().isEmpty())
            return;
        if (documentDao.existsByPdfLink(data.getPdf_link()))
            return;
        writeSingle(data);
    }


    private void writeSingle(Data data) {
        try {

            int citation_count = data.getCitation_count().length() == 0 ?
                    0 : Integer.parseInt(data.getCitation_count());

        Document document = Document.builder().abst(data.getAbst())
                .doi(data.getDoi()).keywords(data.getKeywords()).title(data.getTitle())
                .publication(data.getPublication()).pdfLink(data.getPdf_link())
                .citationCount(citation_count)
                .publicationYear(Integer.parseInt(data.getPublish_year())).build();

        Document docRes = documentDao.saveAndFlush(document);
        int docId = docRes.getId();

        List<AuthorData> authorDatas = data.getAuthors().stream()
                .filter(a -> a.getId() != null && !a.getId().isEmpty()
                        && a.getName() != null && !a.getName().isEmpty())
                .collect(Collectors.toList());


        for(AuthorData authorData : authorDatas){
            /*----------load author msg----------------------*/
            boolean auexists = authorDao.existsByIeeeId(authorData.getId());
            if (auexists){
                int auId = authorDao.findFirstByIeeeId(authorData.getId()).getId();
                AuthorPublish authorPublish = AuthorPublish.builder()
                        .authorId(auId).documentId(docId).build();
                authorPublishDao.saveAndFlush(authorPublish);
            }else{
                Author author = Author.builder()
                        .name(authorData.getName()).affiliation(authorData.getAffiliation())
                        .ieeeId(authorData.getId()).firstName(authorData.getFirstName())
                        .lastName(authorData.getLastName()).build();
                Author auRes = authorDao.saveAndFlush(author);
                AuthorPublish authorPublish = AuthorPublish.builder()
                        .authorId(auRes.getId()).documentId(docId).build();
                authorPublishDao.saveAndFlush(authorPublish);
            }

            /*load affiliation msg*/
            boolean afexists = affiliationDao.existsByName(authorData.getAffiliation());
            boolean afEmpty = authorData.getAffiliation()==null
                    || authorData.getAffiliation().isEmpty();
            if (afexists){
                int afid = affiliationDao.findFirstByName(authorData.getAffiliation()).getId();
                AffiliationPublish affiliationPublish = AffiliationPublish.builder()
                        .affId(afid).documentId(docId).build();
                affiliationPublishDao.saveAndFlush(affiliationPublish);
            }else if (!afEmpty){
                Affiliation affiliation = Affiliation.builder()
                        .name(authorData.getAffiliation()).build();
                Affiliation afRes = affiliationDao.saveAndFlush(affiliation);
                AffiliationPublish affiliationPublish = AffiliationPublish.builder()
                        .affId(afRes.getId()).documentId(docId).build();
                affiliationPublishDao.saveAndFlush(affiliationPublish);
            }
        }



    }catch (Exception ex){
            log.warn("加载某个文章信息出现错误");
        }
    }


    private void changePdfLinkFormat(){
        List<Document> docs = documentDao.findAll();
        for (Document document : docs){
            String linkNum = document.getPdfLink().split("=")[1];
            String newLink = "https://ieeexplore.ieee.org/document/" + linkNum;
            document.setPdfLink(newLink);
            documentDao.saveAndFlush(document);
        }

    }

}

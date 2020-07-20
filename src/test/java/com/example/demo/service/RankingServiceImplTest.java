package com.example.demo.service;


import com.example.demo.service.serviceinterface.RankingService;
import com.example.demo.vo.ranking.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RankingServiceImplTest {

    @Autowired
    private RankingService rankingService;

    private AffRankingInpVO affRankingInpVO;
    private AffDetailInpVO affDetailInpVO;
    private AuthorRankingInpVO authorRankingInpVO;
    private AuthorDetailInpVO authorDetailInpVO;
    @Before
    public void init(){
        affRankingInpVO = AffRankingInpVO.builder()
                .startTime(2010).endTime(2020).page(0).size(50)
                .fields(Arrays.asList("Computing and Processing",
                        "Communication, Networking and Broadcast Technologies",
                        "Aerospace", "Bioengineering")).build();
        affDetailInpVO = AffDetailInpVO.builder().affiliationId(8).build();
        authorRankingInpVO = AuthorRankingInpVO.builder()
                .startTime(2010).endTime(2020).page(0).size(50)
                .fields(Arrays.asList("Computing and Processing",
                        "Communication, Networking and Broadcast Technologies",
                        "Aerospace", "Bioengineering")).build();
        authorDetailInpVO = AuthorDetailInpVO.builder().authorId(1).build();
    }
    @Test
    public void getAffListTest(){
        affRankingInpVO.setSortBy("Count");
        AffRankingResVO affRankingResVO =
                 rankingService.getAffList(affRankingInpVO).getData();
        System.out.println(affRankingResVO.getList().get(0).getAffiliation());

        affRankingInpVO.setSortBy("Citation");
        affRankingResVO =
                rankingService.getAffList(affRankingInpVO).getData();
        System.out.println(affRankingResVO.getList().get(0).getAffiliation());

        affRankingInpVO.setSortBy("SHKBScore");
        affRankingResVO =
                rankingService.getAffList(affRankingInpVO).getData();
        System.out.println(affRankingResVO.getList().get(0).getAffiliation());
    }
    @Test
    public void getAffDetailTest(){
        AffDetailVO affDetailVO =
                rankingService.getAffDetail(affDetailInpVO).getData();
        System.out.println(affDetailVO.getBar().getValue());
        System.out.println(affDetailVO.getPie().get(0).getName());
    }

    @Test
    public void getAuListTest(){
        authorRankingInpVO.setSortBy("Count");
        AuthorRankingResVO authorRankingResVO =
                rankingService.getAuthorList(authorRankingInpVO).getData();
        System.out.println(authorRankingResVO.getList().get(0).getAuthor());

        authorRankingInpVO.setSortBy("Citation");
        authorRankingResVO =
                rankingService.getAuthorList(authorRankingInpVO).getData();
        System.out.println(authorRankingResVO.getList().get(0).getAuthor());

        authorRankingInpVO.setSortBy("SHKBScore");
        authorRankingResVO =
                rankingService.getAuthorList(authorRankingInpVO).getData();
        System.out.println(authorRankingResVO.getList().get(0).getAuthor());


    }
    @Test
    public void getAuDetailTest(){
        AuthorDetailVO authorDetailVO =
                rankingService.getAuthorDetail(authorDetailInpVO).getData();
        System.out.println(authorDetailVO.getPapers().size());
        System.out.println(authorDetailVO.getPie().get(0).getName());
    }
}

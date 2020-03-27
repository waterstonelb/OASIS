package com.example.demo.controller;

import com.example.demo.service.serviceinterface.AuthorService;
import com.example.demo.vo.AuthorMapVO;
import com.example.demo.vo.AuthorVO;
import com.example.demo.vo.ResponseVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;
    @Autowired
    public void setAuthorService(AuthorService authorService){
        this.authorService=authorService;
    }

    /**
     * 作者详细信息查询
     * @param authorId 作者Id
     * @return {@link AuthorVO}
     */
    @ApiOperation("作者详细信息查询")
    @GetMapping("/info")
    public ResponseVO<AuthorVO> getAuthorInfo(@RequestParam int authorId){
        log.info("查询作者信息:"+authorId);
        return authorService.getAuthorInfo(authorId);
    }

    /**
     * 作者关系小图查询
     * @param authorId 作者ID
     * @return {@link AuthorMapVO}
     */
    @ApiOperation("作者关系小图")
    @GetMapping("/map")
    public ResponseVO<AuthorMapVO> getAuthorMap(@RequestParam int authorId){
        log.info("查询作者关系图:"+authorId);
        return authorService.getAuthorMap(authorId);
    }


}

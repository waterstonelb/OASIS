package com.example.demo.controller;

import com.example.demo.service.serviceinterface.AuthorService;
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
     * @return AuthorInfo
     */
    @ApiOperation("作者详细信息查询")
    @GetMapping("/info")
    public ResponseVO<AuthorVO> getAuthorInfo(@RequestParam int authorId){
        log.info("查询作者信息:"+authorId);
        return authorService.getAuthorInfo(authorId);
    }


}

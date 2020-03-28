package com.example.demo.service.serviceinterface;

import com.example.demo.vo.ResponseVO;
import com.example.demo.vo.figure.AffiliationFigureVO;
import com.example.demo.vo.figure.AuthorFigureVO;
import com.example.demo.vo.figure.FieldFigureVO;
import org.springframework.stereotype.Service;


public interface FigureService {

    ResponseVO<AuthorFigureVO> getAuthorFigure();

    ResponseVO<AffiliationFigureVO> getAffiliationFigure();

    ResponseVO<FieldFigureVO> getFieldFigure();
}

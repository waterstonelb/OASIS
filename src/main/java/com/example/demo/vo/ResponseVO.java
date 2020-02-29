package com.example.demo.vo;

public class ResponseVO {

    private boolean success;

    private String message;

    private Object data;


    public static ResponseVO buildSuccess(Object data){
        ResponseVO responseVO = new ResponseVO();
        responseVO.success = true;
        responseVO.data = data;
        return responseVO;
    }

    public static ResponseVO buildFailure(String message){
        ResponseVO responseVO = new ResponseVO();
        responseVO.success = false;
        responseVO.message = message;
        return responseVO;
    }
}

package com.opendev.advice;

import com.opendev.exception.AccessLessException;
import com.opendev.response.ResponseHelper;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ResponseAdvice {

    /**
     *  操作速度太快
     *
     * @return
     */

    @ExceptionHandler(value = AccessLessException.class)
    public ResponseHelper AccessLessException(){
        return ResponseHelper.SpeedFast();
    }
}

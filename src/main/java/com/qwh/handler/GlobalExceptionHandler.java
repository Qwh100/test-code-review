package com.qwh.handler;



import com.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    /**
     * 处理全部异常
     */
    @ExceptionHandler(Exception.class)
    public R exceptionHandler(Exception ex){
        log.error("异常信息：{}",ex.getMessage());
        ex.printStackTrace();
        return R.error(ex.getMessage());

    }




}

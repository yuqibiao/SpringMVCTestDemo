package com.yyyu.spring_mvc.exception;

/**
 * 功能：自定义异常
 *
 * @author yu
 * @date 2017/6/20.
 */
public class MyException extends Exception{

    // 异常信息
    private String message;

    public MyException() {
        super();
    }

    public MyException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

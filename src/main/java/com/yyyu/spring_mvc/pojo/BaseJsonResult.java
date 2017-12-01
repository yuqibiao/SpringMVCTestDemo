package com.yyyu.spring_mvc.pojo;

/**
 * 功能：
 *
 * @author yu
 * @date 2017/11/30.
 */
public class BaseJsonResult<T> {

    private int code ;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

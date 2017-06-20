package com.yyyu.spring_mvc.pojo;

import java.util.Date;

/**
 * 功能：测试包装得pojo类型传参
 *
 * @author yu
 * @date 2017/6/20.
 */
public class QueryVo {

    private User user;
    private String otherPrams;
    private Date birth;

    public QueryVo(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOtherPrams() {
        return otherPrams;
    }

    public void setOtherPrams(String otherPrams) {
        this.otherPrams = otherPrams;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}

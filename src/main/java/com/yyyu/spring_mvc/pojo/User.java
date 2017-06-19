package com.yyyu.spring_mvc.pojo;

/**
 * 功能：用户信息Bean
 *
 * @author yu
 * @date 2017/6/19.
 */
public class User {

    private int id;
    private String username;
    private String pwd;
    private Integer age;
    private String intro;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}

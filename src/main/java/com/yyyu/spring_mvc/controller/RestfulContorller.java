package com.yyyu.spring_mvc.controller;

import com.yyyu.spring_mvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能：Restful风格测试
 *
 * Restful只是一中编程风格，唯一多得是@PathVariable注解
 *
 * 注意：
 * web.xml中得拦截配置要改成/（之前是*.action一直报404）
 *
 * @author yu
 * @date 2017/6/21.
 */
@Controller
@RequestMapping("/restful")
public class RestfulContorller {

    @RequestMapping(value = {"/userInfo/{userId}"} , method = RequestMethod.GET)
    public @ResponseBody User getUserInfo(@PathVariable() Integer userId){
        User user = new User();
        if(userId==1){
            user.setUsername("禹启标");
            user.setAge(24);
            user.setIntro("GET请求");
        }
        return user;
    }


}

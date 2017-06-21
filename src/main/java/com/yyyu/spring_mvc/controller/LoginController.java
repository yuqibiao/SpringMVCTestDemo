package com.yyyu.spring_mvc.controller;

import com.yyyu.spring_mvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能：登录相关控制器
 *
 * @author yu
 * @date 2017/6/19.
 */
@Controller
public class LoginController {

    /**
     * 登录验证
     * @return
     */
    @RequestMapping(value = "/check.action" ,method ={RequestMethod.POST} )
    public ModelAndView check(HttpServletRequest request){

        ModelAndView modelAndView = new ModelAndView();

        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        if("yyyu".equals(username) && "123".equals(pwd)){
            User user = new User();
            user.setUsername("yyyu");
            user.setAge(24);
            user.setIntro("Java 入门选手");
            modelAndView.addObject(user);
            modelAndView.setViewName("/view/userInfo.jsp");
            request.getSession().setAttribute("user" , user);
        }else{
            modelAndView.addObject("用户名或密码错误！");
            modelAndView.setViewName("/view/login.jsp");
        }
        return modelAndView;

    }

    @RequestMapping(value = "/login/modifyUserInfo.action")
    public @ResponseBody User modifyUserInfo( ){
        User user = new User();
        user.setUsername("yu");
        user.setIntro("模拟修改用户信息，登录后才能看到我哦！");
        return user;
    }

}

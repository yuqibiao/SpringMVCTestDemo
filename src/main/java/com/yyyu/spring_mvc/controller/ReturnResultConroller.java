package com.yyyu.spring_mvc.controller;

import com.yyyu.spring_mvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 功能：Controller返回结果得几种类型
 *
 * @author yu
 * @date 2017/6/20.
 */
@Controller
@RequestMapping("/result")
public class ReturnResultConroller {


    /**
     * 3.返回字符串类型（开发中推荐使用）
     *
     * 当参数通过pojo、基本类型、封装pojo绑定时，该指可以直接在下一个页面中使用
     * 等价于modelAndView.addObject(user);
     *
     * @param user
     * @return
     */
    @RequestMapping( value = {"/getResult3.action"} , method = {RequestMethod.GET})
    public String getResult3(User user){
        String result;
        String username = user.getUsername();
        String pwd = user.getPwd();
        if("yyyu".equals(username) && "123".equals(pwd)){
            user.setUsername("禹启标");
            user.setAge(24);
            user.setIntro("返回字符串类型（开发中推荐使用）");
            result =  "forward:/view/userInfo.jsp";
        }else{
            result = "forward://view/login.jsp";
        }
        return result;
    }

    /**
     * 2.返回void
     *
     * 通过session传值，通过request转发页面
     *也可以如3中那样传值
     *
     * @param request
     * @param response
     */
    @RequestMapping( value = {"/getResult2.action"} , method = {RequestMethod.GET})
    public void getResult2(HttpServletRequest request  , HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        System.out.println("username："+username+"   pwd："+pwd);
        if("yyyu".equals(username) && "123".equals(pwd)){
            User user = new User();
            user.setUsername("禹启标");
            user.setAge(24);
            user.setIntro("通过session传值，通过request转发页面");
            HttpSession session = request.getSession();
            session.setAttribute("user" , user);
            request.getRequestDispatcher("/view/userInfo.jsp").forward(request , response);
        }else{
            request.getRequestDispatcher("/view/login.jsp").forward(request , response);
        }
    }

    /**
     * 1.通过ModelAndView返回值和跳转页面
     *
     * @param request
     * @return
     */
    @RequestMapping( value = {"/getResult1.action"} , method = {RequestMethod.GET})
    public ModelAndView getResult1(HttpServletRequest request ){
        ModelAndView modelAndView = new ModelAndView();
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        System.out.println("username："+username+"   pwd："+pwd);
        if("yyyu".equals(username) && "123".equals(pwd)){
            User user = new User();
            user.setUsername("禹启标");
            user.setAge(24);
            user.setIntro("通过ModelAndView返回值和跳转页面");
            modelAndView.addObject(user);
            modelAndView.setViewName("/view/userInfo.jsp");
        }else{
            modelAndView.addObject("用户名或密码错误！");
            modelAndView.setViewName("/view/login.jsp");
        }
        return modelAndView;
    }

}

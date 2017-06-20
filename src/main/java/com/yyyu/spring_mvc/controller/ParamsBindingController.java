package com.yyyu.spring_mvc.controller;

import com.yyyu.spring_mvc.pojo.QueryVo;
import com.yyyu.spring_mvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 功能：参数绑定得几种方式
 *
 *
 * 各种参数绑定（List、Set）：http://www.cnblogs.com/HD/p/4107674.html
 *
 * @author yu
 * @date 2017/6/20.
 */
@Controller
public class ParamsBindingController {


    /**
     * 5.自定义Converter
     *
     * 默认情况下如果接受前台数据的Vo类或者Dto类中有Date类型属性,那么请求时
     * SpringMVC会直接返回400代码,而不会执行Controller方法.这是因为无法解析
     * 参数导致的.这种情况下我们需要自定义转换器.
     *
     * 参考：https://www.oschina.net/code/snippet_2438265_52717
     *
     * @param queryVo
     * @return
     */
    @RequestMapping( value = {"/getParams5"} , method = {RequestMethod.GET})
    public ModelAndView getParams2(QueryVo queryVo){
        ModelAndView modelAndView = new ModelAndView();
        String username = queryVo.getUser().getUsername();
        String pwd = queryVo.getUser().getPwd();
        Date birth = queryVo.getBirth();
        System.out.println("username："+username+"   pwd："+pwd+"  birth："+birth);
        if("yyyu".equals(username) && "123".equals(pwd)){
            queryVo.getUser().setAge(24);
            queryVo.getUser().setIntro("自定义Converter将字符串转换成Date类型");
            modelAndView.addObject(queryVo.getUser());
            modelAndView.setViewName("view/userInfo2.jsp");
        }else{
            modelAndView.addObject("用户名或密码错误！");
            modelAndView.setViewName("view/login.jsp");
        }
        return modelAndView;
    }


    /**
     * 4.封装得pojo类型得绑定
     *
     *注意在表单代码中，需要使用“属性名(对象类型的属性).属性名”来命名input的name。
     * XX/getParams4.action?user.username=yyyu&user.pwd=123&otherPrams=hahaha
     * @param queryVo
     * @return
     */
    @RequestMapping( value = {"/getParams4"} , method = {RequestMethod.GET})
    public ModelAndView getParams(QueryVo queryVo){
        ModelAndView modelAndView = new ModelAndView();
        String username = queryVo.getUser().getUsername();
        String pwd = queryVo.getUser().getPwd();
        String otherPrams = queryVo.getOtherPrams();
        System.out.println("username："+username+"   pwd："+pwd+"  otherPrams："+otherPrams);
        if("yyyu".equals(username) && "123".equals(pwd)){
            queryVo.getUser().setAge(24);
            queryVo.getUser().setIntro("意在表单代码中，需要使用“属性名(对象类型的属性).属性名”来命名input的name");
            modelAndView.addObject(queryVo.getUser());
            modelAndView.setViewName("view/userInfo.jsp");
        }else{
            modelAndView.addObject("用户名或密码错误！");
            modelAndView.setViewName("view/login.jsp");
        }
        return modelAndView;
    }


    /**
     * 3.绑定pojo类型
     *
     *注意pojo中属性名和对应控件得id是否一致得情况，如果不一致需要在pojo中对
     * 应得属性上加上RequestParam注解。
     *
     * @param user
     * @return
     */
    @RequestMapping( value = {"/getParams3"} , method = {RequestMethod.GET})
    public ModelAndView getParams(User user){
        ModelAndView modelAndView = new ModelAndView();
        String username = user.getUsername();
        String pwd = user.getPwd();
        if("yyyu".equals(username) && "123".equals(pwd)){
            user.setAge(24);
            user.setIntro("注意pojo中属性名和对应控件得id是否一致得情况");
            modelAndView.addObject(user);
            modelAndView.setViewName("view/userInfo.jsp");
        }else{
            modelAndView.addObject("用户名或密码错误！");
            modelAndView.setViewName("view/login.jsp");
        }
        return modelAndView;
    }

    /**
     * 2.简单类型参数得绑定
     *
     * 直接把参数当作controller方法得形参即可，如果参数名和表单中控件得id名一致
     * 就不用额外加注解了，否则需要加上RequestParam注解。
     *
     * @return
     */
    @RequestMapping( value = {"/getParams2"} , method = {RequestMethod.GET})
    public ModelAndView getParams(String username ,
                                  @RequestParam(value = "pwd" , defaultValue = "222") String password){
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("username："+username+"   pwd："+password);
        if("yyyu".equals(username) && "123".equals(password)){
            User user = new User();
            user.setUsername(username);
            user.setAge(24);
            user.setIntro("直接把参数当作controller方法得形参即可，如果参数名和表单" +
                    "中控件得id名一致就不用额外加注解了，否则需要加上注解。");
            modelAndView.addObject(user);
            modelAndView.setViewName("view/userInfo.jsp");
        }else{
            modelAndView.addObject("用户名或密码错误！");
            modelAndView.setViewName("view/login.jsp");
        }
        return modelAndView;
    }

    /**
     * 1.使用servlet中Api
     *
     * HttpServletRequest：获取请求信息
     * HttpServletResponse：获取响应信息
     * HttpSession：获取session信息
     *
     * @return
     */
    @RequestMapping( value = {"/getParams1"} , method = {RequestMethod.GET})
    public ModelAndView getParams(HttpServletRequest request ){
        ModelAndView modelAndView = new ModelAndView();
        String username = request.getParameter("username");
        String pwd = request.getParameter("pwd");
        System.out.println("username："+username+"   pwd："+pwd);
        if("yyyu".equals(username) && "123".equals(pwd)){
            User user = new User();
            user.setUsername("禹启标");
            user.setAge(24);
            user.setIntro("HttpServletRequest：获取请求信息");
            modelAndView.addObject(user);
            modelAndView.setViewName("view/userInfo.jsp");
        }else{
            modelAndView.addObject("用户名或密码错误！");
            modelAndView.setViewName("view/login.jsp");
        }
        return modelAndView;
    }

}

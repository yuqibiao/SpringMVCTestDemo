package com.yyyu.spring_mvc.controller;

import com.yyyu.spring_mvc.exception.MyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能：模拟抛出异常
 *
 * @author yu
 * @date 2017/6/20.
 */

@Controller
@RequestMapping("/exception")
public class ExceptionController {

    @RequestMapping("/getException1.action")
    public void getException1() throws MyException {
        throw  new MyException("自定义得异常====");
    }

    @RequestMapping("/getException2.action")
    public void getException2(){
       throw new NullPointerException("空指针====");
    }


}

package com.yyyu.spring_mvc.controller;

import com.yyyu.spring_mvc.pojo.QueryVo;
import com.yyyu.spring_mvc.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能：JSON交互
 *
 * 需要导入得jar包：
 * jackson-annotations-2.4.0.jar
 * jackson-core-2.4.2.jar
 * jackson-databind-2.4.2.jar
 *
 * @author yu
 * @date 2017/6/20.
 */
@Controller
@RequestMapping("/json")
public class JsonInteractionController {

    @RequestMapping(value = "/getJsonData.action" , method = RequestMethod.POST)
    public @ResponseBody  QueryVo getJsonData(@RequestBody User user){

        System.out.println("====user=="+user);
        QueryVo queryVo = new QueryVo();
        if("123".equals(user.getPwd())){
            user.setAge(24);
            user.setIntro("通过Json返回数据");
        }
        queryVo.setUser(user);
        queryVo.setOtherPrams("你是人间得四月天");
        return queryVo;
    }

}

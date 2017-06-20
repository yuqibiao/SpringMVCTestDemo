package com.yyyu.spring_mvc.exception;

import com.sun.xml.internal.ws.handler.HandlerException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 功能：自定义异常处理器
 *
 *
 * 参考：http://www.cnblogs.com/xguo/p/3163519.html
 *
 * 404、500等异常可以再web.xml文件里配置
 *
 * @author yu
 * @date 2017/6/20.
 */
public class CustomExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        String msg;
        if(ex instanceof MyException){
            msg = ex.getMessage();
        }else{
            // 如果是运行时异常，则取错误堆栈，从堆栈中获取异常信息
            Writer out = new StringWriter();
            PrintWriter s = new PrintWriter(out);
            ex.printStackTrace(s);
            msg = out.toString();
        }

        // 返回错误页面，给用户友好页面显示错误信息
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", msg);
        modelAndView.setViewName("/view/error.jsp");
        return modelAndView;
    }

}

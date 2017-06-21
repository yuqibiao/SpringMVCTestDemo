package com.yyyu.spring_mvc.interceptor;

import com.yyyu.spring_mvc.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 功能：用户登录状态判断拦截器
 *
 * preHandle按拦截器定义顺序调用
 * postHandler按拦截器定义逆序调用
 * afterCompletion按拦截器定义逆序调用
 * postHandler在拦截器链内所有拦截器返成功调用
 * afterCompletion只要preHandle返回true才调用
 *
 * @author yyyu
 * @date 2017/6/21
 *
 */
public class LoginHandlerInterceptor implements HandlerInterceptor{

    /**
     *Controller执行前调用此方法
     * 返回true表示继续执行，返回false中止执行
     * 这里可以加入登录校验、权限拦截等
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user!=null){//已登录
            return true;
        }else{//未登录 跳转到登录界面
            response.sendRedirect(request.getContextPath()+"/view/login.jsp");
        }
        return false;
    }

    /**
     * Controller执行后但是未返回视图前调用
     *  这里可在返回用户前对模型数据进行加工处理，比如这里加入公用信息以便页面显示
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     *  Controller执行且返回视图后调用
     *  这里可得到执行controller时的异常信息
     *  这里可记录操作日志
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

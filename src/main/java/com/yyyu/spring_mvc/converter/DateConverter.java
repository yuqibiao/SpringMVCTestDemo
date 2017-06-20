package com.yyyu.spring_mvc.converter;


import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能：将字符串转换为时间类型
 *
 * @author yu
 * @date 2017/6/20.
 */
public class DateConverter implements Converter<String  , Date> {

    @Override
    public Date convert(String source) {
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
            Date date = simpleDateFormat.parse(source);
            return date;
        }catch (Exception e){
            System.out.println("Date转换异常==="+e.getMessage());
            return null;
        }

    }
}

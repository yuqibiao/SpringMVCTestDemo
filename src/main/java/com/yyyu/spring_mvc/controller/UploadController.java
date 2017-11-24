package com.yyyu.spring_mvc.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 功能：文件上传
 *
 * 需要导入得jar包：
 * commons-fileupload-1.2.2.jar
 * commons-io-2.4.jar
 *
 * 三种方式：
 * http://www.cnblogs.com/fjsnail/p/3491033.html
 * 上传进度：
 * http://blog.csdn.net/frankcheng5143/article/details/51832428
 *
 * tomcat配置虚拟路径：
 * http://longx.blog.51cto.com/3327997/1357666
 *
 * Idea添加虚拟路径：
 * http://blog.csdn.net/h3243212/article/details/50819218
 *
 * @author yu
 * @date 2017/6/21.
 */
@Controller
@RequestMapping(value = {"/upload"})
public class UploadController {

    public static final String FILEPATH_IMG= "/uploadFiles/upload_Img/";	//用户图片上传路径


    @RequestMapping(value = {"/fileUpload1.action"})
    public String fileUpload1(@RequestParam("file") CommonsMultipartFile multipartFile , HttpServletRequest request , Model model) {

        //获得图片地址
        String rootPath = this.getClass().getResource("/").getPath();
        System.out.println("rootPath==b=="+rootPath);
        rootPath = rootPath.substring(1, rootPath.indexOf("/WEB-INF"));
        System.out.println("rootPath==a=="+rootPath);
        String picName = UUID.randomUUID().toString();

        //获取文件名
        String oriName = multipartFile.getOriginalFilename();
        //获取文件后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        String uploadPath = rootPath+FILEPATH_IMG+picName+extName;
        System.out.println("uploadPath===="+uploadPath);
        try {
            File saveFile = new File(uploadPath);
            if(!saveFile.getParentFile().exists()){
                saveFile.getParentFile().mkdirs();
            }
            multipartFile.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("异常==="+e.getMessage());
        }
        model.addAttribute("filePath" , request.getContextPath()+FILEPATH_IMG+picName+extName);

        return "forward:/view/uploadShow.jsp";
    }

    @RequestMapping(value = {"/fileUpload2.action"})
    public String fileUpload2(@RequestParam("file") CommonsMultipartFile multipartFile , HttpServletRequest request , Model model) {

        //获得图片地址
        String picName = UUID.randomUUID().toString();

        //获取文件名
        String oriName = multipartFile.getOriginalFilename();
        //获取文件后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        String uploadPath = "e:/upload"+FILEPATH_IMG+picName+extName;
        System.out.println("uploadPath===="+uploadPath);
        try {
            File saveFile = new File(uploadPath);
            if(!saveFile.getParentFile().exists()){
                saveFile.getParentFile().mkdirs();
            }
            //使用流得形式
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("异常==="+e.getMessage());
        }
        model.addAttribute("filePath" , "/upload"+FILEPATH_IMG+picName+extName);
        return "forward:/view/uploadShow.jsp";
    }

}

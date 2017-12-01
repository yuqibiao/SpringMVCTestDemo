package com.yyyu.spring_mvc.controller;

import com.yyyu.spring_mvc.pojo.BaseJsonResult;
import com.yyyu.spring_mvc.pojo.CropInfo;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 功能：文件上传
 * <p>
 * 需要导入得jar包：
 * commons-fileupload-1.2.2.jar
 * commons-io-2.4.jar
 * <p>
 * 三种方式：
 * http://www.cnblogs.com/fjsnail/p/3491033.html
 * 上传进度：
 * http://blog.csdn.net/frankcheng5143/article/details/51832428
 * <p>
 * tomcat配置虚拟路径：
 * http://longx.blog.51cto.com/3327997/1357666
 * <p>
 * Idea添加虚拟路径：
 * http://blog.csdn.net/h3243212/article/details/50819218
 *
 * @author yu
 * @date 2017/6/21.
 */
@Controller
@RequestMapping(value = {"/upload"})
public class UploadController {

    public static final String FILEPATH_IMG = "/uploadFiles/upload_Img/";    //用户图片上传路径


    @RequestMapping(value = {"/fileUpload1.action"})
    public String fileUpload1(@RequestParam("file") CommonsMultipartFile multipartFile, HttpServletRequest request, Model model) {

        //获得图片地址
        String rootPath = this.getClass().getResource("/").getPath();
        rootPath = rootPath.substring(1, rootPath.indexOf("/WEB-INF"));
        String id = request.getParameter("id");
        System.err.println("id=" + id);

        String picName = UUID.randomUUID().toString();

        //获取文件名
        String oriName = multipartFile.getOriginalFilename();
        //获取文件后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        String uploadPath = rootPath + FILEPATH_IMG + picName + extName;
        System.out.println("uploadPath====" + uploadPath);
        try {
            File saveFile = new File(uploadPath);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            multipartFile.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("异常===" + e.getMessage());
        }
        model.addAttribute("filePath", request.getContextPath() + FILEPATH_IMG + picName + extName);

        return "forward:/view/uploadShow.jsp";
    }

    @RequestMapping(value = {"/fileUpload2.action"})
    public String fileUpload2(@RequestParam("file") CommonsMultipartFile multipartFile, HttpServletRequest request, Model model) {

        //获得图片地址
        String picName = UUID.randomUUID().toString();

        //获取文件名
        String oriName = multipartFile.getOriginalFilename();
        //获取文件后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        String uploadPath = "e:/upload" + FILEPATH_IMG + picName + extName;
        System.out.println("uploadPath====" + uploadPath);
        try {
            File saveFile = new File(uploadPath);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            //使用流得形式
            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), saveFile);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("异常===" + e.getMessage());
        }
        model.addAttribute("filePath", "/upload" + FILEPATH_IMG + picName + extName);
        return "forward:/view/uploadShow.jsp";
    }

    @RequestMapping(value = {"/imgUpload.action"})
    public String imgUpload(@RequestParam("file") CommonsMultipartFile multipartFile, HttpServletRequest request, Model model) {
        int x = Integer.parseInt(request.getParameter("x"));
        int y = Integer.parseInt(request.getParameter("y"));
        int x2 = Integer.parseInt(request.getParameter("x2"));
        int y2 = Integer.parseInt(request.getParameter("y2"));
        int boundx = Integer.parseInt(request.getParameter("boundx"));
        int boundy = Integer.parseInt(request.getParameter("boundy"));
        //获得图片地址
        String rootPath = this.getClass().getResource("/").getPath();
        rootPath = rootPath.substring(1, rootPath.indexOf("/WEB-INF"));
        String picName = UUID.randomUUID().toString();

        //获取文件名
        String oriName = multipartFile.getOriginalFilename();
        //获取文件后缀
        String extName = oriName.substring(oriName.lastIndexOf("."));
        String uploadPath = rootPath + FILEPATH_IMG + picName + extName;
        System.out.println("uploadPath====" + uploadPath);
        float srcImageHeight;
        float srcImageWidth;
        try {
            File saveFile = new File(uploadPath);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            multipartFile.transferTo(saveFile);
            BufferedImage bufferedImage = ImageIO.read(new File(uploadPath));
            srcImageHeight = bufferedImage.getHeight();
            srcImageWidth = bufferedImage.getWidth();
            float scaleX = srcImageWidth / boundx;
            float scaleY = srcImageHeight / boundy;
            int rx = (int) (x * scaleX);
            int ry = (int) (y * scaleY);
            int rx2 = (int) (x2 * scaleX);
            int ry2 = (int) (y2 * scaleY);
            String cropImgPath = rootPath + FILEPATH_IMG + picName + "_crop" + extName;
            Thumbnails.of(uploadPath)
                    .sourceRegion(rx, ry, rx2, ry2)
                    .size(rx2 - rx, ry2 - ry)
                    .keepAspectRatio(false)
                    .toFile(cropImgPath);
            model.addAttribute("filePath", request.getContextPath() + FILEPATH_IMG + picName + "_crop" + extName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("异常===" + e.getMessage());
        }

        return "forward:/view/uploadShow.jsp";
    }

}

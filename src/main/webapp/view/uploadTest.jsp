<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>文件上传</title>


    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/Jcrop/css/jquery.Jcrop.css" type="text/css" />
    <style type="text/css">
        .jcrop-holder #preview-pane {
            display: block;
            position: absolute;
            z-index: 2000;
            top: 10px;
            right: -280px;
            padding: 6px;
            border: 1px rgba(0,0,0,.4) solid;
            background-color: white;

            -webkit-border-radius: 6px;
            -moz-border-radius: 6px;
            border-radius: 6px;

            -webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
            -moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
            box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
        }

        #preview-pane .preview-container {
            width: 196px;
            height: 196px;
            overflow: hidden;
        }
    </style>

</head>
<body>
<form name="serForm" action="${pageContext.request.contextPath}/upload/fileUpload1.action" method="post"  enctype="multipart/form-data">
    <h1>采用multipart提供的file.transfer方法上传文件</h1>
    <input type="file" name="file">
    <input type="hidden" name="id" value="1">
    <input type="submit" value="upload"/>
</form>

<form name="Form2" action="${pageContext.request.contextPath}/upload/fileUpload2.action" method="post"  enctype="multipart/form-data">
    <h1>采用流的方式上传文件</h1>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>

<form name="Form3" action="${pageContext.request.contextPath}/upload/imgUpload.action" method="post"  enctype="multipart/form-data">
    <h1>图片裁剪上传</h1>
    <div >
    <input type="file" name="file" id="pic_choice" value="选择图片">
    <img src="${pageContext.request.contextPath}/statics/Jcrop/demos/demo_files/sago.jpg" height="200px" id="target" alt="[Jcrop Example]" />
    </div>
    <input id="x" type="hidden" name="x" >
    <input  id="y"type="hidden" name="y">
    <input id="x2"type="hidden" name="x2">
    <input id="y2"type="hidden" name="y2">
    <input id="boundx"type="hidden" name="boundx">
    <input id="boundy"type="hidden" name="boundy">
    <input type="submit" value="上传"/>
</form>

<%--<form name="Form2" action="${pageContext.request.contextPath}/upload/springUpload3.action" method="post"  enctype="multipart/form-data">
    <h1>使用spring mvc提供的类的方法上传文件</h1>
    <input type="file" name="file">
    <input type="submit" value="upload"/>
</form>--%>



<script src="${pageContext.request.contextPath}/statics/Jcrop/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/Jcrop/js/jquery.Jcrop.js"></script>

<script type="text/javascript">

    jQuery(function ($) {
        $('#pic_choice').change(function(event) {//JCrop和图片预览存在冲突
            destoryJCrop();
            // 根据这个 <input> 获取文件的 HTML5 js对象
            var files = event.target.files, file;
            if (files && files.length > 0) {
                // 获取目前上传的文件
                file = files[0];
                // 获取window的 URL工具
                var URL = window.URL || window.webkitURL;
                // 通过 file生成目标 url
                var imgURL = URL.createObjectURL(file);
                $('#target').attr('src', imgURL);
                //图片选择完成后再设置JCrop
                initJCrop();
            }
        });

    });
    var isInitJCrop = false;
    var jcrop_api;
    function initJCrop(){
        var boundx;
        var boundy;
        $('#target').Jcrop({
            onChange: showInfo,
            aspectRatio: 1//框的比例
        }, function () {
            var bounds = this.getBounds();
            boundx = bounds[0];
            boundy = bounds[1];
            $("#boundx").val(boundx);
            $("#boundy").val(boundy);
            jcrop_api = this;
            jcrop_api.animateTo([100,100,296,296]);
        });
        function showInfo(c) {
            $("#x").val(c.x);
            $("#y").val(c.y);
            $("#x2").val(c.x2);
            $("#y2").val(c.y2);

            console.log("c.x："+c.x+" c.y："+c.y+" c.x2："+c.x2+"  c.y2："+c.y2
                +"  boundx："+boundx+"  boundy："+boundy);

        };
        isInitJCrop = true;
    }
    function destoryJCrop(){
        if(isInitJCrop){
            jcrop_api.destroy();
            isInitJCrop = false;
        }
    }

</script>
</body>
</html>
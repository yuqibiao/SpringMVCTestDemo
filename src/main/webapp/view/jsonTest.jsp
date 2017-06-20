<%--
  Created by IntelliJ IDEA.
  User: yu
  Date: 2017/6/20
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SpringMVC与JSON交互</title>
    <script type="application/javascript"
            src="${pageContext.request.contextPath}/plugin/jquery/jquery-3.2.1.js"></script>
    <style>
        .wrapper {
            background: #fefefe;
            margin: 0 auto;
            width: 780px;
        }

        button {
            width: 100px;
            height: 30px;
            line-height: 30px;
            color: #fff;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            background: #2697f5;
        }

        button :hover {
            background-color: #1380d9;
        }
    </style>
</head>

<body>

<div class="wrapper">
    <ul id="show_data">显示数据</ul>
    <button id="get_data" class="get_data">得到数据</button>
</div>


<script>

    $(function () {
        $("#get_data").bind("click", function () {
            $.ajax({
                url: "${pageContext.request.contextPath}/json/getJsonData.action",
                data: '{"username":"yyyu","pwd":"123"}',
                type: "post",
                dataType: "json",
                contentType: 'application/json;charset=utf-8',
                success: function (data) {
                   /* $("#show_data").append("<li>" + JSON.stringify(data)+ "</li>");*/
                    $("#show_data").append("<li>" + data.user.username+ "</li>");
                    $("#show_data").append("<li>" + data.otherPrams+ "</li>");
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert("Error")
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        })
    });

</script>

</body>
</html>

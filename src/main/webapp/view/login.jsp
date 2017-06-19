<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html >
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/statics/css/login.css"/>
    <title>登录界面</title>
</head>
<body>

<div class="header">
    <div class="title_logo" ><img  src="${pageContext.request.contextPath}/img/title.jpg"  height="100%"/></div>
    <p class="tel">服务热线：027-87003631</p>
</div>

<div class="wrapper_bg">

<div class="main">

    <div class="loginL">
        <img  src="${pageContext.request.contextPath}/img/main.jpg" width="100%" height="100%">
    </div>
    <div class="loginR" >
            <p class="tip">用户登录</p>
        <form action="check.action" method="post">
            <div>
                <label for="username">用户名：</label>
                <input type="text" id="username" name="username"  placeholder="请输入用户名"/>
            </div>
            <div>
                <label for="pwd">密　码：</label></label>
                <input type="password" id="pwd" name="pwd" placeholder="请输入密码"/>
            </div>
            <div>
                <label for="submit_login">　　　　</label>
                <button class="button" id="submit_login" name="submit_login" type="submit">登录</button>
            </div>
        </form>
        </ul>
    </div>
</div>

</div>

<div class="footer">
    <p>Copyright ©2000-2015 **有限公司 - 京ICP备15057190号</p>
</div>



</body>
</html>
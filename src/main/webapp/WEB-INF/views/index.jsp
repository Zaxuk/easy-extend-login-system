<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<script src="http://ku-res.17wo.cn/wap-video-storm/scripts/jquery-1.8.2.min.js" type="text/javascript"></script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h2>Hello, ${user.name}</h2>
<div>
    <form action="login" method="post">
        <ul>
            用户名：<input type="text" name="userName"/>
        </ul>
        <ul>
            密码：<input type="password" name="password"/>
        </ul>
        <ul>
            <input type="submit" value="提交"/>
        </ul>
        <ul>${message}</ul>
    </form>
</div>
</body>
</html>
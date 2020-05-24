<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加老师</title>
</head>
<body>
<h1>老师信息录入</h1>
<form action="/stu38/addteacher" method="post">
    工号：<input type="text" name="tid"><br>
    姓名：<input type="text" name="tname"><br>
    密码：<input type="password" name="password"><br>
    <input type="reset" value="重置">
    <input type="submit" value="添加">
</form>
</body>
</html>

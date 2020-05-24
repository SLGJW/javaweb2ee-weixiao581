<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生</title>
</head>
<body>
<h1>学生信息录入</h1>
<form action="/stu38/addstudent" method="post">
    学号：<input type="text" name="sid"><br>
    姓名：<input type="text" name="sname"><br>
    密码：<input type="password" name="password"><br>
    <input type="reset" value="重置">
    <input type="submit" value="添加">
</form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息录入</title>
</head>
<body>
<h1>学生信息录入</h1>
<form action="show.jsp" method="post">
    学号：<input type="text" name="sid"><br>
    姓名：<input type="text" name="sname"><br>
    性别：男<input type="radio" name="ssex" value="男">&nbsp;
            女<input type="radio" name="ssex" value="女"><br>
    <input type="reset" value="重置">
    <input type="submit" value="提交">
</form>
</body>
</html>

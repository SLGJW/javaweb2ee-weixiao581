<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息显示</title>
</head>
<% request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="student" scope="page" class="com.org.StudentBean" />
<jsp:setProperty name="student" property="*" />
<body>
<h1>学生信息获取</h1>
<h2>
    学号： <jsp:getProperty name="student" property="sid"/> <br>
    姓名： <jsp:getProperty name="student" property="sname"/> <br>
    性别： <jsp:getProperty name="student" property="ssex"/> <br>
</h2>
</body>
</html>

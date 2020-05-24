<%@ page import="com.util.MyUtil" %>
<%@ page import="com.domain.Student" %>
<%@ page import="com.dao.DAOFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息修改</title>
</head>
<body>
<h1>学生信息修改</h1>
<%
    request.setCharacterEncoding("utf-8");
    String Sid = request.getParameter("update");
    int sid = MyUtil.toInt(Sid);
    try {
        Student student = MyUtil.getStudent(sid);
        %>
        <form action="/stu38/updatestudent" method="get">
            学号：<input type="text" value="<%=student.getSid()%>" disabled><br>
            <input type="hidden" name="sid" value="<%=student.getSid()%>">
            姓名：<input type="text" name="sname" value="<%=student.getSname()%>"><br>
            密码：<input type="text" name="password" value="<%=student.getPassword()%>"><br>
            <input type="reset" value="重置">
            <input type="submit" value="提交">
        </form>
        <%
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
</body>
</html>

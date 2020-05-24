<%@ page import="com.util.MyUtil" %>
<%@ page import="com.domain.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师信息修改</title>
</head>
<body>
<h1>教师信息修改</h1>
<%
    request.setCharacterEncoding("utf-8");
    String Tid = request.getParameter("update");
    int tid = MyUtil.toInt(Tid);
    try {
        Teacher teacher = MyUtil.getTeacher(tid);
        %>
        <form action="/stu38/updateteacher" method="get">
            工号：<input type="text" value="<%=teacher.getTid()%>" disabled><br>
            <input type="hidden" name="tid" value="<%=teacher.getTid()%>">
            姓名：<input type="text" name="tname" value="<%=teacher.getTname()%>"><br>
            密码：<input type="text" name="password" value="<%=teacher.getPassword()%>"><br>
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

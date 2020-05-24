<%@ page import="com.domain.Course" %>
<%@ page import="com.util.MyUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除课程</title>
</head>
<script>
    <%
    request.setCharacterEncoding("utf-8");
    String Cid = request.getParameter("delete");
    int cid = MyUtil.toInt(Cid);
    Course course = MyUtil.getCourse(cid);
    %>
    onload = function () {
        var flag = confirm("是否删除课程编号为<%=course.getCid()%>，课程名为：<%=course.getCname()%>的课程信息？")
        if(flag==true){
            var form =document.getElementById("form");
            form.submit();
        }
    }
</script>
<body>
<form id="form" action="/stu57/deletecourse" method="post">
    <input type="hidden" name="cid" value="<%=course.getCid()%>">
</form>
</body>
</html>

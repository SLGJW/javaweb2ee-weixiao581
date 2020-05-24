<%@ page import="com.util.MyUtil" %>
<%@ page import="com.domain.Teacher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除教师信息</title>
</head>
<script>

    <%
    request.setCharacterEncoding("utf-8");
    String Tid = request.getParameter("delete");
    int tid = MyUtil.toInt(Tid);
    Teacher teacher = MyUtil.getTeacher(tid);
    %>
    onload = function () {
        var flag = confirm("是否删除工号为<%=teacher.getTid()%>，姓名为：<%=teacher.getTname()%>的教师信息？")
        if(flag==true){
            var form =document.getElementById("form");
            form.submit();
        }
    }
</script>
<body>
<form id="form" action="/stu38/deleteteacher" method="get">
    <input type="hidden" name="tid" value="<%=teacher.getTid()%>">
</form>
</body>
</html>

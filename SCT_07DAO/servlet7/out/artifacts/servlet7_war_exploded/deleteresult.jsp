<%@ page import="java.util.Date" %>
<%@ page import="com.utli.MyUtil" %>
<%@ page import="com.domain.Student" %>
<%@ page import="com.dao.StudentDao" %>
<%@ page import="com.dao.DAOFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>删除结果</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String sid = request.getParameter("sid");
    StudentDao sd = DAOFactory.getStudentDaoInstance();
    Student student = sd.findBySid(sid);
%>
    <script>
    onload = function () {
        var flag = confirm("是否删除学号为：<%=student.getSid()%>，姓名为：<%=student.getSname()%>，出生日期为：<%=student.getSbirthday()%>，性别为：<%=student.getSsex()%>的学生信息?");
        if(flag==true) {
            var result = document.getElementById("inp").value;
        <%
            boolean flag = sd.dodelete(student);
            String jieguo = flag==true?"删除成功":"删除失败";
        %>
        alert(result);
        location.href = "/stu38/CRUD.jsp";
        }else{
        location.href = "/stu38/CRUD.jsp";
        }
    }
</script>
<input type="hidden" name="flag" value="<%=jieguo%>" id="inp">
</body>
</html>

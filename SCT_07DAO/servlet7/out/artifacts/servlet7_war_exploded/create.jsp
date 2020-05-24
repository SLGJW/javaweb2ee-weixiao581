<%@ page import="com.domain.Student" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.utli.MyUtil" %>
<%@ page import="com.dao.StudentDao" %>
<%@ page import="com.dao.DAOFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加结果</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    String sid = request.getParameter("sid");
    String sname = request.getParameter("sname");
    String sbirthday = request.getParameter("sbirthday");
    String ssex = request.getParameter("ssex");
    Date birthday = MyUtil.toDate(sbirthday);
    Student student = new Student(sid,sname,birthday,ssex);
    StudentDao sd = DAOFactory.getStudentDaoInstance();
    boolean flag = sd.doCreate(student);
    String jieguo = flag==true?"添加成功":"添加失败";
%>
<input type="hidden" name="flag" value="<%=jieguo%>" id="inp">
</body>
</html>
<script>
    onload = function () {
        var result = document.getElementById("inp").value;
        alert(result);
        location.href="/stu38/CRUD.jsp";
    }
</script>

<%@ page import="com.dao.StudentDao" %>
<%@ page import="com.dao.DAOFactory" %>
<%@ page import="com.domain.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询</title>
</head>
<body>
<%
    request.setCharacterEncoding("UTF-8");
    StudentDao sd = DAOFactory.getStudentDaoInstance();
    String sid = request.getParameter("sid");
    Student student = null;
    List<Student> results = null;
    if(sid!=null) {
        student = sd.findBySid(sid);
    }
    String sname = request.getParameter("sname");
    if(sname!=null){
        results = sd.findBySname(sname);
    }
    String sbirthday = request.getParameter("sbirthday");
    if(sbirthday!=null){
        results = sd.findBySbirthday(sbirthday);
    }
    String ssex = request.getParameter("ssex");
    if(ssex!=null){
        results = sd.findBySsex(ssex);
    }
%>
<table align="center" width="400px" cellspacing="0px" border="1px" bgcolor="aqua">
<tr>
    <td>学号</td>
    <td>姓名</td>
    <td>出生日期</td>
    <td>性别</td>
</tr>
    <%
    if(student!=null){%>
    <tr>
        <td><%=student.getSid()%></td>
        <td><%=student.getSname()%></td>
        <td><%=student.getSbirthday()%></td>
        <td><%=student.getSsex()%></td>
    </tr>
    <%}
    if(results!=null){
        for(Student stu :results){%>
            <tr>
                <td><%=stu.getSid()%></td>
                <td><%=stu.getSname()%></td>
                <td><%=stu.getSbirthday()%></td>
                <td><%=stu.getSsex()%></td>
            </tr>
        <%}
    }%>
</table>
<a href="/stu38/CRUD.jsp" style="font-size: 30px" style="text-align: center">返回</a>
</body>
</html>

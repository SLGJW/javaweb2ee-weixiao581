<%@ page import="com.domain.Student" %>
<%@ page import="com.dao.DAOFactory" %>
<%@ page import="com.domain.Course" %>
<%@ page import="com.domain.Teacher" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="org.apache.commons.dbutils.QueryRunner" %>
<%@ page import="com.mysql.cj.protocol.a.MysqlBinaryValueDecoder" %>
<%@ page import="com.util.MyUtil" %>
<%@ page import="org.apache.commons.dbutils.handlers.BeanHandler" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生界面</title>
</head>
<script>
    onload = function () {
        <%
        if(request.getAttribute("result")!=null){
            String message = (String)request.getAttribute("result");
            request.removeAttribute("result");
            %>
            alert(<%=message%>);
        <%
        }
        %>
    }
    function cencel() {
        var name = document.getElementById("coursename").value;
        var flag = confirm("是否取消"+name+"这门选课");
        if(flag==true){
            var form = document.getElementById("form");
            form.submit();
        }
    }
</script>
<body>
<%
    if(request.getAttribute("info")!=null){
        int sid = (int)request.getAttribute("info");
        QueryRunner qr = MyUtil.getDB();
        String sql = "select * from student where sid=?";
        Student student = qr.query(sql, new BeanHandler<>(Student.class),sid);
        Map<Course, Teacher> map = DAOFactory.getStudentDaoInstance().findCandT(student);
        Set<Course> courses = map.keySet();
        %>
<table align="center" width="400px" cellspacing="0px" border="1px">
    <tr>
        <td>课程名</td>
        <td>任课教师</td>
        <td>取消选课</td>
    </tr>
<%
        for(Course course : courses){
            %>
        <tr>
            <td><%=course.getCname()%></td>
            <td><%=map.get(course).getTname()%></td>
            <td>
                <form id="form" action="/stu38/cencelcourseservlet" method="get">
                    <input type="hidden" name="course" value="<%=course.getCid()%>">
                    <input type="hidden" name="student" value="<%=student.getSid()%>">
                    <input id="coursename" type="hidden" value="<%=course.getCname()%>">
                    <input type="button" value="取消" onclick="cencel()">
                </form>
            </td>
        </tr>
        <%}%>
</table>
<form action="/stu38/addcourse.jsp" method="post">
    <input type="hidden" name="student" value="<%=student.getSid()%>">
    <input type="submit" value="添加课程">
</form>
    <%}%>
</body>
</html>

<%@ page import="com.dao.DAOFactory" %>
<%@ page import="com.domain.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.util.MyUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加课程</title>
</head>
<body>
<h1>课程信息录入</h1>
<form action="/stu38/addcourse" method="post">
    课程编号：<input type="text" name="cid"><br>
    课程名：<input type="text" name="cname"><br>
    任课教师：<select name="course_teacher">
                <%
                    List<Course> courses = DAOFactory.getUserDaoInstance().selectCourse();
                    for(Course course : courses){
                    %>
                    <option value="<%=course.getCourse_teacher()%>"><%=MyUtil.getTeacherName(course)%></option>
                    <%
                    }
                %>
            </select>
    <input type="reset" value="重置">
    <input type="submit" value="添加">
</form>
</body>
</html>

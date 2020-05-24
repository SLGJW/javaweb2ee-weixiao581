<%@ page import="com.util.MyUtil" %>
<%@ page import="com.domain.Course" %>
<%@ page import="com.dao.DAOFactory" %>
<%@ page import="com.domain.Teacher" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改课程</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String Cid = request.getParameter("update");
    int cid = MyUtil.toInt(Cid);
    try {
        Course course = MyUtil.getCourse(cid);
%>
<form action="/stu38/updatecourse" method="get">
    课程编号：<input type="text" name="cid" value="<%=course.getCid()%>"><br>
    <input type="hidden" name="oldcid" value="<%=course.getCid()%>">
    课程名：<input type="text" name="cname" value="<%=course.getCname()%>"><br>
    任课教师：
    <%
        String teacherName = MyUtil.getTeacherName(course);
        List<Teacher> teachers = DAOFactory.getUserDaoInstance().selectTeacher();
    %>
        <select name="course_teacher">
    <%
        for(Teacher t : teachers){
            if(teacherName.equals(t.getTname())){
                %>
            <option value="<%=t.getTid()%>" selected><%=t.getTname()%></option>
                <%
            }else{
                %>
            <option value="<%=t.getTid()%>"><%=t.getTname()%></option>
                <%
            }
        }
    %>
        </select><br>
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

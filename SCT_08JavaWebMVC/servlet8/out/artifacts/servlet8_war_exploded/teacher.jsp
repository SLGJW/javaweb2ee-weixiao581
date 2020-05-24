<%@ page import="com.dao.DAOFactory" %>
<%@ page import="com.domain.Teacher" %>
<%@ page import="com.domain.Course" %>
<%@ page import="java.util.List" %>
<%@ page import="com.domain.Student" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="org.apache.commons.dbutils.QueryRunner" %>
<%@ page import="com.util.MyUtil" %>
<%@ page import="org.apache.commons.dbutils.handlers.BeanHandler" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师界面</title>
</head>
<body>
<%
    if(request.getAttribute("info")!=null){
        int tid = (int)request.getAttribute("info");
        QueryRunner qr = MyUtil.getDB();
        String sql = "select * from teacher where tid=?";
        Teacher tea = qr.query(sql, new BeanHandler<>(Teacher.class),tid);
        String tname = tea.getTname();
        String ps = tea.getPassword();
        int p = tea.getPermissions();
        Teacher teacher = new Teacher(tid,tname,ps,p);
        Map<Course,List<Student>> teachStudents = DAOFactory.getTeacherDaoInstance().findTeachStudents(teacher);
    %>
<h1>您好！<%=teacher.getTname()%></h1>
<h2>您所教授的所有课程信息为</h2>
<%
    Set<Course> set = teachStudents.keySet();
    for(Course course : set){
        List<Student> list = teachStudents.get(course);
%>
<p>
        课程名：<%=course.getCname()%><br>
        <%
            for(Student s : list){
                if(s!=null){
                    %>
                        学生：<%=s.getSname()%><br>
                    <%
                }
            }
        %>
</p>
    <%}%>
<%}%>
</body>
</html>

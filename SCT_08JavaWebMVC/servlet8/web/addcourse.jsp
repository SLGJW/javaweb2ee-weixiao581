<%@ page import="org.apache.commons.dbutils.QueryRunner" %>
<%@ page import="com.util.MyUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.dbutils.handlers.ArrayListHandler" %>
<%@ page import="com.dao.DAOFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加选课</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String studentid = request.getParameter("student");
    int sid = MyUtil.toInt(studentid);
    List<Object[]> list = DAOFactory.getStudentDaoInstance().findNot(sid);
%>
<form method="post" action="/stu38/addcourseservlet">
    <select name="course">
        <option disabled selected><%="\t"%>课程名<%="\t"%>任课教师<%="\t"%></option>
        <%
        for(Object[] objs : list){
        %>
        <option value="<%=objs[0]%>"><%="\t"%><%=objs[1]%><%="\t"%><%=objs[2]%></option>
        <%
        }%>
    </select><br />
    <input type="hidden" name="student" value="<%=sid%>">
    <input type="submit" value="添加"/>
</form>
</body>
</html>

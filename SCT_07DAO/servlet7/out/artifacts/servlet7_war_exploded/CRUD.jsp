<%@ page import="java.util.List" %>
<%@ page import="com.domain.Student" %>
<%@ page import="com.dao.StudentDao" %>
<%@ page import="com.dao.DAOFactory" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据库的增删改查</title>
    <style>
        div {
            width: 500px;
            background-color: gold;
            margin: 0 auto;
        }

        a:link {
            color: blue;
        }

        a:hover {
            color: red;
        }

        a:active {
            color: purple;
        }

        a:visited {
            color: green;
        }
    </style>
</head>
<script>
    onload = function () {
        <% if(request.getAttribute("message")!=null){
            String result = (String)request.getAttribute("message");
        %>
        alert(<%=result%>);
        <%
            request.removeAttribute("create");
        }%>
    }
</script>
<% String name = (String) session.getAttribute("user");
    if (name != null) {%>
    
<body>
<div style="text-align: center">
    <h1>学生列表</h1>
    <a href="/stu38/create.html">增添学生</a>&nbsp;&nbsp;
    <a href="/stu38/retrieve.html">查找学生</a><br>
    <%
        StudentDao sd = DAOFactory.getStudentDaoInstance();
        List<Student> all = sd.findAll();
        if (all != null) {%>
    <table align="center" width="400px" cellspacing="0px" border="1px" bgcolor="aqua">
        <tr>
            <td>学号</td>
            <td>姓名</td>
            <td>出生日期</td>
            <td>性别</td>
            <td colspan="2">操作</td>
        </tr>
        <%
            for (Student stu : all) {
        %>
        <tr>
            <td><%=stu.getSid()%>
            </td>
            <td><%=stu.getSname()%>
            </td>
            <td><%=stu.getSbirthday()%>
            </td>
            <td><%=stu.getSsex()%>
            </td>
            <td>
                <form action="/stu38/deleteresult.jsp" method="post">
                    <input type="hidden" id="d" value="<%=stu.getSid()%>" name="sid">
                    <input type="submit" value="删除">
                </form>
            </td>
            <td>
                <form action="/stu38/update.jsp" method="post">
                    <input type="hidden" id="u" value="<%=stu.getSid()%>" name="sid">
                    <input type="submit" value="修改">
                </form>
            </td>
        </tr>
        <%}%>
    </table>
    <%}%>

    <h3>点击此处<a href="/stu38/logoutServlet">【注销管理员登录】</a></h3>
</div>
</body>
<%} else { %>
<%
    request.setAttribute("unlogin", "请先登录");
    request.getRequestDispatcher("/index.jsp").forward(request, response);
%>
    <%} %>
</html>
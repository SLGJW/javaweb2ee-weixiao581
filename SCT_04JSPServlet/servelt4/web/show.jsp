<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>信息展示</title>
  </head>
  <body>

  <%
    request.setCharacterEncoding("utf-8");
    String sid = request.getParameter("sid");
    String sname = request.getParameter("sname");
    String birthday = request.getParameter("birthday");
    String sex = request.getParameter("sex");
    %>
    <h3>
        学号：<%=sid%><br>
        姓名：<%=sname%><br>
        出生：<%=birthday%><br>
        性别：<%=sex%>
    </h3>
  </body>
</html>

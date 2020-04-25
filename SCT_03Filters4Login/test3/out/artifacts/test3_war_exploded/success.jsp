<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
    <% String name=(String)session.getAttribute("user");
        if(name!=null){%>
            <body>
                <h1><%=request.getSession().getAttribute("user")%>欢迎您!</h1>
                <img src="/img/1.png"><br>
                <h3>点击此处<a href="/stu38/logoutServlet">【注销】</a></h3>
            </body>
        <%}else{ %>
            <%request.setAttribute("unlogin", "请先登录");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
             %>
        <%} %>
</html>

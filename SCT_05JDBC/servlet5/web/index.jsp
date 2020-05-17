<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>管理员登录</title>
    <style>
      div{
        color:red;
      }
    </style>
  </head>
  <body>
  <h1>管理员登录</h1>
    <form action="/stu38/loginServlet" method="post">
      登录名：<input type="text" name="username"><br />
      密码：<input type="password" name="password"><br />
      <input type="reset" value="重置">
      <input type="submit" value="登录">
    </form>
    <div><%=request.getAttribute("loginlose")==null?"":request.getAttribute("loginlose")%></div>
    <div><%=request.getAttribute("logoutsuccess")==null?"":request.getAttribute("logoutsuccess")%></div>
    <div><%=request.getAttribute("unlogin")==null?"":request.getAttribute("unlogin")%></div>
  </body>
</html>

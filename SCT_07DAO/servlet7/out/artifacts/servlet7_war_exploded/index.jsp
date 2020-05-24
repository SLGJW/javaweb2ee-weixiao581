<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>管理员登录</title>
  <style>
    div1 {
      color: red;
    }
    p {
      color: blue;
      font-size: 30px;
      font-weight: bold;
    }
    div {
      width: 300px;
      background-color: pink;
      margin: 0 auto;
    }
    .inp2 {
      width: 150px;
    }
  </style>
</head>
<body>
<div style="text-align: center;">
  <p>管理员登录</p>
  <form width:200px action="/stu38/loginServlet" method="post">
    登录名：<input type="text" class="inp2" name="username"><br/><br/>
    密码：&nbsp;&nbsp;&nbsp;<input type="password" class="inp2" name="password"><br/><br/>
    <input id="reset" type="reset" value="重置">
    <input id="submit" type="submit" value="登录">
  </form>
  <div1><%=request.getAttribute("loginlose") == null ? "" : request.getAttribute("loginlose")%>
  </div1>
  <div1><%=request.getAttribute("logoutsuccess") == null ? "" : request.getAttribute("logoutsuccess")%>
  </div1>
  <div1><%=request.getAttribute("unlogin") == null ? "" : request.getAttribute("unlogin")%>
  </div1>
</div>
</body>
</html>

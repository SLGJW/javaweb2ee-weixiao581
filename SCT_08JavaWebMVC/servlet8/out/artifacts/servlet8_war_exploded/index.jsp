<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录界面</title>
    <style>
      div{
        color: red;
      }
    </style>
  </head>
  <script>
    change=function(){
      var a = document.getElementById("div1");
      var b = document.getElementById("select");
      if(b.value=="student"){
        a.innerHTML="学生的登录名为学号";
      }
      if(b.value=="teacher"){
        a.innerHTML="教师的登录名为工号";
      }
      if(b.value=="user"){
        a.innerHTML="管理员的登录名为管理员账号";
      }
    }
  </script>
  <body>
  <h1>用户登录</h1>
  <form action="/stu38/loginservlet" method="post">
    身份：
    <select id="select" name="identity" onchange="change()">
      <option disabled selected>请选择您的身份</option>
      <option value="student">学生</option>
      <option value="teacher">教师</option>
      <option value="user">管理员</option>
    </select><br>
    <div id="div1"></div>
    登录名：<input type="text" name="username"><br>
    口令：<input type="password" name="password"><br>
    <input type="reset" value="重置">
    <input type="submit" value="登录">
  </form>
  <div><%=request.getAttribute("info")==null?"":request.getAttribute("info")%></div>
  </body>
</html>

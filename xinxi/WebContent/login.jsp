<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//获取所有的cookie  结果是一个数组
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			//遍历
			for(Cookie c : cookies) {
				//将cookie放到作用域中
				request.setAttribute(c.getName(), c.getValue());
			}
		}
		
	%>
	<h2>登录页面</h2>
	<span style="color:red;">${msg }</span>
	<form action="userServlet" >
		<input type="hidden" name="mark" value="loginUser">
		用户名:<input type="text" name="username" value="${username }"><br>
		密码:<input type="text" name="password" value="${password }"><br>
		
		<input type="submit" value="登录">
	</form>
</body>
</html>
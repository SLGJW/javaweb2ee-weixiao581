<%@page import="com.ujiuye.bean.User"%>
<%@page import="com.ujiuye.bean.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	//就绪函数
	$(function() {
		//实现全选和全不选
		//获取第一个复选框的选中状态，把下面所有的复选框的选中状态跟第一个保持一致
		
		//给第一个复选框一个点击事件
		$("#inp1").click(function() {
			//获取选中状态
			var b = $(this).prop("checked");
			$(".inps").prop("checked",b);
		});
		
		//给批量删除一个点击事件
		$("#btn01").click(function() {
			if (confirm("你确定要批量删除吗")) {
				var str = "";
				//获取除第一个input框被选中的value值
				$(".inps").each(function() {
					//判断每一个input框是否被选中
					if ($(this).prop("checked")) {
						//获取value值进行拼接
						str = str + "," + $(this).val();
					}
				});
				//说明一个也没有选择
				if (str == "") {
					alert("你没有选择");
					return;
				}
				//将前面的逗号去掉
				str = str.substring(1);
				//请求后台进行批量删除
				window.location.href="studentServlet?mark=batchDeleteStudent&sids=" + str;
			}
		});
	});
</script>
</head>
<%
	//获取session作用域的值
	User u = (User)session.getAttribute("user");
	if(u == null) {
		//重定向到登录页面进行的登录
		response.sendRedirect("login.jsp");
	}
%>
<body>
	<%-- <%
		String msg = (String) request.getAttribute("msg");
		if(msg != null) {
	%>
	
	<script type="text/javascript">
		alert("添加成功");
	</script>
	<%
			
		}
	%> --%>
	<script type="text/javascript">
		var c;
		window.onload = function() {
			c = window.setInterval(show,1000);
		}
		function show() {
			var msg = '${msg}';
			if (msg != null && msg != "") {
				alert("添加成功");
				//清除定时器
				window.clearInterval(c);
			}
		}
		
		 
			
		
	</script>
		
	<!-- 显示学生信息 -->
	欢迎嘴强王者${user.username }上线   <a href="userServlet?mark=quitUser">退出</a><br>
	
	
	
	
	<a href="insertStudent.html">添加学生</a>
	<button id="btn01">批量删除</button>
	<br>
	<form action="studentServlet">
		<input type="hidden" name="mark" value="dimQueryStudent">
		姓名:<input type="text" name="sname" value="${db.sname }">
		起始年龄:<input type="text" name="startAge" value="${db.startAge }">
		结束年龄:<input type="text" name="endAge"  value="${db.endAge }" >
		<input type="submit" value="模糊查询">
	
	</form>
	<table width="800px" border=1>
		<tr>
			<th>
				<input id="inp1" type="checkbox" >
			</th>
			<th>学号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>性别</th>
			<th>班级</th>
			<th>爱好</th>
			<th>学历</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${lists }" var="stu">
			<tr>
				<th>
					<input type="checkbox" class="inps" value="${stu.sid }">
				</th>
				<th>${stu.sid }</th>
				<th>${stu.sname }</th>
				<th>${stu.age }</th>
				<th>${stu.sex == 1 ? '男' : '女' }</th>
				<th>${stu.sclass }</th>
				<th>${stu.shobby }</th>
				<th>${stu.edu }</th>
				<th>
					<a href="studentServlet?mark=deleteStudentBySid&sid=${stu.sid }">删除</a>
					||
					<!-- 通过学生主键sid，查询到该条学生信息，回显到修改的页面 -->
					<a href="studentServlet?mark=queryStudentBySid&sid=${stu.sid }">修改</a>
				</th>
			</tr>
		</c:forEach>
		
		
	</table>
	
	<p>
		<a href="studentServlet?mark=dimQueryStudent&currentPage=1&sname=${db.sname}">首页</a>
		<a href="studentServlet?mark=dimQueryStudent&currentPage=${pt.prePage }&sname=${db.sname}">上一页</a>
		<a href="studentServlet?mark=dimQueryStudent&currentPage=${pt.nextPage }&sname=${db.sname}">下一页</a>
		<a href="studentServlet?mark=dimQueryStudent&currentPage=${pt.totalPages }&sname=${db.sname}">尾页</a>
		当前在第  ${pt.currentPage }  页
		一共有${pt.totalSize }条数据
	</p>
</body>
</html>

 
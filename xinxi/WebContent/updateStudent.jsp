<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>修改页面</h1>
	
	
	<form action="studentServlet">
		<input type="hidden" name="mark" value="updateStudent">
		<input type="hidden" name="sid" value="${student.sid }">
		姓名:<input type="text" name="sname" value="${student.sname }"><br>
		年龄:<input type="text" name="age" value="${student.age }"><br>
		<%-- <c:if test="${student.sex == 1 }">
			性别:<input type="radio" name="sex" value="1" checked>男
			<input type="radio" name="sex" value="0">女<br>
		</c:if>
		<c:if test="${student.sex == 0 }">
			性别:<input type="radio" name="sex" value="1" >男
			<input type="radio" name="sex" value="0" checked>女<br>
		</c:if> --%>
		
		性别:<input type="radio" name="sex" value="1"  
				<c:if test="${student.sex == 1 }">checked</c:if>
			>男
			<input type="radio" name="sex" value="0"
				<c:if test="${student.sex == 0 }">checked</c:if>
			>女<br>
		
		班级:<input type="text" name="sclass" value="${student.sclass }"><br>
		
		爱好:<input type="checkbox" name="shobby" value="唱"
			 
			<c:if test="${fn:contains(student.shobby,'唱') }">checked</c:if>
		>唱
		
		<input type="checkbox" name="shobby" value="跳"
			<c:if test="${fn:contains(student.shobby,'跳') }">checked</c:if>
		>跳
		
		<input type="checkbox" name="shobby" value="rap"
			<c:if test="${fn:contains(student.shobby,'rap') }">checked</c:if>
		>rap
		<input type="checkbox" name="shobby" value="打篮球"
			<c:if test="${fn:contains(student.shobby,'打篮球') }">checked</c:if>
		>打篮球<br>
		学历:<select name="edu">
		
			<option value="初中"  <c:if test="${student.edu== '初中' }">selected</c:if> >初中</option>
			<option value="高中"  <c:if test="${student.edu== '高中' }">selected</c:if>>高中</option>
			<option value="专科"  <c:if test="${student.edu== '专科' }">selected</c:if>>专科</option>
			<option value="本科"  <c:if test="${student.edu== '本科' }">selected</c:if>>本科</option>
		</select><br>
		<input type="submit" value="确认修改"> 
	</form>
</body>
</html>
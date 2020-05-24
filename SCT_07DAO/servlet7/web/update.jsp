<%@ page import="com.dao.StudentDao" %>
<%@ page import="com.dao.DAOFactory" %>
<%@ page import="com.domain.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改学生信息</title>
    <style>
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
<%
    request.setCharacterEncoding("utf-8");
    String sid = request.getParameter("sid");
    Student student = null;
    StudentDao sd = DAOFactory.getStudentDaoInstance();
    student = sd.findBySid(sid);
    if(student!=null){
%>
<div style="text-align: center;">
    <p>学生信息修改</p>
    <form action="/stu38/updateresult.jsp" method="get">
        学号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="inp2" type="text" name="ssid" value="<%=student.getSid()%>" disabled/><br/><br/>
                    <input type="hidden" name="sid" value="<%=student.getSid()%>">
        姓名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input class="inp2" type="text" value="<%=student.getSname()%>" name="sname"/><br/><br/>
        出生日期：<input class="inp2" type="date" value="<%=student.getSbirthday().toString()%>" name="sbirthday"/><br/><br/>
        <%if("男".equals(student.getSsex())){%>
        性别：男：<input type="radio" name="ssex" value="男" checked/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        女：<input type="radio" name="ssex" value="女"/>&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
        <%}else{%>
        性别：男：<input type="radio" name="ssex" value="男"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        女：<input type="radio" name="ssex" value="女" checked/>&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
        <%}%>
        <input type="reset" value="重置"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" value="提交"/>
    </form>
</div>
<%}%>
</body>
</html>

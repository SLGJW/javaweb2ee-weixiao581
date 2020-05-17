<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mchange.v2.c3p0.ComboPooledDataSource" %>
<%@ page import="org.apache.commons.dbutils.QueryRunner" %>
<%@ page import="org.apache.commons.dbutils.handlers.ArrayHandler" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改学生信息</title>
</head>
<script>
        <%
        request.setCharacterEncoding("utf-8");
        String sid = request.getParameter("sid");
        DataSource ds = new ComboPooledDataSource();
        QueryRunner qr = new QueryRunner(ds);
        Object[] result = null;
        String sql = "select * from students where sid=?";
        result = qr.query(sql, new ArrayHandler(),sid);
        %>

        onload=function () {
        <%
        String sidvalue = (String)result[0];
        System.out.println(sidvalue);
        String snamevalue = (String)result[1];
        System.out.println(snamevalue);
        Date sbirthdayvalue = (Date)result[2];
        System.out.println(sbirthdayvalue.toString());
        String ssexvalue = (String)result[3];
        System.out.println(ssexvalue);
        %>

        var arr = new Array('<%=sidvalue%>','<%=snamevalue%>','<%=sbirthdayvalue%>','<%=ssexvalue%>');

        var n =document.getElementById('name');
        n.value=arr[1];

        var b =document.getElementById('birthday');
        b.value=arr[2];

        var s =document.getElementById('sex');
        s.value=arr[3];
    }

</script>
<body>
<h1>学生信息修改</h1>
<form action="/stu38/UpdateServlet" method="get">
    学号：<input id="sid" type="text" value="<%=sidvalue%>" name="ssid" disabled><br>
            <input type="hidden" name="sid" value="<%=sidvalue%>">
    姓名：<input id="name" type="text" value="" name="sname"><br>
    出生日期：<input id="birthday" type="date" value="" name="sbirthday"><br>
    性别：<input id="sex" type="text" value="" name="ssex"><br>
    <input type="reset" value="重置">
    <input type="submit" value="提交">
</form>
</body>
</html>

<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.mchange.v2.c3p0.ComboPooledDataSource" %>
<%@ page import="org.apache.commons.dbutils.QueryRunner" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.dbutils.handlers.ArrayListHandler" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据库的增删改查</title>
</head>
<script>
    onload = function () {
        <% if(request.getAttribute("create")!=null){
            int result = (int)request.getAttribute("create");
            if(result!=0){%>
        alert("添加成功");
        <%}else{%>
        alert("添加失败");
        <%}
        request.removeAttribute("create");
    }%>

        <% if(request.getAttribute("update")!=null){
            int result = (int)request.getAttribute("update");
            if(result!=0){%>
        alert("修改成功");
        <%}else{%>
        alert("修改失败");
        <%}
        request.removeAttribute("update");
    }%>

        <% if(request.getAttribute("delete")!=null){
            int result = (int)request.getAttribute("delete");
            if(result!=0){%>
        alert("删除成功");
        <%}else{%>
        alert("删除失败");
        <%}
        request.removeAttribute("delete");
}%>
    }

    function deletes(id) {
        var flag = confirm("是否删除?");
        if (flag) {
            return true;
        } else {
            return false;
        }
    }

    function updates(id) {
        return true;
    }

</script>
<% String name = (String) session.getAttribute("user");
    if (name != null) {%>
    
<body>
<h1>学生列表</h1>
<a href="/stu38/create.html">增添学生</a>&nbsp;&nbsp;
<a href="/stu38/retrieve.html">查找学生</a><br>
<%
    request.setCharacterEncoding("utf-8");
    DataSource ds = new ComboPooledDataSource();
    QueryRunner qr = new QueryRunner(ds);
    List<Object[]> result = null;
    String sql = "select * from students";
    result = qr.query(sql, new ArrayListHandler());
    if (result != null) {
        int i = 0;%>
<table width="400px" cellspacing="0px" border="1px">
    <tr>
        <td>学号</td>
        <td>姓名</td>
        <td>出生日期</td>
        <td>性别</td>
        <td colspan="2">操作</td>
    </tr>
    <%
        for (Object[] obj : result) {
            request.setAttribute("student" + i, obj);
    %>
    <tr>
        <%for (Object objvalue : obj) {%>
        <td><%=objvalue%>
        </td>
        <%}%>
        <td>
            <form action="/stu38/DeleteServlet" method="get" onsubmit="return deletes(this.id)">
                <input type="hidden" id="d" value="<%=obj[0]%>" name="sid">
                <input type="submit" value="删除">
            </form>
        </td>
        <td>
            <form action="/stu38/update.jsp" method="get" onsubmit="return updates(this.id)">
                <input type="hidden" id="u" value="<%=obj[0]%>" name="sid">
                <input type="submit" value="修改">
            </form>
        </td>
    </tr>
    <%
            i++;
        }
    %>
</table>
<%}%>

<h3>点击此处<a href="/stu38/logoutServlet">【注销管理员登录】</a></h3>
</body>
<%} else { %>
<%
    request.setAttribute("unlogin", "请先登录");
    request.getRequestDispatcher("/index.jsp").forward(request, response);
%>
    <%} %>
</html>

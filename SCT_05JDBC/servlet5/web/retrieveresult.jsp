<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询结果</title>
</head>
<body>
    <% if(request.getAttribute("retrieve")!=null){
        List<Object[]> result = (List<Object[]>)request.getAttribute("retrieve");
        if(result!=null){%>
            <table width="400px" cellspacing="0px" border="1px">
                <tr>
                    <td>学号</td>
                    <td>姓名</td>
                    <td>出生日期</td>
                    <td>性别</td>
                </tr>
                <%for(Object[] obj : result){%>
                <tr>
                    <%for(Object objvalue : obj){%>
                        <td><%=objvalue%></td>
                    <%}%>
                </tr>
                <%}%>
            </table>
        <%}
        request.removeAttribute("retrieve");
    }%>
    <a href="/stu38/CRUD.jsp">返回</a>
</body>
</html>

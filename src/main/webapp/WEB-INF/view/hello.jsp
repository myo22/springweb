<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023-07-20
  Time: 오후 3:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
<h1> hello !!</h1>
<%
    for(int i = 0; i < 100; i++){
        out.write("hi!!<br>"); //br은 칸넘김이다.
    }
%>
</body>
</html>

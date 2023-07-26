<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.springweb.doamin.ToDo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToDo List</title>
</head>
<body>
<h1>ToDo List</h1>
<%--<% // 그러나 jsp에서 이렇게 자바 코드를 사용하는것은 적절하지 않으므로 다른 방안--%>
<%--    // Model에 담긴 값이 (List<ToDo>)request.getAttribute("toDoList"); 을 사용하면 읽어낼 수 있다.--%>
<%--    List<ToDo> toDoList = (List<ToDo>)request.getAttribute("toDoList");--%>
<%--    for(ToDo todo : toDoList) {--%>
<%--        System.out.println(todo.getTodo());--%>
<%--    }--%>
<%--%>--%>
<c:forEach var="todo" items="${toDoList}" varStatus="status">
    <p>${todo.todo}"/></p>
</c:forEach>

<br><br>
<%--http://localhost:8080/todos/list--%>
<%--http://localhost:8080/todos/addTodo <- action="addToDo"      상대경로--%>
<%--http://localhost:8080/addToDo       <- action="/addToDo"     절대경로--%>
<form method="post" action="addToDo">
    할일 : <input type="text" name="todo">
    <input type="submit" value="추가">
</form>
</body>
</html>

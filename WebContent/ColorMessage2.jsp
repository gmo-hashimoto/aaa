<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%
	String message = request.getParameter("message");
	String color = request.getParameter("color");
%>

<style>
doby{
background-color: <%=color %>;
}
</style>
</head>
<body bgcolor="<%=color %>">


<%=message %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="person" scope="session" class="beans.Person"></jsp:useBean>
<%
	person.setName(request.getParameter("name"));
	person.setAddress(request.getParameter("address"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Registered<br>
<a href="name-address2.html">Go to main page</a>
</body>
</html>
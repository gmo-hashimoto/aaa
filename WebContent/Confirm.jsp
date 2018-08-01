<%@page import="beans.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="person" scope="session" class="beans.Person"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
regist info<br>
name: <jsp:getProperty name="person" property="name"></jsp:getProperty><br>
name: <jsp:getProperty property="address" name="person"/><br>
<a href="name-address2.html">Go to main page</a>
</body>
</html>
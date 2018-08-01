<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<% String sex = request.getParameter("sex"); %>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${param.name } is ${param.sex == "man" ? "man" : "woman" }
</body>
</html>
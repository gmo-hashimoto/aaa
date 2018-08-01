<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="product" scope="request" class="beans.Product"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Updated!</h1>
<table border="1">
	<tr>
		<th>no</th>
		<td><%=product.getNo() %></td>
	</tr>
	<tr>
		<th>name</th>
		<td><%=product.getName() %></td>
	</tr>
	<tr>
		<th>price</th>
		<td><%=product.getPrice() %></td>
	</tr>
</table>
<a href="FindProducts.jsp">TOP</a>
</body>
</html>
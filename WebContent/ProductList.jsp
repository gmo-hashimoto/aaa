<%@page import="java.util.List"%>
<%@page import="beans.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List<Product> productList = (List<Product>) request.getAttribute("productList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Product List</h1>
	<table border="1">
		<tr>
			<th>no</th>
			<th>name</th>
			<th>price</th>
		</tr>
		<%
			for (Product product : productList) {
		%>
		<tr>
			<td><%=product.getNo() %></td>
			<td><%=product.getName() %></td>
			<td><%=product.getPrice() %></td>
		</tr>
		<%
			}
		%>

	</table>
</body>
</html>
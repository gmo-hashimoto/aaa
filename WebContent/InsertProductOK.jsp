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
	<h1>Registed!</h1>
	<table border="1">
		<tr>
			<th>name</th>
			<th>price</th>
		</tr>
		<tr>
			<td><%=product.getName() %></td>
			<td><%=product.getPrice() %></td>
		</tr>
	</table>
	<a href="FindProducts.jsp">TOP</a>
</body>
</html>
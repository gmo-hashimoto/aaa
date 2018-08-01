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
	<h1>Update Infomation of Product</h1>
	<form action="UpdateProductServlet" method="post">
		<table border="1">
			<tr>
				<th>no</th>
				<td><%=product.getNo()%></td>
			</tr>
			<tr>
				<th>name</th>
				<td><input type="text" name="name"
					value="<%=product.getName()%>"></td>
			</tr>
			<tr>
				<th>price</th>
				<td><input type="text" name="price"
					value="<%=product.getPrice()%>"></td>
			</tr>
		</table>
		<button type="submit">submit</button>
		<input type="hidden" name="no" value="<%=product.getNo() %>">
	</form>
</body>
</html>
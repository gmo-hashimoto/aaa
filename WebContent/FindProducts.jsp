<%@page import="java.util.ArrayList"%>
<%@page import="beans.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	List<Product> products = (List<Product>) request.getAttribute("products");
	if (products == null) {
		products = new ArrayList<Product>();
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="include/css.jsp"%>
</head>
<body>
	<div class="container text-center">
		<h1>Find Products</h1>

		<form action="FindProductsServlet" method="post">
			<div class="text-left w-50 mx-auto">
				<div class="row">
					<div class="text-right col-3">
						<p>no:</p>
					</div>
					<div class="col-9">
						<input type="text" name="no" class="w-100">
					</div>
				</div>
				<div class="row">
					<div class="text-right col-3">
						<p>name:</p>
					</div>
					<div class="col-9">
						<input type="text" name="name" class="w-100">
					</div>
				</div>
				<div class="row">
					<div class="text-right col-3">
						<p>price:</p>
					</div>
					<div class="col-4">
						<input type="text" name="priceLow" class="w-100">
					</div>
					<div class="col-1">~</div>
					<div class="col-4">
						<input type="text" name="priceHigh" class="w-100">
					</div>
				</div>
				<div class="row">
					<div class="col text-right">
						<button type="submit" class="text-center btn btn-info">Search</button>
					</div>
					<div class="col text-left">
						<a href="/Prac/insert-product-form.html" class="btn btn-info">Regist
							Product</a>
					</div>
				</div>
			</div>
		</form>

		<%
			if (products.size() != 0) {
		%>
		<table border="1" class="mx-auto mt-3 w-50">
			<tr>
				<th>no</th>
				<th>name</th>
				<th>price</th>
				<th>Edit/Delete</th>
			</tr>
			<%
				for (Product product : products) {
			%>
			<tr>
				<td><%=product.getNo()%></td>
				<td><%=product.getName()%></td>
				<td><%=product.getPrice()%></td>
				<td>
					<form action="UpdateProductServlet" method="get">
						<input type="hidden" name="no" value="<%=product.getNo()%>">
						<button type="submit" class="btn btn-primary w-75">Edit</button>
					</form>
					<form action="DeleteProductServlet" method="get">
						<input type="hidden" name="no" value="<%=product.getNo()%>">
						<button type="submit" class="btn btn-success w-75">Delete</button>
					</form>
				</td>
			</tr>

			<%
				}
			%>
		</table>
		<%
			}
		%>
		<div class="text-left w-50 mx-auto mt-3"></div>
	</div>
</body>
</html>
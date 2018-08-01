package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Product;

public class ProductDAO {
	private Connection con;

	public ProductDAO(Connection con) {
		this.con = con;
	}

	public List<Product> findAll() throws SQLException {
		List<Product> productList = new ArrayList<Product>();

		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("select * from products");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				int price = rs.getInt("price");

				productList.add(new Product(no, name, price));
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return productList;

	}

	public List<Product> find(int no, String name, int priceLow, int priceHigh) throws SQLException {
		PreparedStatement stmt = null;
		List<Product> products = new ArrayList<Product>();
		String query = "";
		if (no != -1) {
			query += " no = " + no;
		}
		if (!name.isEmpty()) {
			if (!query.isEmpty()) {
				query += " and";
			}
			query += " name like '%" + name + "%'";
		}
		if (priceLow != -1) {
			if (!query.isEmpty()) {
				query += " and";
			}
			query += " price >= " + priceLow;
		}
		if (priceHigh != -1) {
			if (!query.isEmpty()) {
				query += " and";
			}
			query += " price <= " + priceHigh;
		}
		if (!query.isEmpty()) {
			query = " where" + query;
		}
		query = "select * from products" + query;
		System.out.println("#no: "+no+"  #name: "+name+"  #pLow: "+priceLow+"  #pHigh:"+priceHigh);
		System.out.println(query);
		try {
			stmt = con.prepareStatement(query);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				int no_ = rs.getInt("no");
				String name_ = rs.getString("name");
				int price = rs.getInt("price");

				products.add(new Product(no_, name_, price));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return products;
	}

	public Product findByNo(int no) throws SQLException {
		PreparedStatement stmt = null;
		Product product = null;
		try {
			stmt = con.prepareStatement("select * from products where no=" + no);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				product = new Product(rs.getInt("no"), rs.getString("name"), rs.getInt("price"));
			} else {
				// product = null;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		return product;
	}

	public int insert(Product product) throws SQLException {
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			stmt = con.prepareStatement("insert into products values(null, ?, ?)");

			stmt.setString(1, product.getName());
			stmt.setInt(2, product.getPrice());

			rows = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		return rows;
	}

	public int update(Product product) throws SQLException {
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			stmt = con.prepareStatement("update products set name = '"
					+ product.getName()
					+ "', price = "
					+ product.getPrice() + " where no = " + product.getNo());
			rows = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		return rows;
	}

	public int delete(int no) throws SQLException {
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			stmt = con.prepareStatement("delete from products where no = " + no);
			rows = stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}

		return rows;
	}
}

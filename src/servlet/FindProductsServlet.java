package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.Product;
import dao.ProductDAO;

/**
 * Servlet implementation class FindProductsServlet
 */
@WebServlet("/FindProductsServlet")
public class FindProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataSource source;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FindProductsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		InitialContext c = null;
		try {
			c = new InitialContext();
			source = (DataSource) c.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if (c != null) {
				try {
					c.close();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection con = null;
		String nextPage = "Error.jsp";
		int no = -1, priceLow = -1, priceHigh = -1;
		String name = "";
		try {
			no = Integer.parseInt(request.getParameter("no"));
		} catch (NumberFormatException e) {
			System.out.println("###Empty No");
		}
		name = request.getParameter("name");
		try {
			priceLow = Integer.parseInt(request.getParameter("priceLow"));
		} catch (NumberFormatException e) {
			System.out.println("###Empty priceLow");
		}
		try {
			priceHigh = Integer.parseInt(request.getParameter("priceHigh"));
		} catch (NumberFormatException e) {
			System.out.println("###Empty priceHigh");
		}

		try {
			con = source.getConnection();

			ProductDAO dao = new ProductDAO(con);

			List<Product> products = dao.find(no, name, priceLow, priceHigh);

			request.setAttribute("products", products);
			nextPage = "FindProducts.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		request.getRequestDispatcher(nextPage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

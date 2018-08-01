package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource source;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteProductServlet() {
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
		Product product = null;

		try {
			con = source.getConnection();
			ProductDAO dao = new ProductDAO(con);
			product = dao.findByNo(Integer.parseInt(request.getParameter("no")));
			if(product != null) {
				nextPage = "DeleteProductForm.jsp";
				request.setAttribute("product", product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
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
		Connection con = null;
		String nextPage = "Error.jsp";
		int rows = 0;
		int no = Integer.parseInt(request.getParameter("no"));

		try  {
			con = source.getConnection();
			ProductDAO dao = new ProductDAO(con);
			rows = dao.delete(no);
			if(rows == 1) {
				nextPage = "DeleteProductOK.jsp";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

}

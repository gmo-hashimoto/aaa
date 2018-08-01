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
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DataSource source;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init() throws ServletException{
		InitialContext c = null;
		try {
			c = new InitialContext();
			source = (DataSource)c.lookup("java:comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			if( c != null) {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		String nextPage = "Error.jsp";
		Product product = null;
		try {
			con = source.getConnection();
			ProductDAO dao = new ProductDAO(con);
			product = dao.findByNo(Integer.parseInt(request.getParameter("no")));
			if(product != null) {
				nextPage = "UpdateProductForm2.jsp";
				request.setAttribute("product", product);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		String nextPage = "Error.jsp";
		int rows = 0;
		Product product = new Product(Integer.parseInt(request.getParameter("no")),
									request.getParameter("name"),
									Integer.parseInt(request.getParameter("price")));
		try {
			con = source.getConnection();
			ProductDAO dao = new ProductDAO(con);
			rows = dao.update(product);
			if(rows == 1) {
				request.setAttribute("product", product);
				nextPage = "UpdateProductOK.jsp";
			}
		} catch (SQLException e) {
			// TODO: handle exception
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

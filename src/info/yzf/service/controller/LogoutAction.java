package info.yzf.service.controller;

import info.yzf.database.SerialDatabase;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutAction
 */
@WebServlet("/LogoutAction")
public class LogoutAction extends HttpServlet {

	private static final long serialVersionUID = 8337499139106090812L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		SerialDatabase.getInstance().save();
		request.getSession().invalidate();
		response.sendRedirect("home.jsp");
	}

}

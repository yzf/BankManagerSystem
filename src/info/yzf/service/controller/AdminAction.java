package info.yzf.service.controller;

import info.yzf.service.manager.EmployeeManager;
import info.yzf.util.Message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminAction
 */
@WebServlet("/AdminAction")
public class AdminAction extends HttpServlet {
       
	private static final long serialVersionUID = 5213535673047227104L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("operation", "admin");
		request.setAttribute("message", Message.Success);
		try {
			String op = request.getParameter("op");
			if ("add".equals(op)) {
				String name = request.getParameter("name");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				int type = Integer.parseInt(request.getParameter("type"));
				int depId = Integer.parseInt(request.getParameter("depId"));
				EmployeeManager.getInstance().add(name, username, password, type, depId);
			}
			else if ("delete".equals(op)){
				String name = request.getParameter("name");
				String username = request.getParameter("username");
				EmployeeManager.getInstance().delete(name, username);
			}
			else if ("update".equals(op)) {
				String name = request.getParameter("name");
				String username = request.getParameter("username");
				int type = Integer.parseInt(request.getParameter("type"));
				int depId = Integer.parseInt(request.getParameter("depId"));
				EmployeeManager.getInstance().updateInfo(name, username, type, depId);
			}
			else if ("password".equals(op)) {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				EmployeeManager.getInstance().updatePassword(username, password);
			}
		} catch(Exception e) {
			request.setAttribute("message", e.getLocalizedMessage());
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}

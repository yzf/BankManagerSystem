package info.yzf.service.controller;

import info.yzf.database.model.Employee;
import info.yzf.service.manager.EmployeeManager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginAction
 */
@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	
	private static final long serialVersionUID = 5524169880908514079L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		Employee employee = EmployeeManager.getInstance().get(username, password);
		if (employee != null) {//登陆成功
			HttpSession session = request.getSession();
			session.setAttribute("employee", employee);
			request.setAttribute("message", "登陆成功");
		}
		else {//登陆失败
			request.setAttribute("message", "账号或密码错误");
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}

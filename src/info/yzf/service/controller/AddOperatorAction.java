package info.yzf.service.controller;

import info.yzf.database.model.Employee;
import info.yzf.database.model.Log;
import info.yzf.service.manager.UserAccountManager;
import info.yzf.util.Message;
import info.yzf.util.Pair;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class createOperatorAction
 */
@WebServlet("/createOperatorAction")
public class AddOperatorAction extends HttpServlet {
       
	private static final long serialVersionUID = -4272064552100511626L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("operation", "changePassword");
		request.setAttribute("message", Message.Success);
		try {
			String identity = request.getParameter("identity").trim();
			String username = request.getParameter("username").trim();
			String password = request.getParameter("password");
			String newIdentity = request.getParameter("newIdentity").trim();
			String newName = request.getParameter("newName").trim();
			String newPassword = request.getParameter("newPassword");
			String newPasswordAgain = request.getParameter("newPasswordAgain");
			int role = Integer.parseInt(request.getParameter("role"));
			//添加操作人
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			Pair pair = UserAccountManager.getInstance().addOperator(employee, identity, username, 
					password, newIdentity, newName, newPassword, newPasswordAgain, role);
			Log log = (Log) pair.getSecond();
			request.setAttribute("log", log);
			request.setAttribute("logTime", log.getFormatTime());
		} catch (Exception e) {
			request.setAttribute("message", e.getLocalizedMessage());
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}

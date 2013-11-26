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
 * Servlet implementation class ChangePasswordAction
 */
@WebServlet("/ChangePasswordAction")
public class ChangePasswordAction extends HttpServlet {

	private static final long serialVersionUID = 5013264444821984298L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("operation", "changePassword");
		request.setAttribute("message", Message.Success);
		try {
			//获取数据
			String identity = request.getParameter("identity").trim();
			String username = request.getParameter("username").trim();
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			//修改密码
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			Pair pair = UserAccountManager.getInstance().changePassword(
								employee, identity, username, oldPassword, newPassword);
			//记录日志
			Log log = (Log) pair.getSecond();
			request.setAttribute("log", log);
			request.setAttribute("logTime", log.getFormatTime());
		} catch (Exception e) {
			request.setAttribute("message", e.getLocalizedMessage());
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}

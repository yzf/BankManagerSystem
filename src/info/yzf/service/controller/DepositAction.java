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
 * Servlet implementation class DepositAction
 */
@WebServlet("/DepositAction")
public class DepositAction extends HttpServlet {

	private static final long serialVersionUID = -2924676645549109282L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("operation", "deposit");
		request.setAttribute("message", Message.Success);
		try {
			//获取数据
			String username = request.getParameter("username").trim();
			String password = request.getParameter("password").trim();
			double money = Double.parseDouble(request.getParameter("money"));
			//存款
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			Pair pair = UserAccountManager.getInstance().deposit(employee, username, password, money);
			//日志
			double balance = (double) pair.getFirst();
			Log log = (Log) pair.getSecond();
			request.setAttribute("detail", "余额为：" + balance);
			request.setAttribute("log", log);
			request.setAttribute("logTime", log.getFormatTime());
		} catch (Exception e) {
			request.setAttribute("message", e.getLocalizedMessage());
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}

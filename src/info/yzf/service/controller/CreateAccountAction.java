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
 * Servlet implementation class CreateAccountAction
 */
@WebServlet("/CreateAccountAction")
public class CreateAccountAction extends HttpServlet {

	private static final long serialVersionUID = 1014231803231070741L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("operation", "createAccount");
		request.setAttribute("message", Message.Success);
		try {
			//获取数据
			String enIdentity = request.getParameter("enIdentity");
			String enName = request.getParameter("enName");
			String identity = request.getParameter("identity");
			String name = request.getParameter("name");
			double balance = Double.parseDouble(request.getParameter("balance"));
			int uType = Integer.parseInt(request.getParameter("uType"));
			int aType = Integer.parseInt(request.getParameter("aType"));
			String password = request.getParameter("password");
			String passwordAgain = request.getParameter("passwordAgain");
			//开户
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			Pair pair = UserAccountManager.getInstance().createAccount(employee, enIdentity, enName, 
								identity, name, uType, aType, balance, password, passwordAgain);
			String username = (String) pair.getFirst();
			Log log = (Log) pair.getSecond();
			//返回数据
			request.setAttribute("detail", "账号为：" + username);
			request.setAttribute("log", log);
			request.setAttribute("logTime", log.getFormatTime());
		} catch (Exception e) {
			request.setAttribute("message", e.getLocalizedMessage());
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}

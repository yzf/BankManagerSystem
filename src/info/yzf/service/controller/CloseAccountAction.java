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
 * Servlet implementation class CloseAccountAction
 */
@WebServlet("/CloseAccountAction")
public class CloseAccountAction extends HttpServlet {

	private static final long serialVersionUID = -4932001298686214124L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("operation", "closeAccount");
		request.setAttribute("message", Message.Success);
		try {
			//获取信息
			String identity = request.getParameter("identity").trim();
			String username = request.getParameter("username").trim();
			String password = request.getParameter("password");
			//销户
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			Pair pair = UserAccountManager.getInstance().closeAccount(employee, identity, username, password);
			Log log = (Log) pair.getSecond();
			request.setAttribute("detail", "取出：" + (double) pair.getFirst() + "元");
			request.setAttribute("log", log);
			request.setAttribute("logTime", log.getFormatTime());
		} catch (Exception e) {
			request.setAttribute("message", e.getLocalizedMessage());
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}

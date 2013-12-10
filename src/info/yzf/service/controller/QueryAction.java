package info.yzf.service.controller;

import info.yzf.database.model.Employee;
import info.yzf.database.model.Log;
import info.yzf.service.manager.LogManager;
import info.yzf.service.manager.UserAccountManager;
import info.yzf.util.Message;
import info.yzf.util.Pair;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class QueryAction
 */
@WebServlet("/QueryAction")
public class QueryAction extends HttpServlet {
       
	private static final long serialVersionUID = 2398503070828877157L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("operation", "query");
		request.setAttribute("message", Message.Success);
		try {
			int op = Integer.parseInt(request.getParameter("op"));
			String identity = request.getParameter("identity").trim();
			String username = request.getParameter("username").trim();
			String password = request.getParameter("password");
			String begin = request.getParameter("begin");
			String end = request.getParameter("end");
			
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			
			if (op == 1) {
				Vector<Log> logs = LogManager.getInstance().query(identity, username, password, begin, end);
				request.setAttribute("logs", logs);
			}
			else {
				Pair pair = UserAccountManager.getInstance().query(employee, identity, username, password);
				Log log = (Log) pair.getSecond();
				request.setAttribute("detail", "余额为：" + (double) pair.getFirst() + "元");
				request.setAttribute("log", log);
				request.setAttribute("logTime", log.getFormatTime());
			}
		} catch (Exception e) {
			request.setAttribute("message", e.getLocalizedMessage());
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}

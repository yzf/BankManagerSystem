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
 * Servlet implementation class TransferAction
 */
@WebServlet("/TransferAction")
public class TransferAction extends HttpServlet {

	private static final long serialVersionUID = -570310956553739931L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("operation", "transfer");
		request.setAttribute("message", Message.Success);
		try {
			//获取数据
			String fromIdentity = request.getParameter("fromIdentity").trim();
			String fromName = request.getParameter("fromName").trim();
			String fromUsername = request.getParameter("fromUsername").trim();
			String fromPassword = request.getParameter("fromPassword");
			double money = Double.parseDouble(request.getParameter("money"));
			String toName = request.getParameter("toName").trim();
			String toUsername = request.getParameter("toUsername").trim();
			//转账
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			Pair pair = UserAccountManager.getInstance().transfer(employee, fromIdentity, fromName, 
										fromUsername, fromPassword, toName, toUsername, money);
			Log log = (Log) pair.getSecond();
			request.setAttribute("detail", "余额为：" + (double) pair.getFirst() + "元");
			request.setAttribute("log", log);
			request.setAttribute("logTime", log.getFormatTime());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getLocalizedMessage());
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}
}

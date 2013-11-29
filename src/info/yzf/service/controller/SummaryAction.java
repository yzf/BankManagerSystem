package info.yzf.service.controller;

import info.yzf.database.model.Employee;
import info.yzf.service.manager.EmployeeManager;
import info.yzf.util.Message;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SummaryAction
 */
@WebServlet("/SummaryAction")
public class SummaryAction extends HttpServlet {
	
	private static final long serialVersionUID = 5655943993532297200L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("operation", "summary");
		request.setAttribute("message", Message.Success);
		try {
			//获取数据
			String content = request.getParameter("content");
			Employee employee = (Employee) request.getSession().getAttribute("employee");
			EmployeeManager.getInstance().submitSummary(employee, content);
		} catch (Exception e) {
			request.setAttribute("message", e.getLocalizedMessage());
		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}

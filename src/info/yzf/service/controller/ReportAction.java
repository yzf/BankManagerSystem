package info.yzf.service.controller;

import info.yzf.database.model.Employee;
import info.yzf.service.manager.LogManager;
import info.yzf.util.Report;
import info.yzf.util.Util;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReportAction
 */
@WebServlet("/ReportAction")
public class ReportAction extends HttpServlet {

	private static final long serialVersionUID = 6651831563286448953L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("operation", "report");
		String resultPage = "report/";
		try {
			//获取数据
			Timestamp begin = Util.getToday(request.getParameter("time"));
			int type = Integer.parseInt(request.getParameter("type"));
			int cnt = 0;
			if (request.getParameter("quarter") != null) {
				cnt = Integer.parseInt(request.getParameter("quarter"));
				begin = Util.getEnd(request.getParameter("time"), type, cnt - 1);
			}
			Timestamp end = Util.getEnd(request.getParameter("time"), type, cnt);
			int op = Integer.parseInt(request.getParameter("op"));
			Employee e = (Employee) request.getSession().getAttribute("employee");
			if (op == 0) {
				resultPage += "emp.jsp";
				Report report = LogManager.getInstance().getPersonalReport(e.getUsername(), begin, end);
				request.setAttribute("report", report);
				request.setAttribute("begin", Util.formatDay(begin));
				request.setAttribute("end", Util.formatEndTime(end));
			} 
			else if (op == 1) {
				String username = request.getParameter("username");
				resultPage += "emp.jsp";
				Report report = LogManager.getInstance().getPersonalReport(username, begin, end);
				request.setAttribute("report", report);
				request.setAttribute("begin", Util.formatDay(begin));
				request.setAttribute("end", Util.formatEndTime(end));
			}
			else if (op == 2) {
				resultPage += "dep.jsp";
				int depId = Integer.parseInt(request.getParameter("depId"));
				System.out.println(e);
				System.out.println(depId);
				System.out.println(begin);
				System.out.println(end);
				Report report = LogManager.getInstance().getDepReport(e, depId, begin, end);
				request.setAttribute("report", report);
				request.setAttribute("begin", Util.formatDay(begin));
				request.setAttribute("end", Util.formatEndTime(end));
			}
			else if (op == 3) {
				resultPage += "bank.jsp";
				Report report = LogManager.getInstance().getBankReport(e, begin, end);
				request.setAttribute("report", report);
				request.setAttribute("begin", Util.formatDay(begin));
				request.setAttribute("end", Util.formatEndTime(end));
			}
			request.setAttribute("type", type == 0 ? "日报" : (type == 1 ? "月报" : (type == 2 ? "季度报" : "年报")));
		} catch (Exception e) {
			request.setAttribute("message", e.getLocalizedMessage());
		}
		request.getRequestDispatcher(resultPage).forward(request, response);
	}
}

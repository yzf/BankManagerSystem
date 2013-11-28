package info.yzf.service.controller;

import info.yzf.util.Message;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogAction
 */
@WebServlet("/LogAction")
public class LogAction extends HttpServlet {

	private static final long serialVersionUID = 6651831563286448953L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("operation", "changePassword");
		request.setAttribute("message", Message.Success);
		Enumeration<String> enu = request.getParameterNames();
		while (enu.hasMoreElements()) {
			String key = enu.nextElement();
			System.out.println(key + ": " + request.getParameter(key));
		}
 //		try {
//			int level = Integer.parseInt(request.getParameter("level"));
//			int depId = Integer.parseInt(request.getParameter("depId"));
//			int type = Integer.parseInt(request.getParameter("type"));
//			String time = request.getParameter("time");
//			Employee employee = (Employee) request.getSession().getAttribute("employee");
//			Pair pair = LogManager.getInstance().getJournal(employee, level, depId, type, time);
//			@SuppressWarnings("unchecked")
//			Vector<Log> logs = (Vector<Log>) pair.getSecond();
//			request.setAttribute("logs", logs);
//		} catch (Exception e) {
//			String message = e.getLocalizedMessage();
//			if (message == "") {
//				message = "日期格式错误";
//			}
//			request.setAttribute("message", message);
//		}
		request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}

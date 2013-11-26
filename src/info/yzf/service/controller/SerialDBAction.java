package info.yzf.service.controller;

import info.yzf.database.SerialDatabase;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveDBAction
 */
@WebServlet("/SaveDBAction")
public class SerialDBAction extends HttpServlet {

	private static final long serialVersionUID = 3814966534782696455L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public SerialDBAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		PrintWriter pw = response.getWriter();
		String op = request.getParameter("op");
		if (op.equals("save")) {
			pw.write("save");
			SerialDatabase.getInstance().save();
		}
		else if (op.equals("dump")) {
			pw.write("dump");
			SerialDatabase.getInstance().dump();
		}
		else if (op.equals("reset")) {
			pw.write("reset");
			SerialDatabase.getInstance().reset();
			SerialDatabase.getInstance().save();
		}
	}

}

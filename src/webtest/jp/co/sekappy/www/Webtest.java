package webtest.jp.co.sekappy.www;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helper.DBhelper;

/**
 * Servlet implementation class Webtest
 */
@WebServlet("/Webtest")
public class Webtest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Webtest() {
        super();

    	DBhelper helper = new DBhelper();
    	helper.AuthDB("google@gmail.com" ,"1234567890");


    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DBhelper helper = new DBhelper();
//		helper.openDB();
    	helper.AuthDB("google@gmail.com" ,"1234567890");
//    	helper.closeDB();


		String s = "Served at: ";
		response.getWriter().append(s).append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

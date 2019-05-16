package webtest.jp.co.sekappy.www;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helper.DBhelper;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
    }

    private static String path = "";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doInsert(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doInsert(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String mail = "";
		String password = "";

		mail = request.getParameter("mail");
		password = request.getParameter("password");

		session.setAttribute("mail", mail);
		session.setAttribute("password", password);

		DBhelper helper = new DBhelper();
		helper.insertDB(mail, password);




		//新規登録に成功した場合はマイページへ遷移
		//それ以外は新規登録ページへ再遷移
		//できれば再遷移したことがわかるフラグとエラーメッセージを実装したい。
		int insResultFrag = 0;
		if(insResultFrag == 1) {
			path = "/WEB-INF/BattleArena/Mypage.jsp";
		}else {
			path = "/WEB-INF/BattleArena/Login.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request,response);

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}

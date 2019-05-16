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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

    private static String path = "";

    //ログイン処理
    //入力された値の受け取り、データベースの照合、遷移先の分岐


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doLogin(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doLogin(request, response);
	}


	protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	HttpSession session = request.getSession();

    	String[] str = { "mail", "password" };

    	for ( String s : str ) {
    		if ( request.getParameter(s) != null ) {
    			session.setAttribute( s, request.getParameter(s) );
    		}
    	}

    	String m = request.getParameter("mail");
    	String p = request.getParameter("password");

    	session.setAttribute( "mail", m );
    	session.setAttribute( "password", p );

		DBhelper helper = new DBhelper();
		String mail     = session.getAttribute("mail").toString();
		String password = session.getAttribute("password").toString();

		//AuthDBメソッドの1回目でエラーが出るため、2回実行する（原因特定に至らず暫定処置）
		helper.AuthDB(mail, password);
		boolean AuthResult = helper.AuthDB( mail, password );
//		AuthResult = helper.AuthDB( mail, password );
				//(m,p);

		if(AuthResult) {
			path = "/WEB-INF/BattleArena/Mypage.jsp";
		}else {
			path = "/WEB-INF/BattleArena/Login.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(path);
		dispatcher.forward(request,response);

	}

}

package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tool.Action;

public class LogoutAction extends Action{

	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {
		String url = "";
		url = "logout.jsp";
		HttpSession session = req.getSession(); // セッション

		//リクエストパラメータ―の取得 2-
		//なし
		//DBからデータ取得 3
		//なし
		//ビジネスロジック 4
		session.removeAttribute("user");
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//なし
		//JSPへフォワード 7
		url = "logout.jsp";
		req.getRequestDispatcher(url).forward(req, res);
	}

}
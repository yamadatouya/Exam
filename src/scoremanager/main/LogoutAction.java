package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction {

	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {
		HttpSession session = req.getSession(); // セッション

		//リクエストパラメータ―の取得 2-
		//なし
		//DBからデータ取得 3
		//なし
		//ビジネスロジック 4

		if(session.getAttribute("user")!=null){
			session.invalidate();
		}
		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		//なし
		//JSPへフォワード 7
		req.getRequestDispatcher("logout.jsp").forward(req, res);
	}

}
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception {

		HttpSession session = req.getSession();//セッション

		Teacher teacher = (Teacher)session.getAttribute("user");

		String cd = req.getParameter("cd");

		SubjectDao p=new SubjectDao();

		Subject a = p.get(cd, teacher.getSchool());

		req.setAttribute("subject", a);


	//JSPへフォワード 7
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}

}
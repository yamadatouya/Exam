package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

	@Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		SubjectDao subjectDao = new SubjectDao();
        Subject subject = new Subject();

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");


        subject.setCd(cd);
        subject.setName(name);

        //保存
        subjectDao.save(subject);



        //変更に成功した場合、成功ページにフォワード
        req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
        //保存に失敗した場合、学生リストページにフォワード
        req.getRequestDispatcher("SubjectList.action").forward(req, res);
	}

}

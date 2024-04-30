package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //変数宣言
        HttpSession session = req.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = new Subject();

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");


        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);



        boolean saved = subjectDao.save(subject);

        if (saved) {
            //変更に成功した場合、成功ページにフォワード
            req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
        } else {
            //保存に失敗した場合、学生リストページにフォワード
            req.getRequestDispatcher("StudentList.action").forward(req, res);
        }
    }
}
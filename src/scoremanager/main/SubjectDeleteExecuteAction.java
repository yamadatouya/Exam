package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = new Subject();

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        subject.setCd(cd);
        subject.setName(name);

        subjectDao.delete(subject);




    }
}

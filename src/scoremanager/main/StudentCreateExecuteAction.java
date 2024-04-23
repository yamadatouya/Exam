package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //変数宣言
        HttpSession session = req.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();
        StudentDao studentDao = new StudentDao();
        Student student = new Student();
        int ent_year = Integer.parseInt(req.getParameter("f1"));
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String class_num = req.getParameter("f2");

        student.setNo(no);
        student.setName(name);
        student.setEntYear(ent_year);
        student.setClassNum(class_num);
        student.setAttend(true);
        student.setSchool(school);

        boolean saved = studentDao.save(student);

        if (saved) {
            //変更に成功した場合、成功ページにフォワード
            req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
        } else {
            //保存に失敗した場合、学生リストページにフォワード
            req.getRequestDispatcher("StudentList.action").forward(req, res);
        }
    }
}

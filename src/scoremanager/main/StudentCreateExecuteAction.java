package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

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
        Map<String, String> errors = new HashMap<>();

        student.setNo(no);
        student.setName(name);
        student.setEntYear(ent_year);
        student.setClassNum(class_num);
        student.setAttend(true);
        student.setSchool(school);

        // 入学年度が未選択の場合のエラーチェック
        if (ent_year == 0) {
            errors.put("ent_year", "入学年度を入力してください");
            req.setAttribute("errors", errors);
            req.setAttribute("ent_year", ent_year);
            req.setAttribute("class_num", class_num);
            req.setAttribute("name", name);
            req.setAttribute("no", no);
            req.getRequestDispatcher("StudentCreate.action").forward(req, res);
            return;
        } else if (studentDao.get(no) != null) {
        	errors.put("no", "学籍番号が重複しています");
        	req.setAttribute("errors", errors);
            req.setAttribute("ent_year", ent_year);
            req.setAttribute("class_num", class_num);
            req.setAttribute("name", name);
            req.setAttribute("no", no);
            req.getRequestDispatcher("StudentCreate.action").forward(req, res);
            return;
        }

        boolean saved = studentDao.save(student);

        if (saved) {
            // 変更に成功した場合、成功ページにフォワード
            req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
        } else {
            // 保存に失敗した場合、学生リストページにフォワード
            req.getRequestDispatcher("StudentList.action").forward(req, res);
        }
    }
}

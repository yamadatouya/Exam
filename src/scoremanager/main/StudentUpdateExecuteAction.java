package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	HttpSession session = req.getSession(); // ユーザーデータ取得
		Teacher teacher = (Teacher)session.getAttribute("user");
		// ユーザーが所属している学校を取得
        School school = teacher.getSchool();
        // 生徒データを取得するためのDAOを作成
        StudentDao studentDao = new StudentDao();
        Student student = new Student();

        int ent_year = Integer.parseInt(req.getParameter("ent_year"));
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String class_num = req.getParameter("class_num");
        String isAttend = req.getParameter("isAttend");



        student.setNo(no);
        student.setName(name);
        student.setEntYear(ent_year);
        student.setClassNum(class_num);
        student.setAttend(true);
        student.setSchool(school);




        // 取得した生徒一覧をリクエスト属性にセット
        req.setAttribute("ent_year",ent_year);
        req.setAttribute("no",no);
    	req.setAttribute("students", student);
    	req.setAttribute("class_num",class_num);
    	req.setAttribute("nem", name);
    	//req.setAttribute("isAttend",isAttend);
    	List<Student> students = studentDao.filter(teacher.getSchool(), isAttend);




        //変更に成功した場合、成功ページにフォワード
        req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
        //保存に失敗した場合、学生リストページにフォワード
        req.getRequestDispatcher("StudentList.action").forward(req, res);
    }
}

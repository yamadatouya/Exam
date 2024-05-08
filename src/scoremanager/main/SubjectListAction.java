package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    HttpSession session = req.getSession();
	    Teacher teacher = (Teacher)session.getAttribute("user");

	    //変数宣言タイム
	    String entYearStr="";
	    String classNum="";
	    String isAttendStr="";
	    int entYear = 0;
	    boolean isAttend = false;
	    List<Student> students = null;
	    LocalDate todaysDate = LocalDate.now();
	    int year = todaysDate.getYear();
	    StudentDao sDao = new StudentDao();
	    SubjectDao subjectDao = new SubjectDao();
	    Map<String, String> errors = new HashMap<>();

	  //ログインユーザーの学校コードをもとにクラス番号一覧の取得
	    List<Subject> list = subjectDao.filter(teacher.getSchool());


	    req.setAttribute("subject_set", list);



	    req.getRequestDispatcher("subject_list.jsp").forward(req,  res);
	}
}

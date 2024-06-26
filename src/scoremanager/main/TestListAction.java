package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	String action = req.getParameter("f");
    	HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

		LocalDate todaysDate = LocalDate.now(); //LocalDateインスタンス取得
		int year =todaysDate.getYear(); // 現在の年を取得

		List<Integer> numSet= new ArrayList<>();

		for (int i = 1; i < 101; i++){
			numSet.add(i);
		}

		// リストを初期化
		List<Integer> entYearSet=new ArrayList<>();

		// 10年前から1年後までの年をリストに追加
		for (int i = year-10;i<year+1;i++){
			entYearSet.add(i);
		}

		SubjectDao subjectDao = new SubjectDao(); // 科目Dao
		List<Subject> subjectList=subjectDao.filter(teacher.getSchool());

		// データをリクエストにセット
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("num_set", numSet);
		req.setAttribute("subject_name", subjectList);

        if (action != null) {
            switch (action) {
                case "sj":
                	TestListSubject(req, res);
                    break;
                case "st":
                	TestListStudent(req, res);
                    break;
            }
        } else {
        	TestListStudent(req, res);
        }
    }

    private void TestListSubject (HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

		String subjectName ="";
		Student students = null;
		List<Test> test = null;
		Subject subject = new Subject();
        SubjectDao subjectDao = new SubjectDao(); // 科目Dao
		List<Subject> subjectList=subjectDao.filter(teacher.getSchool());

		subjectName=req.getParameter("sj");

		students = ((Test) test).getStudent();
		subject=subjectDao.get(subjectName, teacher.getSchool());

		req.setAttribute("subject_name", subjectList);
    }

	private void TestListStudent(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Dao

		// クラスデータ取得
        List<String> classNumList=cNumDao.filter(teacher.getSchool());

	    req.setAttribute("class_num_set", classNumList);

	    // test_list.jspにフォワード
	    req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}
}
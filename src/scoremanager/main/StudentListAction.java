package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {

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
	    ClassNumDao cNumDao = new ClassNumDao();
	    Map<String, String> errors = new HashMap<>();


	    entYearStr = req.getParameter("f1");
	    classNum = req.getParameter("f2");
	    isAttendStr = req.getParameter("f3");

        //ログインユーザーの学校コードをもとにクラス番号一覧の取得
	    List<String> list = cNumDao.filter(teacher.getSchool());

	    if (entYear != 0 && !classNum.equals("0")) {
	    	//入学年度とクラスを指定
	    	students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
	    } else if (entYear != 0 && classNum.equals("0")) {
	    	//入学年度のみ指定
	    	students = sDao.filter(teacher.getSchool(), entYear, isAttend);
	    } else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
	    	//指定無し、全学年情報を取得
	        students = sDao.filter(teacher.getSchool(), isAttend);
	    } else {
	    	errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
	    	req.setAttribute("errors", errors);
	    	//全学年情報を取得
	    	students = sDao.filter(teacher.getSchool(), isAttend);
	    }

	    if (entYearStr != null) {
	    	//数値に変換
	    	entYear = Integer.parseInt(entYearStr);
	    }
	    //リストを初期化
	    List<Integer> entYearSet = new ArrayList<>();
	    //10年前から１年後まで年をリストに追加
	    for (int i = year - 10; i< year + 1; i++) {
	    	entYearSet.add(i);
	    }



	    req.setAttribute("f1",entYear);
	    req.setAttribute("f2",classNum);
	    if (isAttendStr != null) {
	    	isAttend = true;
	    	req.setAttribute("f3",isAttendStr);
	    }
	    req.setAttribute("students", students);
	    req.setAttribute("class_num_set", list);
	    req.setAttribute("ent_year_set", entYearSet);

	    //JSPへフォワード
	    req.getRequestDispatcher("student_list.jsp").forward(req, res);

	}

}

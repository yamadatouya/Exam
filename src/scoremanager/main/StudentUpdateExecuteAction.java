package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {


        StudentDao studentDao = new StudentDao();
        Student student = new Student();
        boolean isAttend = false;

        int ent_year = Integer.parseInt(req.getParameter("ent_year"));
        String no = req.getParameter("no");
        String name = req.getParameter("name");
        String class_num = req.getParameter("class_num");
        String isAttendStr = req.getParameter("is_Attend");


        if (isAttendStr != null) {
			isAttend = true;
		}



        student.setNo(no);
        student.setName(name);
        student.setEntYear(ent_year);
        student.setClassNum(class_num);
        student.setAttend(isAttend);
        //保存
        studentDao.save(student);




        //変更に成功した場合、成功ページにフォワード
        req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
        //保存に失敗した場合、学生リストページにフォワード
        req.getRequestDispatcher("StudentList.action").forward(req, res);
    }
}

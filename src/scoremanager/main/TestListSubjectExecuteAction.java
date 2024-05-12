package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.Test;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        //変数宣言
        HttpSession session = req.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();
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

        // 入力内容のチェック
        if (ent_year == 0 || class_num == null || no == null || name == null || class_num.isEmpty() || no.isEmpty() || name.isEmpty()) {
            // 入力が不足している場合はエラーメッセージを設定
            req.setAttribute("message", "入学年度、クラス、科目を全て選択してください。");
            // フォームの入力値を保持してtest_list_student.jspにフォワード
            req.setAttribute("ent_year", ent_year);
            req.setAttribute("class_num", class_num);
            req.setAttribute("no", no);
            req.setAttribute("name", name);
            req.getRequestDispatcher("/test_list_student.jsp").forward(req, res);
            return;
        }

        // 入学年度、クラス、科目から成績データを取得する処理を追加
        List<Test> testList = getTestScores(String.valueOf(ent_year), class_num, req.getParameter("f3"));

        // 成績データをリクエスト属性にセット
        req.setAttribute("testList", testList);
        // メッセージを設定
        req.setAttribute("message", "成績データが取得されました。");

        // test_list_student.jspにフォワード
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }

    // 成績データを取得するロジック
    private List<Test> getTestScores(String f1, String f2, String f3) {
        // ここで入学年度、クラス、科目から成績データを取得する処理を実装する
        // 例えば、データベースから成績データを取得するなど
        // ダミーデータを返す場合は適宜修正すること
        return someDatabaseOperationToGetTestScores(f1, f2, f3);
    }


    private List<Test> someDatabaseOperationToGetTestScores(String f1, String f2, String f3) {
        // データベースから成績データを取得するロジックを実装する
        return null;
    }
}

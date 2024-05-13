package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.corba.se.spi.presentation.rmi.PresentationManager.ClassData;

import bean.School;
import bean.Subject;
import bean.User;
import tool.Action;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();

        // セッションからユーザーデータを取得
        User user = (User)session.getAttribute("user");

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

		// データをリクエストにセット
		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("num_set", numSet);

        if (user != null) {
            // ユーザーが所属している学校を取得
            School userSchool = user.getSchool();

            // 学校からクラスデータを取得
            List<ClassData> classes = getClassesForSchool(userSchool);

            // 学校から科目データを取得
            List<Subject> subjects = getSubjectsForSchool(userSchool);

            // 取得したクラスデータと科目データをリクエスト属性にセット
            req.setAttribute("classes", classes);
            req.setAttribute("subjects", subjects);
        }

        // test_list.jspにフォワード
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }

    // 学校からクラスデータを取得するロジック
    private List<ClassData> getClassesForSchool(School school) {
        // ここで学校からクラスデータを取得するロジックを実装する
        return someDatabaseOperationToGetClasses(school);
    }

    // 学校から科目データを取得するロジック
    private List<Subject> getSubjectsForSchool(School school) {
        // ここで学校から科目データを取得するロジックを実装する
        return someDatabaseOperationToGetSubjects(school);
    }

    // このメソッドは実装する必要があります
    private List<ClassData> someDatabaseOperationToGetClasses(School school) {
        // データベースからクラスデータを取得するロジックを実装する
        return null;
    }

    // このメソッドは実装する必要があります
    private List<Subject> someDatabaseOperationToGetSubjects(School school) {
        // データベースから科目データを取得するロジックを実装する
        return null;
    }
}

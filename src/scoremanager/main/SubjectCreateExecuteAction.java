
package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user");


        String subjectName = ""; // 入力された科目名
        String subjectCd = ""; // 入力された科目コード
        Subject makeSubject = new Subject(); // 送信用科目情報
        SubjectDao sDao = new SubjectDao(); // 科目Dao
        Map<String, String> errors = new HashMap<>(); // エラーメッセージ

        // リクエストパラメータの取得
        subjectName = req.getParameter("subject_name");
        subjectCd = req.getParameter("no");

        // 科目コード重複確認
        Subject isSubject = sDao.get(subjectCd, teacher.getSchool());

        // エラーチェック
        if (isSubject != null && subjectName.equals("0")) {
            System.out.println("★リスト未選択かつ科目コード重複");
            errors.put("f1", "科目名を入力してください");
            errors.put("f2", "科目コードが重複しています");
            req.setAttribute("errors", errors);
            req.setAttribute("no", subjectCd);
            req.setAttribute("subject_name", subjectName);
            req.getRequestDispatcher("SubjectCreate.action").forward(req, res);
        } else if (subjectName.equals("0")) {
            System.out.println("★リスト未選択");
            errors.put("f1", "科目名を入力してください");
            req.setAttribute("errors", errors);
            req.setAttribute("no", subjectCd);
            req.setAttribute("subject_name", subjectName);
            req.getRequestDispatcher("SubjectCreate.action").forward(req, res);
        } else if (isSubject != null) {
            System.out.println("★科目コード重複");
            errors.put("f2", "科目コードが重複しています");
            req.setAttribute("errors", errors);
            req.setAttribute("no", subjectCd);
            req.setAttribute("subject_name", subjectName);
            req.getRequestDispatcher("SubjectCreate.action").forward(req, res);
        } else if (isSubject == null) {
            System.out.println("★リスト選択OK、科目コード重複無し");
            // 送信用の科目情報の作成
            makeSubject.setCd(subjectCd);
            makeSubject.setName(subjectName);
            makeSubject.setSchool(teacher.getSchool());
            // 送信
            boolean end = sDao.save(makeSubject);

            if (end) {
                req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
            } else {
                System.out.println("★登録に失敗しました");
                req.setAttribute("no", subjectCd);
                req.setAttribute("subject_name", subjectName);
                req.getRequestDispatcher("SubjectCreate.action").forward(req, res);
            }
        }
    }
}

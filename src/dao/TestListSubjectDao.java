	package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

	private String baseSql = "select * from test join subject on test.school_cd = subject.school_cd where ent_year=?, student_no=?,student_name=?,class_num=?";


	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {

		//リストの初期化
		List<TestListSubject> list = new ArrayList<>();
		//リザルトセットを全権走査
		try {
			while (rSet.next()) {
				//学生インスタンスを初期化
				TestListSubject testlistsubject = new TestListSubject();
				//学生インスタンスに検索結果をセット
				testlistsubject.setEntYear(rSet.getInt("ent_year"));
				testlistsubject.setStudentNo(rSet.getString("student_no"));
				testlistsubject.setStudentName(rSet.getString("student_name"));
				testlistsubject.setDassNum(rSet.getString("class_num"));
				testlistsubject.setPoints(null);
				//リストに追加
				list.add(testlistsubject);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<TestListSubject> filter(int entYear,String classNum,Subject subject,School school) throws Exception {
		//リストを初期化
		List<TestListSubject> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		String condition = "";
		//SQL文のソート
		String order = "order by no asc";
		//SQL文の条件
		String conditionIsAttend = "";


		try {
			//プリペアーステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			statement.setString(3, subject.getCd());
			statement.setString(4, school.getCd());
			//プライベートステートメントを実行
			rSet = statement.executeQuery();
			//リストへの格納処理を実行
			list = postFilter(rSet);
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}
}

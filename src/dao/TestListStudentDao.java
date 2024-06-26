package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao {

	private String baseSql = "select * from test join student on test.student_no = student.no where student_no=?";


	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {

		//リストの初期化
		List<TestListStudent> list = new ArrayList<>();
		//リザルトセットを全権走査
		try {
			while (rSet.next()) {
				//学生インスタンスを初期化
				TestListStudent testliststudent = new TestListStudent();
				//学生インスタンスに検索結果をセット
				testliststudent.setSubjectName(rSet.getString("subjectName"));
				testliststudent.setSubjectCd(rSet.getString("suubjectCd"));
				testliststudent.setNum(rSet.getInt("num"));
				testliststudent.setPoint(rSet.getInt("point"));
				//リストに追加
				list.add(testliststudent);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<TestListStudent> filter(Student student) throws Exception {
		//リストを初期化
		List<TestListStudent> list = new ArrayList<>();
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
			statement.setString(1, student.getNo());
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

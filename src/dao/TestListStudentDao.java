package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class TestListStudentDao extends Dao {

	private String baseSql = "select * from student where school_cd=?";


	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {

		//リストの初期化
		List<TestListStudent> list = new ArrayList<>();
		//リザルトセットを全権走査
		try {
			while (rSet.next()) {
				//学生インスタンスを初期化
				TestListStudent testliststudent = new TestListStudent();
				//学生インスタンスに検索結果をセット
				TestListSubject.setSubjectName(rSet.getStriing("subject_name"));
				TestListSubject.setSubjectcd(rSet.getString("suubject_cd"));
				TestListSubject.setNum(rSet.getInt("num"));
				TestListSubject.setPoint(rSet.getInt("point"));
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
		List<TestListStudentDao> list = new ArrayList<>();
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
			statement.setString(1, student);
			//プライベートステートメントを実行
			rSet = statement.executeQuery();
			//リストへの格納処理を実行
			list = postFilter(rSet, testliststudent);
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

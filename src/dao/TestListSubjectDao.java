package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;

public class TestListSubjectDao extends Dao {

	private String baseSql = "select * from student where school_cd=?";


	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {

		//リストの初期化
		List<Student> list = new ArrayList<>();
		//リザルトセットを全権走査
		try {
			while (rSet.next()) {
				//学生インスタンスを初期化
				TestListSubject testlistsubject = new TestListSubject();
				//学生インスタンスに検索結果をセット
				TestListSubject.setEntYear(rSet.getInt("ent_year"));
				TestListSubject.setStudentNo(rSet.getString("student_no"));
				TestListSubject.setStudentName(rSet.getString("student_name"));
				TestListSubject.setClassNum(rSet.getString("class_num"));
				TestListSubject.setPoints(rSet.get)
				//リストに追加
				list.add(testlistsubject);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<TestListSubjectDao> filter(int entYear,String classNum,Subject subject,School school) throws Exception {
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
			statement.setString(1, entYear);
			statement.setInt(2, classNum);
			statement.setString(3, subject);
			statement.setString(4, school);
			//プライベートステートメントを実行
			rSet = statement.executeQuery();
			//リストへの格納処理を実行
			list = postFilter(rSet, restlistsubject);
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

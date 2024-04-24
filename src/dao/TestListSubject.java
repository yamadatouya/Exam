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

public class TestListSubject extends Dao {

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
				TestListSubject.setNo(rSet.getString("no"));
				TestListSubject.setName(rSet.getString("name"));
				TestListSubject.setNum(rSet.getString("num"));
				TestListSubject.setPoint(rSet.<integer,integer>)
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
		List<Student> list = new ArrayList<>();
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
			//プリペアーステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			//プリペアーステートメントに入学年度をバインド
			statement.setInt(2, entYear);
			//プリペアーステートメントにクラス番号をバインド
			statement.setString(3, classNum);
			//プライベートステートメントを実行
			rSet = statement.executeQuery();
			//リストへの格納処理を実行
			list = postFilter(rSet, school);
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

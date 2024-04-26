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

public class TestDao extends Dao {

	//STUDENT_NO  	SUBJECT_CD  	SCHOOL_CD  	NO  	POINT  	CLASS_NUM
	private String baseSql = "select * from test where school_cd=?";

	public Test get(Student student,Subject subject,School school,int no) throws Exception {
		Student student = new Student();
		Test test = new Test();
		School school = new School();
		Subject subject = new Subject();


		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			//プリペアーステートメントにSQL文をセット STUDENT_NO  	SUBJECT_CD  	SCHOOL_CD  	NO  	POINT  	CLASS_NUM

			statement = connection.prepareStatement("select * from test where student_no=?,subject_cd=?,school_cd=?");
			//プリペアーステートメントにnannkaをバインド
			statement.setString(1, student_no);
			statement.setString(2, subject_cd);
			statement.setString(3, school_cd);
			//プライベートステートメントを実行
			ResultSet rSet = statement.executeQuery();

			//学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

			if(rSet.next()) {
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット STUDENT_NO  	SUBJECT_CD  	SCHOOL_CD  	NO  	POINT  	CLASS_NUM

				test.getStudent_no("student_no");
				test.getSubject_cd("subject_cd");
				test.getSchool_cd("school_cd");
				test.getNo("no");
				test.getPoint("point");
				test.getClass_num("class_num");

			} else {
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				test = null;
			}
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
			//コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return test;
	}

	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {

		//リストの初期化
		List<Student> list = new ArrayList<>();
		//リザルトセットを全権走査
		try {
			while (rSet.next()) {
				//学生インスタンスを初期化
				Test test = new Test();
				//学生インスタンスに検索結果をセット
				test.setStudent_no(rSet.getString("student_no"));
				test.setSubject_cd(rSet.getString("subject_cd"));
				test.setSchool_cd(rSet.getString("school_cd"));
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				test.setClass_num(rSet.getString("class_num"));

				//リストに追加
				list.add(test);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Test> filter(int entYear,String classNum,Subject subject,int num,School school) throws Exception {
		//リストを初期化
		List<Test> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		String condition = "and ent_year=? and class_num=?";
		//SQL文のソート
		String order = "order by no asc";
		//SQL文の条件
		String conditionIsAttend = "";
		//在学フラグがtrueも場合
		try {
			//プリペアーステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
			//バインド
			statement.setString(1, school.getCd());
			statement.setString(2, classNum);
			statement.setSubject(3, subject);
			statement.setString(4, classNum);
			statement.setString(5, classNum);
			//プライベートステートメントを実行
			rSet = statement.executeQuery();
			//リストへの格納処理を実行
			list = postFilter(rSet, test);
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

	public boolean save(Test test) throws Exception {
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアーステートメント
	    PreparedStatement statement = null;
	    // 実行件数
	    int count = 0;

	    try {
	        // DBから学生を取得
	        Subject old = get(subject.getCd());
	        if (old == null) {
	            // 学生が存在しなかった場合
	            // プリペアードステートメントにINSERT文をセット
	            statement = connection.prepareStatement(
	                    "insert into student(school_cd,cd,name) values(?, ?, ?)");
	            // プリペアーどステートメントに値をバインド
	            statement.setString(1, subject.getSchool());
	            statement.setString(2, subject.getCd());
	            statement.setString(3,  subject.getName());
	        } else {
	            // 学生が存在する場合
	            // プリペアードステートメントにUPDATE文をセット
	            statement = connection.prepareStatement(
	                    "update subject set school_cd=?,cd=?,name=?");
	            // プリペアードステートメントに値をバインド
	            statement.setString(1, subject.getSchool());
	            statement.setString(2, subject.getCd());
	            statement.setString(3,  subject.getName());
	        }

	        // プリペアードステートメントを実行
	        count = statement.executeUpdate();

	    } catch (Exception e) {
	        throw e;
	    } finally {
	        // 閉じる
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        // コネクションを閉じる
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return count > 0;
	}

	public boolean save(Test test,Connection connection) throws Exception {

	}

	public boolean delete(List<Test> list) throws Exception {
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアーステートメント
	    PreparedStatement statement = null;

        statement = connection.prepareStatement(
                "delete student(school_cd,cd) values(?, ?,)");
        // プリペアーどステートメントに値をバインド
        statement.setString(1, subject.getSchool_cd());
        statement.setString(2, subject.getCd());
	}

	public boolean delete(Test test,Connection connection) throws Exception {
}

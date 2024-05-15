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
import bean.Test;

public class TestDao extends Dao {

	//STUDENT_NO  	SUBJECT_CD  	SCHOOL_CD  	NO  	POINT  	CLASS_NUM
	private String baseSql = "select * from test where";

	public Test get(Student student,Subject subject,School school,int no) throws Exception {
		//変数宣言タイム
		Test test = new Test();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			//sessionのユーザーデータから、ユーザーが所属している学校の科目一覧データを取得
			statement = connection.prepareStatement("select * from test where student_no=?,subject_cd=?,school_cd=?,no=?");
			statement.setString(1, student.getNo());
			statement.setString(2, subject.getCd());
			statement.setString(3, school.getCd());
			statement.setInt(4, no);
			//これで実行されてrSetに入る
			ResultSet rSet = statement.executeQuery();
			if(rSet.next()) {
				test.setStudent(student);
				test.setSubject(subject);
				test.setSchool(school);
				test.setNo(no);
				test.setPoint(rSet.getInt("point"));
				test.setClassNum(rSet.getString("class_num"));
			} else {
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				test = null;
			}
		//エラー処理
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
		List<Test> list = new ArrayList<>();
		try {
			while (rSet.next()) {
				Test test = new Test();
				StudentDao studentdao = new StudentDao();
				SubjectDao subjectdao = new SubjectDao();
				//テーブル列名 STUDENT_NO  	SUBJECT_CD  	SCHOOL_CD  	NO  	POINT  	CLASS_NUM
				test.setStudent(studentdao.get("student_no"));
				test.setSubject(subjectdao.get(rSet.getString("subject_cd"), school));
				test.setSchool(school);
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				test.setClassNum(rSet.getString("class_num"));
				//リストに追加
				list.add(test);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Test> filter(int entYear,String classNum,Subject subject,int num,School school) throws Exception {
		List<Test> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String order = "order by no asc";
		String condition = "ent_year=? and class_num=? and subject_cd=? and no=?";

		try {
			statement = connection.prepareStatement(baseSql + condition + order);
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			statement.setString(3, subject.getCd());
			statement.setInt(4, num);
			statement.setString(5, school.getCd());

			rSet = statement.executeQuery();
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

	public boolean save(List<Test> test) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		//実行件数
		int count = 0;
		try {
//			//DBから科目を取得
//			Test old = get(test.getStudent(),test.getSubject(),test.getSchool(),test.getNo());
//			if (old == null) {
//				//存在しなかった場合
//				//プリペアードステートメントにINSERT文をセット
//				statement = connection.prepareStatement(
//						"insert into test(STUDENT_NO,SUBJECT_CD,SCHOOL_CD,NO,POINT,CLASS_NUM) values(?,?,?,?,?,?)");
//				//プリペアーどステートメントに値をバインド
//				statement.setString(1, test.getStudent().getNo());
//				statement.setString(2, test.getSubject().getCd());
//				statement.setString(3, test.getSchool().getCd());
//				statement.setInt(4, test.getNo());
//				statement.setInt(5, test.getPoint());
//				statement.setString(6, test.getClassNum());
//			} else{
//				//存在した場合
//				//プリペアードステートメントにINSERT文をセット
//				statement = connection.prepareStatement(
//						"update test set point=? where school_cd=?,subject_cd=?,student_no=?,no=?");
//				//プリペアードステートメントに値をバインド
//				statement.setInt(1, test.getPoint());
//				statement.setString(2, test.getSchool().getCd());
//				statement.setString(3, test.getSubject().getCd());
//				statement.setString(4,test.getStudent().getNo());
//				statement.setInt(5, test.getNo());
//			}
//
//			//プリペアードステートメントを実行
//			count = statement.executeUpdate();

			for(Test test1:test){
				save(test1,connection);
			}

		} catch (Exception e) {
			throw e;
	    } finally {
	    	//閉じる
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

		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean save(Test test,Connection connection) throws Exception {
		int count = 0;
		//DBから科目を取得
		Test old = get(test.getStudent(),test.getSubject(),test.getSchool(),test.getNo());
		PreparedStatement statement = null;
		if (old == null) {
			//存在しなかった場合
			//プリペアードステートメントにINSERT文をセット
			statement = connection.prepareStatement(
					"insert into test(STUDENT_NO,SUBJECT_CD,SCHOOL_CD,NO,POINT,CLASS_NUM) values(?,?,?,?,?,?)");
			//プリペアーどステートメントに値をバインド
			statement.setString(1, test.getStudent().getNo());
			statement.setString(2, test.getSubject().getCd());
			statement.setString(3, test.getSchool().getCd());
			statement.setInt(4, test.getNo());
			statement.setInt(5, test.getPoint());
			statement.setString(6, test.getClassNum());
		} else{
			//存在した場合
			//プリペアードステートメントにINSERT文をセット
			statement = connection.prepareStatement(
					"update test set point=? where school_cd=?,subject_cd=?,student_no=?,no=?");
			//プリペアードステートメントに値をバインド
			statement.setInt(1, test.getPoint());
			statement.setString(2, test.getSchool().getCd());
			statement.setString(3, test.getSubject().getCd());
			statement.setString(4,test.getStudent().getNo());
			statement.setInt(5, test.getNo());
		}

		//プリペアードステートメントを実行
		count = statement.executeUpdate();
		return false;
	}

	//public boolean delete(List<Test> list) throws Exception {
	//}

	//public boolean delete(Test test,Connection connection) throws Exception {
	//}
}

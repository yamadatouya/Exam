package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;
import bean.Student;

public class SubjectDao extends Dao {


	public Subject get(String cd, School school) throws Exception {
		//科目インスタンスを初期化
		Subject subject = new Subject();
		//DBへのコネクショを確立
		Connection connection = getConnection();
		//プリペアステートメント
		PreparedStatement statement = null;

		try {
			//プリペアーステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from subject where cd=?");
			//プリペアーステートメントに科目コードをバインド
			statement.setString(1, cd);
			//プライベートステートメントを実行
			ResultSet rSet = statement.executeQuery();

			//学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

			if(rSet.next()) {
				//リザルトセットが存在する場合
				//科目インスタンスに検索結果をセット  SCHOOL_CD  	CD  	NAME
				subject.setSchool(school);
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
			} else {
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				subject = null;
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
		return subject;
	}

	private List<Student> postFilter(ResultSet rSet, School school) throws Exception {

		//リストの初期化
		List<Student> list = new ArrayList<>();
		//リザルトセットを全権走査
		//学校Daoを初期化
		SchoolDao schoolDao = new SchoolDao();
		try {
			while (rSet.next()) {
				//科目インスタンスを初期化
				Subject subject = new Subject();
				//科目インスタンスに検索結果をセット
				subject.setSchool(school);
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				//リストに追加
				list.add(subject);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}


	public List<Student> filter(School school) throws Exception {
		//リストの初期化
		List<Student> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		// プリペアーステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//SQL文のソート
		String order = "order by no asc";
		try {
			//プリペアーステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from subject where cd=?" + order);
			//プリペアーステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
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
			//コネクションを作る
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;
	}









	public boolean save(Subject subject) throws Exception {
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



	public boolean delete(Student student) throws Exception {
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアーステートメント
	    PreparedStatement statement = null;

        statement = connection.prepareStatement(
                "insert into student(school_cd,cd) values(?, ?,)");
        // プリペアーどステートメントに値をバインド
        statement.setString(1, subject.getSchool_cd());
        statement.setString(2, subject.getCd());
	}

}

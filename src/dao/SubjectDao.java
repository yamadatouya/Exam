package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {
	//SQL      SCHOOL_CD CD NAME
	public Subject get(String cd, School school) throws Exception {
		//変数宣言タイム
		Subject subject = new Subject();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			//sessionのユーザーデータから、ユーザーが所属している学校の科目一覧データを取得
			statement = connection.prepareStatement("select * from subject where school_cd=? and cd=?");
			statement.setString(1, school.getCd());
			statement.setString(2, cd);
			//これで実行されてrSetに入る
			ResultSet rSet = statement.executeQuery();
			if(rSet.next()) {
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
			} else {
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				subject = null;
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
		return subject;
	}


	public List<Subject> filter(School school) throws Exception {

		List<Subject> list = new ArrayList<>();
		Subject subject = new Subject();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			//sessionのユーザーデータから、ユーザーが所属している学校の科目一覧データを取得
			statement = connection.prepareStatement("select * from subject where school_cd=?");
			statement.setString(1, school.getCd());
			//これで実行されてrSetに入る
			ResultSet rSet = statement.executeQuery();
			while (rSet.next()) {
				subject.setSchool(school);
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
				list.add(subject);
			}
		//エラー処理
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
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
		return list;
	}

	public boolean save(Subject subject) throws Exception {

		Connection connection = getConnection();
		PreparedStatement statement = null;
		//実行件数
		int count = 0;

		try {
			//DBから科目を取得
			Subject old = get(subject.getCd(),subject.getSchool());
			if (old == null) {
				//科目が存在しなかった場合
				//プリペアードステートメントにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into subject values(?, ?, ?)");
				//プリペアーどステートメントに値をバインド
				statement.setString(1, subject.getSchool().getCd());
				statement.setString(2, subject.getCd());
				statement.setString(3, subject.getName());
			} else{
				//科目が存在した場合
				//プリペアードステートメントにINSERT文をセット
				statement = connection.prepareStatement(
						"update subject set name=? where cd=?");
				//プリペアードステートメントに値をバインド
				statement.setString(1, subject.getName());
				statement.setString(2, subject.getCd());
			}

			//プリペアードステートメントを実行
			count = statement.executeUpdate();

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

	public boolean delete(Subject subject) throws Exception {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    int count = 0;

	    try {
	        // DB接続取得
	        connection = getConnection();

	        // プリペアードステートメントにDELETE文をセット
	        statement = connection.prepareStatement(
	                "delete from subject where cd = ? and school_cd = ?");

	        // プリペアードステートメントに値をバインド
	        statement.setString(1, subject.getSchool().getCd());
	        statement.setString(2, subject.getCd());

	        count = statement.executeUpdate();

	    } catch (Exception e) {
	        throw e;
	    } finally {
	        // ステートメントをクローズ
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        // コネクションをクローズ
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }
	    // 実行結果に応じて真偽値を返す
	    return count > 0;
	}
}

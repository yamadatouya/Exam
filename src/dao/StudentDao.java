package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

	private String baseSql = "select * from student where school_cd=?";

	public Student get(String no) throws Exception {
		//学生インスタンスを初期化
		Student student = new Student();
		//DBへのコネクショを確立
		Connection connection = getConnection();
		//プリペアステートメント
		PreparedStatement statement = null;

		try {
			//プリペアーステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from student where no=?");
			//プリペアーステートメントに学生番号をバインド
			statement.setString(1, no);
			//プライベートステートメントを実行
			ResultSet rSet = statement.executeQuery();

			//学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

			if(rSet.next()) {
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setAttend(rSet.getBoolean("is_attend"));
				//学校フィールドには学校コードで検索した学校インスタンスをセット
				student.setSchool(schoolDao.get(rSet.getString("school_cd")));

			} else {
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				student = null;
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
		return student;
	}

    private List<Student> postFilter(ResultSet rSet, School school) throws Exception {

		//リストの初期化
		List<Student> list = new ArrayList<>();
		//リザルトセットを全権走査
		try {
			while (rSet.next()) {
				//学生インスタンスを初期化
				Student student = new Student();
				//学生インスタンスに検索結果をセット
				student.setNo(rSet.getString("no"));
				student.setName(rSet.getString("name"));
				student.setEntYear(rSet.getInt("ent_year"));
				student.setClassNum(rSet.getString("class_num"));
				student.setAttend(rSet.getBoolean("is_attend"));
				student.setSchool(school);
				//リストに追加
				list.add(student);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
		//リストを初期化
		List<Student> list = new ArrayList<>();
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
		if(isAttend) {
			conditionIsAttend = "and is_attend=true";
		}
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

	public List<Student> filter(School school, int entYear,boolean isAttend) throws Exception {
		//リストの初期化
		List<Student> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		// プリペアーステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//SQL文の条件
		String condition = "and ent_year=?";
		//SQL文のソート
		String order = "order by no asc";
		//SQL文の在学フラグ
		String conditionIsAttend = "";
		//在学フラグがtrueだった場合
		if (isAttend) {
			conditionIsAttend = "and is_attend=true";
		}
		try {
			//プリペアーステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
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

	public List<Student> filter(School school,boolean isAttend) throws Exception {
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
		//SQL文の在学フラグ
		String conditionIsAttend = "";
		//在学フラグがtrueだった場合
		if (isAttend) {
			conditionIsAttend = "and is_attend=true";
		}
		try {
			//プリペアーステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
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

	public boolean save(Student student) throws Exception {
	    // コネクションを確立
	    Connection connection = getConnection();
	    // プリペアーステートメント
	    PreparedStatement statement = null;
	    // 実行件数
	    int count = 0;

	    try {
	        // DBから学生を取得
	        Student old = get(student.getNo());
	        if (old == null) {
	            // 学生が存在しなかった場合
	            // プリペアードステートメントにINSERT文をセット
	            statement = connection.prepareStatement(
	                    "insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, ?, ?)");
	            // プリペアーどステートメントに値をバインド
	            statement.setString(1, student.getNo());
	            statement.setString(2, student.getName());
	            statement.setInt(3,  student.getEntYear());
	            statement.setString(4, student.getClassNum());
	            statement.setBoolean(5, student.isAttend());
	            statement.setString(6,  student.getSchool().getCd());
	        } else {
	            // 学生が存在する場合
	            // プリペアードステートメントにUPDATE文をセット
	            statement = connection.prepareStatement(
	                    "update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?");
	            // プリペアードステートメントに値をバインド
	            statement.setString(1, student.getName());
	            statement.setInt(2,  student.getEntYear());
	            statement.setString(3, student.getClassNum());
	            statement.setBoolean(4, student.isAttend());
	            statement.setString(5, student.getNo());
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
}

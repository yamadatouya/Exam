package bean;

import java.io.Serializable;
import java.util.Map;

public class TestListSubject implements Serializable {

	private int entYear;

	private String studentNo;

	private String studentName;

	private String dassNum;

	private Map<Integer,Integer> points;


	public TestListSubject() {

	}

	public int getEntYear() {
		return entYear;
	}

	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getDassNum() {
		return dassNum;
	}

	public void setDassNum(String dassNum) {
		this.dassNum = dassNum;
	}

	public Map<Integer,Integer> getPoints() {
		return points;
	}

	public void setPoints(Map<Integer,Integer> points) {
		this.points = points;
	}


	public String getPoint(int key) {
		return null;
	}

	public void putPoint(int key, int value) {

	}

}
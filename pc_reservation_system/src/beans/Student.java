package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "student")
public class Student {
	@Id
	@Column(name = "userID")
	private int userID;

	@Column(name = "course/college")
	private String courseAndCollege;

	@Column(name = "status")
	private String status;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getCourseAndCollege() {
		return courseAndCollege;
	}

	public void setCourseAndCollege(String courseAndCollege) {
		this.courseAndCollege = courseAndCollege;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}

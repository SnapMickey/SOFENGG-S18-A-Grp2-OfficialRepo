package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "faculty")
public class Faculty {
	@Id
	@Column(name = "userID")
	private int userID;

	@Column(name = "faculty")
	private String faculty;

	@Column(name = "college")
	private String college;

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public int getUserID() {
		return userID;
	}

	public String getFaculty() {
		return faculty;
	}

	public String getCollege() {
		return college;
	}

	
}

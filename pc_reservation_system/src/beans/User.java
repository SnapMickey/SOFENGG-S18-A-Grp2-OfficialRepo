package beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="user_info")
public class User {

	@Id
	@Column(name = "userID")
	private int userID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "position")
	private String position;

	public int getUserID() {
		return userID;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "User [id=" + userID + ", name=" + name + ", password=" + password + ", email=" + email + ", position="
				+ position + "]";
	}
	
}

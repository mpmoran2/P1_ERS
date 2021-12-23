package persistant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")

public class Employees {
	@Id
	
	@Column(name = "userID")
	private int userID;
	@Column(name = "userName")
	private String userName;
	@Column(name = "userPass")
	private String userPass;
	@Column(name = "firstName")
	private String firstName;
	@Column(name = "lastName")
	private String lastName;
	@Column(name = "title")
	private String title;
	@Column(name = "manager")
	private boolean manager;
	
	public Employees() {
		
	}
	
	public Employees(int userID, String userName, String userPass, String firstName, String lastName, String title, boolean manager) {
		this.userID = userID;
		this.userName = userName;
		this.userPass = userPass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.manager = manager;
	}
	
	//for login emp
	public Employees(String userName, String userPass) {
		this.userName = userName;
		this.userPass = userPass;
	}
	//for manager logins
	public Employees(String userName, String userPass, boolean manager) {
		this.userName = userName;
		this.userPass = userPass;
		this.manager = manager;
	}
	
	//render
	public Employees(int userID) {
		this.userID = userID;
	}
	
	//update
	public Employees(int userID, String userName, String userPass, String firstName, String lastName) {
		this.userID = userID;
		this.userName = userName;
		this.userPass = userPass;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	//usuall getters and setters	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}
	
	//to string
	@Override
	public String toString() {
		return "Employees [userID=" + userID + ", userName=" + userName + ", userPass=" + userPass + ", firstName="
				+ firstName + ", lastName=" + lastName + ", title=" + title + ", manager=" + manager + "]";
	}	
}

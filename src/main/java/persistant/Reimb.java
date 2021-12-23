package persistant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reimb")
public class Reimb {
	@Id
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "reimbID")
	private int reimbID;
	@Column(name = "amount")
	private double amount;
	@Column(name = "status")
	private String status;
	@Column(name = "userID")
	private int userID;
	@Column(name = "catagory")
	private String catagory;
	
	
	public Reimb() {
	
	}

	public Reimb(int reimbID, double amount, String status, int userID, String catagory) {
		this.reimbID = reimbID;
		this.amount = amount;
		this.status = status;
		this.userID = userID;
		this.catagory = catagory;
	}
	
	public Reimb(String status,int userID) {
		this.status = status;
		this.userID = userID;
	}
	
	public Reimb(int userID) {
		this.userID = userID;
	} 
	
	public Reimb(String status) {
		this.status = status;
	}
	
	public Reimb(double amount, String status, int userID, String catagory) {
		this.amount = amount;
		this.status = status;
		this.userID = userID;
		this.catagory = catagory;
	}
	
	//getters and setters
	public int getReimbID() {
		return reimbID;
	}

	public void setReimbID(int reimbID) {
		this.reimbID = reimbID;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	@Override
	public String toString() {
		return "Reimb [reimbID=" + reimbID + ", amount=" + amount + ", status=" + status + ", userID=" + userID
				+ ", catagory=" + catagory + "]";
	}
	
	
}

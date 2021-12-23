package dao;

import java.util.List;

import persistant.Employees;
import persistant.Reimb;

public interface EmpImp {
	public Employees viewInfo(int userID);
	
	public boolean updateInfo(int userID, String userName, String userPass, String firstName, String lastName);
	public boolean submitReimb(int userID, String category, double amount);
	public boolean empLogin(String userName, String userPass);
	
	public List<Reimb> pendingByEmp(int userID);
	public List<Reimb> resolvedByEmp(int userID);
}

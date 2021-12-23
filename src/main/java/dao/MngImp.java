package dao;

import java.util.List;

import persistant.Reimb;

public interface MngImp {
	public boolean denials(int reimbID);
	public boolean approvals(int reimbID);
	public boolean mngLogin(String userName, String userPass);
	
	public List<Reimb> AllPending();
	public List<Reimb> AllResolve();
	public List<Reimb> AllReimbByEmp();
		
}

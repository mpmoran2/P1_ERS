package dao;

import java.util.List;

import org.hibernate.query.Query;

import persistant.Employees;
import persistant.Reimb;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class MngDAO implements MngImp{
	private String userName;
	private String userPass;
	
	private int userID;
	private int reimbID;
	
	private boolean loginVal;
	private boolean approVal;
	private boolean denialVal;	
	
	private List<Employees> AllEmp;
	private List<Reimb> AllPending;
	private List<Reimb> AllResolve;
	private List<Reimb> ReimbByEmp;
	

	public boolean getLoginVal() {
		// TODO Auto-generated method stub
		return loginVal;
	}
	
	public boolean getApproVal() {
		// TODO Auto-generated method stub
		return approVal;
	}
	
	public boolean getDenialVal() {
		// TODO Auto-generated method stub
		return denialVal;
	}
	
	@Override
	public boolean denials(int reimbID) {
		Transaction trans = null;
		Reimb reimb = null;
		boolean denialVal = false;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			trans = session.beginTransaction();
			reimb = session.get(Reimb.class, reimbID);
			
			if(reimb.getStatus().equals("Pending")) {
				reimb.setStatus("Denied");
				session.saveOrUpdate(reimb);
				trans.commit();
				approVal = true;
				this.denialVal=denialVal;
				return this.denialVal;
			}else {
				denialVal=false;
				this.denialVal=denialVal;
				return this.denialVal;
			}
		}catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
		}
		return this.denialVal;
	}
	

	@Override
	public boolean approvals(int reimbID) {
		Transaction trans = null;
		Reimb reimb = null;
		boolean approVal = false;
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			trans = session.beginTransaction();
			reimb = session.get(Reimb.class, reimbID);
			
			if(reimb.getStatus().equals("Pending")) {
				reimb.setStatus("Approved");
				session.saveOrUpdate(reimb);
				trans.commit();
				approVal = true;
				this.approVal=approVal;
				return this.approVal;
			}else {
				approVal=false;
				this.approVal=approVal;
				return this.approVal;
			}
		}catch(Exception e) {
			if(trans != null) {
				trans.rollback();
			}
		}
		return this.approVal;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean mngLogin(String userName, String userPass) {
		this.userName=userName;
		this.userPass=userPass;
		
		Transaction trans = null;
		Employees emp = null;
		boolean loginVal = false;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			trans = session.beginTransaction();
			
			String hql = "FROM reimb WHERE userName =? AND userPass =? AND manager =?";				
			Query q = session.createQuery(hql);
			q.setParameter(1, userName);
			q.setParameter(2, userPass);
			q.setParameter(3, true);
			emp = (Employees) q.uniqueResult();
			System.out.println("Here" + emp);			
			
			trans.commit();
			loginVal = true;
			this.loginVal=loginVal;			
			return this.loginVal;
		} catch(Exception e) {
			
		}
		return this.loginVal;
	}

	@Override
	public List<Reimb> AllPending() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimb> AllResolve() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimb> AllReimbByEmp() {
		// TODO Auto-generated method stub
		return null;
	}

}

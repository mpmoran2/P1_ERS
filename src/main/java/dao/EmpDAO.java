package dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.util.List;

//import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
//import org.postgresql.core.ConnectionFactory;

import persistant.Employees;
import persistant.Reimb;
import util.ConnectUtil;
import util.HibernateUtil;


public class EmpDAO implements EmpImp {
	private boolean loginVal;
	private boolean updateVal;
	private boolean submitVal;
	private int userID;
	private String userName;
	private String userPass;
	private String firstName;
	private String lastName;
	private String title;
	private boolean manager;
	//reimb
	private String category;
	private double amount;
	private Employees emp;
	//lists
	private List<Reimb> listPendingByEmp;
	private List<Reimb> listResolvedByEmp;
	
	public static Connection conn;
	
	
	public boolean getLoginVal() {
		return loginVal;
	}
	
	public boolean getUpdateVal() {
		return updateVal;
	}
	
	public boolean getSubmitVal() {
		return submitVal;
	}
	
	//getset employee
	public Employees getEmp() {
		return emp;
	}
	
	public void setEmployee(Employees employee) {
		this.emp = employee;
	}
	
	//pendinglist get and set for emp
	public List<Reimb> getPendingByEmp(int userID){
		return listPendingByEmp;
	}
	
	public void setPendingbyEmp(List<Reimb> pendingByEmp) {
		this.listPendingByEmp = pendingByEmp;
	}
	//resolved list get and set for emp
	public List<Reimb> getResolvedByEmp(){
		return listResolvedByEmp;
	}
	
	public void setResolvedbyEmp(List<Reimb> resolvedByEmp) {
		this.listResolvedByEmp = resolvedByEmp;
	}
	
	//override methods from here
	@Override 
	//get employee info
	public Employees viewInfo(int userID) {
		this.userID = userID;
		Transaction trans = null;
		Employees emp = new Employees();
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			trans = session.beginTransaction();
			emp = session.get(Employees.class, userID);
			trans.commit();
			this.emp = emp;
			return this.emp;					
		}catch(Exception e) {
			
		}
		return this.emp;
	}
		
	@SuppressWarnings("rawtypes")
	@Override
	public boolean empLogin(String userName, String userPass) {
		this.userName = userName;
		this.userPass = userPass;
		
		Employees emp = null;
		boolean loginVal = false;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM reimb WHERE userName =? AND userPass =? AND manager=?";
			
			Query q = session.createQuery(hql);
			q.setParameter(1, userName);
			q.setParameter(2, userPass);
			q.setParameter(3, false);
			
			emp = (Employees)q.uniqueResult();
			System.out.println("here" + emp);
			
			if(emp.getUserName().equals(userName) && emp.getUserPass().equals(userPass)) {
				loginVal=true;
				this.loginVal=loginVal;
				return loginVal;
			}else {			
				loginVal = false;
				this.loginVal = loginVal;
				return loginVal;
			}
		}catch (Exception e){}
		return this.loginVal;
	}

	@Override
	public boolean updateInfo(String userName, String userPass, String firstName, String lastName) {
		this.userName=userName;
		this.userPass=userPass;
		this.firstName=firstName;
		this.lastName=lastName;	
		
		Transaction trans = null;
		boolean updateVal = false;
		Employees emp = null;
		
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			trans = session.beginTransaction();
			
			emp = session.get(Employees.class, userID);
			emp.setUserName(userName);
			emp.setUserPass(userPass);
			emp.setFirstName(firstName);
			emp.setLastName(lastName);
			session.saveOrUpdate(emp);
			
			trans.commit();
			updateVal = true;
			this.updateVal=updateVal;
			return this.updateVal;
		}catch (Exception e) {
			if(trans != null) {
				trans.rollback();
				updateVal = false;
				return updateVal;
			}
		}
		return this.updateVal;
	}

	@Override
	public boolean submitReimb(int userID, String category, double amount) {		
		this.userID = userID;
		this.category = category;
		this.amount = amount;
		
		Transaction trans = null;
		String status = "Pending";
		boolean submitVal = false;
				
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			trans = session.beginTransaction();
			
			Reimb reimb = new Reimb();
			reimb.setUserID(userID);
			reimb.setCatagory(category);
			reimb.setAmount(amount);
			reimb.setStatus(status);
			session.save(reimb);
			
			trans.commit();
			submitVal = true;
			this.submitVal = submitVal;
			return this.submitVal;
		}catch (Exception e) {
			if(trans != null) {
				trans.rollback();
				submitVal = false;
				return submitVal;
			}
		}
		return this.submitVal;
	}
	
	
	@Override
	public List<Reimb> pendingByEmp(int userID){
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM reimb WHERE userID=" + userID, Reimb.class).list();
		}		
	}
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Reimb> resolvedByEmp(int userID) {
		this.userID = userID;
		
		Transaction trans = null;
		List<Reimb> listResolvedByEmp = null;
		String status = "Pending";
				
		try(Session session = HibernateUtil.getSessionFactory().openSession()){
			trans = session.beginTransaction();
			String hql = "SELECT * FROM reimb WHERE status = 'Pending' OR status = 'Denied' AND userID = '"+userID+"'";
			
			Query q = session.createQuery(hql);
			 listResolvedByEmp = q.list();
			System.out.println("right here" +  listResolvedByEmp);
						
			trans.commit();
			this. listResolvedByEmp= listResolvedByEmp;
			return this.listResolvedByEmp;
		}catch (Exception e) {}
		return this. listResolvedByEmp;
	}

	public void updateInfo(Employees emp2) {
		// TODO Auto-generated method stub
		
	}

}

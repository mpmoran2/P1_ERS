package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.EmpDAO;
import io.javalin.http.Handler;
//import modules.EmpMod;
import persistant.Employees;
import persistant.Reimb;
import util.ConnectUtil;

public class EmpCon2 {
	public static Handler getOne = ctx -> {
		ArrayList<Employees> emp = new ArrayList<>();
		Connection conn = ConnectUtil.getConnection();
		PreparedStatement ps= conn.prepareStatement("select * from users where userID = 2");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Employees e = new Employees(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5));
			emp.add(e);
		}
		ctx.json(emp);
	};
	
	public static Handler getPend = ctx -> {
		EmpDAO ed = new EmpDAO();
		int userID = Integer.parseInt(ctx.pathParam("userID"));		
		ed.getPendingByEmp(userID);		
		List pendingReqsByEmpList = ed.getPendingByEmp(userID);

		ctx.json(pendingReqsByEmpList);
		
		ArrayList<Reimb> rei = new ArrayList<>();
		Connection conn = ConnectUtil.getConnection();
		PreparedStatement ps= conn.prepareStatement("select * from reimb where userID = 1 AND status = 'Pending'");
		ResultSet rs = ps.executeQuery(); 
		while(rs.next()) {
			Reimb r = new Reimb(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getInt(4), rs.getString(5));
			rei.add(r);
		}
		ctx.json(rei);
	};

	public static Handler getResolved= ctx -> {
		ArrayList<Reimb> rei = new ArrayList<>();
		Connection conn = ConnectUtil.getConnection();
		PreparedStatement ps= conn.prepareStatement("select * from reimb where userID = 1 AND status = 'Approved' OR status = 'Denied'");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Reimb r = new Reimb(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getInt(4), rs.getString(5));
			rei.add(r);
		}
		ctx.json(rei);
	};
	
	public static Handler getAll = ctx -> {
		ArrayList<Employees> emp = new ArrayList<>();
		Connection conn = ConnectUtil.getConnection();
		PreparedStatement ps= conn.prepareStatement("select * from users");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Employees e = new Employees(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5));
			emp.add(e);
		}
		ctx.json(emp);
	};
	
	public static Handler getReimbs = ctx -> {
		ArrayList<Reimb> rei = new ArrayList<>();
		Connection conn = ConnectUtil.getConnection();
		PreparedStatement ps= conn.prepareStatement("select * from reimb");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Reimb r = new Reimb(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getInt(4), rs.getString(5));
			rei.add(r);
		}
		ctx.json(rei);
	};
	
	public static Handler updateInfo = ctx -> {
		EmpDAO ed = new EmpDAO();
		String userName = ctx.formParam("userName");
		String userPass = ctx.formParam("userPass");
		String firstName = ctx.formParam("firstName");
		String lastName = ctx.formParam("lastName");
		
		Employees emp = new Employees();
		emp.setUserName(userName);
		emp.setUserPass(userPass);
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		
		ed.updateInfo(emp);
	};
}

package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import io.javalin.http.Handler;
import persistant.Employees;
import persistant.Reimb;
import util.ConnectUtil;

public class MngCon2 {
	public static Handler getPend = ctx -> {
		ArrayList<Reimb> rei = new ArrayList<>();
		Connection conn = ConnectUtil.getConnection();
		PreparedStatement ps= conn.prepareStatement("select * from reimb where status = 'Pending'");
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
		PreparedStatement ps= conn.prepareStatement("select * from reimb where status = 'Approved' OR status = 'Denied'");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Reimb r = new Reimb(rs.getInt(1), rs.getDouble(2), rs.getString(3), rs.getInt(4), rs.getString(5));
			rei.add(r);
		}
		ctx.json(rei);
	};
	
	public static Handler getAllEmp = ctx -> {
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
	
	public static Handler getAllReimb = ctx -> {
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
}

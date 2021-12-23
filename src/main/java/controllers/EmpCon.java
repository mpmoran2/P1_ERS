package controllers;

import java.util.List;

import dao.EmpDAO;
import io.javalin.http.Handler;
import main.Main;
import persistant.Employees;


public class EmpCon {

	public static Handler pendingByEmp= ctx -> {
		EmpDAO eDao = new EmpDAO();
//		int userID = Integer.parseInt(ctx.formParam("userID"));	
		System.out.println("beep");
		int userID = 1;
		eDao.pendingByEmp(userID);
		List listPendingByEmp = eDao.getPendingByEmp();
		System.out.println(listPendingByEmp);
		ctx.json(listPendingByEmp);
	};
	
	public static Handler resolvedByEmp= ctx -> {
		EmpDAO eDao = new EmpDAO();
		int userID = Integer.parseInt(ctx.formParam("userID"));		
		eDao.resolvedByEmp(userID);
		List listResolvedByEmp = eDao.getResolvedByEmp();
		ctx.json(listResolvedByEmp);
	};
	
	public static Handler viewMe= ctx -> {
		EmpDAO eDao = new EmpDAO();
		int userID = Integer.parseInt(ctx.formParam("userID"));	
		eDao.viewInfo(userID);
		Employees emp = eDao.getEmp();
		ctx.json(emp);
	};
	
	public static Handler submitReimb = ctx -> {
		EmpDAO eDao = new EmpDAO();
		boolean submitReimb = false;
		int userID = Integer.parseInt(ctx.formParam("userID"));
		String category = ctx.formParam("category");
		double amount = Double.parseDouble(ctx.formParam("amount"));
		
		eDao.submitReimb(userID, category, amount);
		submitReimb = eDao.getSubmitVal();
		
		if(submitReimb=true) {ctx.redirect("/html/emp/emp_Landing.html");}else {ctx.status(500);}		
	};
	
	public static Handler updateMe= ctx -> {
		EmpDAO eDao = new EmpDAO();
		boolean updateVal =false;
		int userID = Integer.parseInt(ctx.formParam("userID"));	
		String userName = ctx.formParam("userName");
		String userPass = ctx.formParam("userPass");
		String firstName = ctx.formParam("firstName");
		String lastName  = ctx.formParam("lastName");
		
		eDao.updateInfo(userID, userName, userPass, firstName, lastName);
		updateVal = eDao.getUpdateVal();				
		
		if(updateVal=true) {ctx.redirect("/html/emp/emp_Landing.html");}else {ctx.status(500);}
	};
	

}

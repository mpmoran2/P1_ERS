package controllers;

import dao.EmpDAO;
import dao.MngDAO;
import io.javalin.http.Handler;

public class LoginCon {
	public static Handler empLogin = ctx ->{
		EmpDAO eDao = new EmpDAO();
		boolean logIn;		
		String userName = ctx.formParam("userName");
		String userPass = ctx.formParam("userPass");
		
		eDao.empLogin(userName, userPass);
		logIn = eDao.getLoginVal();
		if(logIn = true) {
			System.out.println("going to land");
			ctx.redirect("/html/emp/emp_Landing.html");
		}else {
			ctx.status(403);
			System.out.println("Somethin aint adding up");
		}
	};
	
	public static Handler mngLogin = ctx ->{
		MngDAO mDao = new MngDAO();
		boolean logIn;
		
		String userName = ctx.formParam("userName");
		String userPass = ctx.formParam("userPass");
		
		mDao.mngLogin(userName, userPass);
		logIn = mDao.getLoginVal();
		if(logIn=true) {
			System.out.println("going to land");
			ctx.redirect("/html/mng/mngrMyHome.html");
		}else {
			ctx.status(403);
			System.out.println("Somethin aint adding up");
		}
	};
}

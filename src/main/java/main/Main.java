package main;

import controllers.EmpCon;

//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Properties;
//
//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

import controllers.LoginCon;
import controllers.MngCon;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import org.hibernate.Session;
import org.hibernate.Transaction;

import persistant.Employees;
import persistant.Reimb;
import util.HibernateUtil;

public class Main {
//	private static final String LOG_FILE = "log4j.properties";
	public static void main(String[]args) {
		//LOGGER
//		Logger logger = Logger.getLogger(Driver.class);
//		Properties properties = new Properties();
//		
//		try {
//			properties.load(new FileInputStream(LOG_FILE));
//			PropertyConfigurator.configure(properties);
//		}catch(FileNotFoundException e) {
//			e.printStackTrace();
//		}catch(IOException e) {
//			e.printStackTrace();
//		}
//		logger.setLevel(Level.ALL);
//		logger.warn("Warning Warning!");
//		logger.error("Error Error!");
//		logger.fatal("Uh oh, its fatal");
//		logger.info("...---...---...");
		
//		JAVALIN
		Javalin app = Javalin.create(ctx->{
			ctx.enableCorsForAllOrigins();
			ctx.addStaticFiles("web", Location.CLASSPATH);
		}).start(7007);
		
		//calls
		//logins
		app.post("/LoginEmp", LoginCon.empLogin);
		app.post("/mngLogin", LoginCon.mngLogin);
		
		//employee stuff
		app.get("/emp/pendingReimb", EmpCon.pendingByEmp);
		app.get("/emp/resolvedReimb", EmpCon.resolvedByEmp);
		app.get("/emp/view_Me", EmpCon.viewMe);
		app.post("/emp/view_Me", EmpCon.viewMe);
		app.post("/emp/reimb", EmpCon.submitReimb);
		app.post("/emp/infoUpdate", EmpCon.updateMe);	
		
		//management stuff		
		app.get("/mngr/allPending", MngCon.displayPending);
		app.get("/mngr/allResolved", MngCon.displayResolved);
		app.get("/mngr/allEmp", MngCon.displayAllEmp);
		app.get("/mngr/reimbByEmp/{userID}", MngCon.displayReimbByEmp);
		app.post("/mngr/approvals", MngCon.approval);
		app.post("/mngr/denials", MngCon.denial);
		
	}
}

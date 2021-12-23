package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectUtil {
	public static Connection conn;
	
	public static Connection getConnection() throws SQLException, IOException {				
		try{
			//hide password
			FileInputStream propinput = new FileInputStream("C:\\Users\\srimh\\OneDrive\\Desktop\\ProEkahi\\ReimbApp\\src\\main\\resources\\config.properties");
			Properties props = new Properties();
			props.load(propinput);
		
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			conn = DriverManager.getConnection(url, username, password);
			
			return conn;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

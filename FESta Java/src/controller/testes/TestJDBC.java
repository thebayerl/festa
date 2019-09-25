package controller.testes;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://remotemysql.com:3306/6BkoHsgUIY?useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
		String user = "6BkoHsgUIY";
		String password = "DXaGJHcRpC";
		try {
			System.out.println("onnecting to database: " + jdbcUrl);
			
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, password);
			
			System.out.println("Connection successful!!!");
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

}

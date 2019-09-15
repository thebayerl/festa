package com.testj.dbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/festa?useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
		String user = "admin";
		String password = "123";
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

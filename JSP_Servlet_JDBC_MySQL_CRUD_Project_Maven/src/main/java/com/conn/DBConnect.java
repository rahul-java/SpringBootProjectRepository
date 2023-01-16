package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

	private static Connection conn=null;
	
	public static Connection getConnection()
	{
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rahuldatabase","root","root");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}

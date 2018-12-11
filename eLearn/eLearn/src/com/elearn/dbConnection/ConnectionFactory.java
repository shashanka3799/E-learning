package com.elearn.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearn", "root", "root");

		} 
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return connection;
	}
}
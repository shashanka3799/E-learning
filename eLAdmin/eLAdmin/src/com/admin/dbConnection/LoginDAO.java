package com.admin.dbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.SecretKey;

import sun.security.provider.MD5;



public class LoginDAO {
	ConnectionFactory connectionFactory = new ConnectionFactory();
	Connection con = connectionFactory.getConnection();
	final String secretKey = "ssshhhhhhhhhhh!!!!";

	public boolean validate(String admin_id, String password) throws SQLException {
		

		String password1 = null;
		Statement statement = con.createStatement();
		String query = "select password from User where voter_id ='" + AES.encrypt(admin_id,secretKey) + "'";
		ResultSet resultSet = statement.executeQuery(query);
		
		if (resultSet.next()) {
			password1 = resultSet.getString(1);

		}
		resultSet.close();
		password = AES.encrypt(password, secretKey);
		System.out.println(password + " " + password1);
		//System.out.println(admin_id + " " +password);

		if ((password.equals(password1))) {

			return true;
		}

		return false;
	}
}


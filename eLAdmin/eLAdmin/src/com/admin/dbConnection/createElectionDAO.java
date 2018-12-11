package com.admin.dbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.admin.beans.createElection;

public class createElectionDAO {
	ConnectionFactory connectionFactory = new ConnectionFactory();
	@SuppressWarnings("static-access")
	Connection con = connectionFactory.getConnection();

	public static int createAElection(createElection createElectionBean) throws SQLException {
		int result = 0;
		try {
			Connection connection = ConnectionFactory.getConnection();
			PreparedStatement query = connection.prepareStatement(
					"insert into Elections(E_id,location,post,start_time,end_time) values(?,?,?,?,?)");
			
			query.setString(1, createElectionBean.getE_id());
			query.setString(2, createElectionBean.getLocation());
			query.setString(3, createElectionBean.getPost());
			query.setString(4 ,createElectionBean.getStart_time());
			query.setString(5, createElectionBean.getEnd_time());
			
			
			int resultSet = query.executeUpdate();
			if(resultSet > 0){
				return 1;
				
			} else {
				msgbox("Sorry! Election is not created");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	private static void msgbox(String string) {
		JOptionPane.showMessageDialog(null, string);
		
	}

}



package com.elearn.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;

import java.sql.*;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/Test")
public class Test extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			System.out.println("Entered method");
			String query="insert into student_test(studentId, test_id, test_date, score, count) values(?,?,?,?,?)";
			
		
			String test_id = request.getParameter("test_id");
			String subject = request.getParameter("subject");
			//String test_id = "135c114f-0ae3-4975-8867-423a5f42cc07";
			String correct =request.getParameter("score");
			System.out.println(correct);
			
			if(correct == null)
				correct = "0";
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDateTime now = LocalDateTime.now();
			String date = dtf.format(now);
			
			int score=Integer.parseInt(correct);
			System.out.println("Score is : "+score);
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();  
	    	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/elearn","root","root");  
	    	Statement stmt=con.createStatement();
	    	
	    	String studentId=request.getSession().getAttribute("studentid").toString();
	    	System.out.println(studentId);
	    	ResultSet rs=stmt.executeQuery("select count from student_test where studentId='"+studentId+"'"+"and count IS NOT NULL");
	    	int count=1;
	    	HttpSession session=request.getSession();
	    	if(rs.next()){
	    		count=rs.getInt("count")+1;
	    		stmt.executeUpdate("update student_test set count=count+1 where studentId='"+studentId+"'");
	    		
	    	}
	    		
	    	else{
				PreparedStatement preparedStmt=con.prepareStatement(query);
	
				preparedStmt.setString(1,studentId);
				preparedStmt.setString(2,test_id);
				preparedStmt.setString(3,date);
				preparedStmt.setInt(4,score);
				preparedStmt.setInt(5,count);
				
				
				int resultSet = preparedStmt.executeUpdate();
				System.out.println("Test completed Successfully");
	    	}
	    	if(score > 0){
				session.setAttribute("date",date.toString());
				session.setAttribute("subject","C");
				response.sendRedirect("Certificate.jsp");
			}
	    	else{
	    		JOptionPane.showMessageDialog(null, "You need to score more to get a certificate!");
	    		response.sendRedirect("student_test.jsp");
	    	}
			
			
		}
		catch(Exception e){
			System.out.println("Unsuccessful"+e);
		}
		
	}
}

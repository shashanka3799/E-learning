<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>

<% 
	String id = request.getParameter("id");
	String emailID = null; 
	Connection con = null;
	Statement ps = null;
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearn", "root", "root");
		ps = con.createStatement();
		String sql = "select mailId from students where studentId = '"+id+"' ";
		ResultSet rs = ps.executeQuery(sql);
		if(rs.next()) {
			emailID = rs.getString("mailId");			
		}
		String sql1 = "Update students set status = -1 where studentId= '"+id+"' ";
		ps = con.createStatement();
		int res = ps.executeUpdate(sql1);
		if(res > 0)
			System.out.println("UPDATED!!");
	}	catch(SQLException sql) {
		out.println("no connection");
	}
	
	
	RequestDispatcher rd = getServletContext().getRequestDispatcher("/SendCancellationToVoter?emailID="+emailID);
	rd.forward(request, response);   
%>


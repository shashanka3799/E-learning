package com.elearn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elearn.dbConnection.FacultyLoginDAO;

@WebServlet("/FacultyLogin")
public class FacultyLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String facultyID = request.getParameter("fid");
		String password = request.getParameter("pwd");
		
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		session.setAttribute("faculty_id", facultyID);
		
		FacultyLoginDAO loginDAO = new FacultyLoginDAO();
		
		try {
		    String result = loginDAO.validate(facultyID, password);
		    System.out.println(result);
			if (result.equals("allow")) {
				request.setAttribute("faculty_id", facultyID);
				RequestDispatcher rd = request.getRequestDispatcher("faculty_view_courses.jsp");
				rd.forward(request, response);
				//session.setAttribute("location",result);
			} 
			else {
				RequestDispatcher rd = request.getRequestDispatcher("facultylogin.jsp");
				rd.forward(request, response);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}

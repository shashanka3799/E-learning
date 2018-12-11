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
import com.elearn.dbConnection.StudentLoginDAO;

@WebServlet("/StudentLogin")

public class StudentLogin extends HttpServlet{

		private static final long serialVersionUID = 1L;
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String studentID = request.getParameter("sid");
			String password = request.getParameter("pwd");
			
			System.out.println(request.getParameter("sid"));
			
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			session.setAttribute("studentid", studentID);
			request.setAttribute("student_id", studentID);
			System.out.println("in login "+request.getAttribute("student_id").toString());
			StudentLoginDAO loginDAO = new StudentLoginDAO();
			
			try {
			    String result = loginDAO.validate(studentID, password);
				if (result.equals("allow")) {
					request.setAttribute("student_id", studentID);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("student_view_courses.jsp");
					requestDispatcher.forward(request, response);
					//session.setAttribute("location",result);

				} 
				else {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('User or password incorrect');");
					out.println("location='studentlogin.jsp';");
					out.println("</script>");
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

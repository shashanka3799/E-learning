package com.admin.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SendCancellationToVoter")
public class SendCancellationToVoter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String student_email = request.getParameter("emailID");
		System.out.println("mail id is "+student_email);
	//	h.sendMail(voter_email);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewStudents.jsp");
        dispatcher.forward(request, response); 

	}
	
}
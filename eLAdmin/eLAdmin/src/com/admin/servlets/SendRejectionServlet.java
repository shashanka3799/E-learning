package com.admin.servlets;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.dbConnection.Heyas;

@WebServlet("/SendRejectionServlet")
public class SendRejectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String faculty_email = request.getParameter("emailID");
		//Heyas h = new Heyas();
		System.out.println(faculty_email);
		Heyas.sendMail(faculty_email,"Sorry, your request has been disapproved!");
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewFaculty.jsp");
        dispatcher.forward(request, response); 

	}
	
}
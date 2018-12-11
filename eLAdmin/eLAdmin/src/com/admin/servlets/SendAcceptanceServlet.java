package com.admin.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.dbConnection.Heyas;

@WebServlet("/SendAcceptanceServlet")
public class SendAcceptanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String faculty_email = request.getParameter("emailID");
		System.out.println(faculty_email + "hello");
		Heyas.sendMail(faculty_email,"Congratulations, your request has been as accepted!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewreq.jsp");
        dispatcher.forward(request, response); 
	
	}
/*	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String candidate_email = request.getParameter("emailID");
		Heyas h = new Heyas();
		System.out.println(candidate_email + "hello");
		h.sendMail(candidate_email,"Congratulations, your request has been as accepted!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewreq.jsp");
        dispatcher.forward(request, response); 
	
	}*/
	

}
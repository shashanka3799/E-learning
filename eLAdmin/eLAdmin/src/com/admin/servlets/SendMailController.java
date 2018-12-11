package com.admin.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.dbConnection.Heyas;

@WebServlet("/SendMailController")
public class SendMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String candidate_email = request.getParameter("fp_email");
	//	trial t = new trial();
	//	t.sendMail();
		System.out.println(candidate_email);
		Heyas.sendMail(candidate_email,"Congratulations, your request has been as accepted!");
	
	}

}
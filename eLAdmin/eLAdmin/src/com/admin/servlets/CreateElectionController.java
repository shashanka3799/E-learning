package com.admin.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.beans.createElection;
import com.admin.dbConnection.createElectionDAO;

@WebServlet("/CreateElectionController")
public class CreateElectionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		createElection ce = new createElection();
		ce.setE_id((String)request.getParameter("eid"));
		
		ce.setLocation((String)request.getParameter("location"));
		String customerID = (String) (session.getAttribute("customerID"));
		ce.setPost((String)request.getParameter("post"));
		
		ce.setStart_time(request.getParameter("start_time"));
		ce.setEnd_time(request.getParameter("end_time"));
		
		session.setAttribute("createElection", ce);
		
		try {
			int flag = createElectionDAO.createAElection(ce);
			
			if(flag == 1){
				System.out.println(flag);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.html");
				requestDispatcher.forward(request, response);
			}else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("CreateElection.jsp");
				requestDispatcher.forward(request, response);
			}
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
	



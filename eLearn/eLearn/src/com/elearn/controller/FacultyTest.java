package com.elearn.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@WebServlet("/FacultyTest")
public class FacultyTest extends HttpServlet{
	private static final long serialVersionUID = 1L;
	Connection con;
	String filePath = "";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		
				Class.forName("com.mysql.jdbc.Driver").newInstance();  
		    	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/elearn","root","root");  
		    	
		    	String subject = request.getParameter("subject");
		    	
				Statement statement = con.createStatement();
				String query = "select * from upload_test where subject ='"+subject+"'";
				ResultSet resultSet = statement.executeQuery(query);

				System.out.println("hello .. in faculty test " + subject);
				if (resultSet.next()) {
					filePath = resultSet.getString("file_path");
					System.out.println(filePath);
				}
				resultSet.close();
			
			File stocks = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(stocks);
			doc.getDocumentElement().normalize();
			
			
			System.out.println("root of xml file" + doc.getDocumentElement().getNodeName());
			NodeList nodes = doc.getElementsByTagName("test");
			System.out.println("==========================");
			
			String ques = "";
			int len = nodes.getLength();
			
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
		
					String que = getValue("question", element);
					String op1 = getValue("option1", element);
					String op2 = getValue("option2", element);
					String op3 = getValue("option3", element);
					String op4 = getValue("option4", element);
					String ans = getValue("answer", element);
					
					/*JsonObject j = new JsonObject();
					j.addProperty("que", que);
					j.addProperty("op1", op1);
					j.addProperty("op2", op2);
					j.addProperty("op3", op3);
					j.addProperty("op4", op4);
					j.addProperty("ans", ans);
					
					String obj = "question"+String.valueOf(i);
					JsonObject json = new JsonObject();
					json.add(obj, j);*/
					
					ques += que + "`" + op1 + "`" + op2 + "`" + op3 + "`" + op4 + "`" + ans + "`";
				}
			}
			System.out.println(ques);
			request.setAttribute("ques", ques);
			request.setAttribute("length", len);
			//HttpSession session = request.getSession();
			System.out.println("in faculty test java");
			String fid = request.getParameter("fid").toString();
			System.out.println(len);
			request.setAttribute("faculty_id", fid);
			RequestDispatcher rd = request.getRequestDispatcher("/test_faculty.jsp");
			rd.forward(request, response);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static String getValue(String tag, Element element) {
		NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodes.item(0);
		return node.getNodeValue();
	}
}

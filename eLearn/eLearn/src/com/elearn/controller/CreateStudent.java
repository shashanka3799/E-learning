package com.elearn.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;
import com.elearn.beans.StudentBean;	
import com.elearn.dbConnection.*;


@SuppressWarnings("serial")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
@WebServlet("/CreateStudent")

public class CreateStudent extends HttpServlet {
	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");

			for (String content : part.getHeader("content-disposition").split(";")) {
				if (content.trim().startsWith("filename")) {
					return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
				}
			}
			return null;
	}
	
	@SuppressWarnings("resource")
	private String uploadPhoto(String path, Part filePart){
		
		String photo = "";
		String fileName = getFileName(filePart);
		try {

			File file = new File(path);
			file.mkdir();
			//String fileName = getFileName(filePart);
			int index = fileName.lastIndexOf("\\");
			fileName = fileName.substring(index + 1);

			OutputStream os = null;

			InputStream filecontent = null;

			os = new FileOutputStream(new File(path + "\\" + fileName));
				
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[104857600];

			while ((read = filecontent.read(bytes)) != -1) {
				os.write(bytes, 0, read);
			}
				photo = path + "\\" + fileName;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return fileName;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// gets values of text fields
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			String fileName = "";
			
			System.out.println("helo");
			Part filePart = request.getPart("image");
			
			try {
				
				String path = "F:\\santhu2\\eLAdmin\\WebContent\\images";
				uploadPhoto(path, filePart);
				path = "F:\\santhu2\\eLearn\\WebContent\\images";
				fileName = uploadPhoto(path, filePart);
				
			}
			catch(Exception e){
				System.out.println(e);
			}
			
			StudentBean sb = new StudentBean();
			
			
			if(!request.getParameter("pwd").equals(request.getParameter("repwd"))){
				
				   out.println("<script type=\"text/javascript\">");
				   out.println("alert('password does not match');");
				   out.println("</script>");
				
			}
			sb.setStudent_Id(request.getParameter("sid"));
			sb.setStudent_Name(request.getParameter("sname"));
			sb.set_branch(request.getParameter("branch"));
			sb.setEmailId(request.getParameter("mailId"));
			sb.set_password(request.getParameter("pwd"));
			sb.setMobileNo(request.getParameter("mobile"));
			sb.setImagePath(fileName);
			
			
			try {
				int res = CreateStudentDAO.createStudentInfo(sb);
				if(res > 0){
					JOptionPane.showMessageDialog(null, "Registered Successfully!!");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("studentlogin.jsp");
					requestDispatcher.forward(request, response);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Couldn't Register!");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("registerStudent.jsp");
					requestDispatcher.include(request, response);
					
				}
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}	
		}


}

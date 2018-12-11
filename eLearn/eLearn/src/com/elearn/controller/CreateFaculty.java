package com.elearn.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

import com.elearn.beans.*;
import com.elearn.dbConnection.*;


@SuppressWarnings("serial")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)

@WebServlet("/CreateFaculty")
public class CreateFaculty extends HttpServlet {
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// gets values of text fields
		response.setContentType("text/html; charset=UTF-8");
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
		
		System.out.println(fileName);
		FacultyBean fb = new FacultyBean();
		
		String subjects = "";
		String[] names = request.getParameterValues("subject");
		for(int i = 0; i < names.length; i++)
			subjects = subjects + " " + names[i]; 
		
		System.out.println(subjects);
		
		fb.setFaculty_Id  (request.getParameter("fid"));
		fb.setFaculty_Name(request.getParameter("fname"));
		fb.set_branch     (request.getParameter("branch"));
		fb.set_designation(request.getParameter("designation"));
		fb.setEmailId     (request.getParameter("mailId"));
		fb.set_password   (request.getParameter("pwd"));
		fb.set_phone_no   (request.getParameter("phno"));
		fb.setImagePath   (fileName);
		fb.set_subjects   (subjects);
		
		try {
			int res = CreateFacultyDAO.createFacultyInfo(fb);
			
			if(res > 0){
				System.out.println("successful");
				JOptionPane.showMessageDialog(null, "Registered Successfully!!");
				RequestDispatcher rd = request.getRequestDispatcher("facultylogin.jsp");
				rd.include(request, response);
			}
			else {
				System.out.println("unsuccessful");
				JOptionPane.showMessageDialog(null, "Couldn't Register!");
				RequestDispatcher rd = request.getRequestDispatcher("registerFaculty.jsp");
				rd.include(request, response);
			}
			System.out.println("after dispatch");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
			
	}
}



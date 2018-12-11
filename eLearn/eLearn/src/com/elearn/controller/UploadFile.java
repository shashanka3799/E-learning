package com.elearn.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.elearn.beans.UploadFileBean;
import com.elearn.dbConnection.UploadFileDAO;

@WebServlet("/UploadFile")

public class UploadFile extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private String getFileName(final Part part) {
		@SuppressWarnings("unused")
		
		final String partHeader = part.getHeader("content-disposition");

		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
	
	private String uploadFile(String path, Part filePart){
		
		String filePath = "";
		String fileName = getFileName(filePart);
		try {

			File file = new File(path);
			file.mkdir();
			fileName = getFileName(filePart);
			int index = fileName.lastIndexOf("/");
			fileName = fileName.substring(index + 1);

			OutputStream os = null;

			InputStream filecontent = null;

			System.out.println("before try");

			System.out.println("after try");
			os = new FileOutputStream(new File(path + "\\" + fileName));
				
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[10000];

			while ((read = filecontent.read(bytes)) != -1) {
				os.write(bytes, 0, read);
			}
			filePath = path + "/" + fileName;
			os.close();
			filecontent.close();
			System.out.println("file path : "+filePath); 
		}
		catch (Exception e) {
			System.out.println("exception");
			System.out.println(e);
		}
		
		return filePath;
	}

	@MultipartConfig
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// gets values of text fields
		response.setContentType("text/html; charset=UTF-8");
		String filePath = ""; 
		System.out.println("hello");
		
		try{
			//HttpSession session = request.getSession();
			System.out.println("hi");
			Part filePart = request.getPart("file1");
			System.out.println("hi");
			String path = "F:/santhu2/eLAdmin/WebContent/files";
			uploadFile(path, filePart);
			path = "F:/santhu2/elearn/WebContent/files";
			filePath = uploadFile(path, filePart);
		}catch(Exception e){
			System.out.println(e);
		}

		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String date = dtf.format(now);
		System.out.println(date);
		System.out.println(filePath);
		
		UploadFileBean upload = new UploadFileBean();
		
		String subject = request.getParameter("subject");
		System.out.println("in upload file : "+request.getParameter("fid"));
		
		upload.set_faculty_id(request.getParameter("fid"));
		upload.set_file_name (request.getParameter("filename"));
		upload.set_subject   (subject);
		upload.set_date(date);
		upload.set_file_path(filePath);
		
		try {
			int res = UploadFileDAO.createFacultyInfo(upload);
			System.out.println("Result is : "+res);
			if(res > 0){
				request.setAttribute("faculty_id", request.getParameter("fid"));
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("faculty_view_courses.jsp");
				requestDispatcher.forward(request, response);
			}
			else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("faculty_view_courses.jsp");
				requestDispatcher.forward(request, response);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}

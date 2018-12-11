package com.elearn.beans;


public class FacultyBean {
	private String faculty_id, faculty_name, branch, designation, mail_id, password, img_path, subjects, phone_no;
	private int status;
	
	public String getFaculty_Id() {
		return faculty_id;
	}
	
	public void setFaculty_Id(String facultyId) {
		faculty_id = facultyId;
	}
	
	public String getFaculty_Name() {
		return faculty_name;
	}
	
	public void setFaculty_Name(String fname) {
		faculty_name = fname;
	}
	
	public String get_branch() {
		return branch;
	}
	
	public void set_branch(String branch) {
		this.branch = branch;
	}
	
	public String get_designation() {
		 return designation;
	}
	
	public void set_designation(String desig) {
		designation = desig;
	}
	
	public String getEmailId() {
		return mail_id;
	}
	
	public void setEmailId(String emailId) {
		mail_id = emailId;
	}
	
	public String get_password(){
		return password;
	}
	
	public void set_password(String pwd){
		password = pwd;
	}
	
	public String getImagePath() {
		return img_path;
	}
	
	public void setImagePath(String imagePath) {
		img_path = imagePath;
	}
	
	public void set_status(int value) {
		status = value;
	}
	
	public int get_status() {
		return status;
	}
	
	public void set_subjects(String value){
		subjects = value;
	}
	
	public String get_subjects(){
		return subjects;
	}
	
	public void set_phone_no(String phno){
		phone_no = phno;
	}
	
	public String get_phone_no(){
		return phone_no;
	}
	
}

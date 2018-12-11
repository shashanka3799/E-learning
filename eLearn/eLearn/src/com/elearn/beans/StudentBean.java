package com.elearn.beans;

public class StudentBean{

		private String student_id, student_name, branch, mail_id, password, img_path,mobile;
		
		
		public String getStudent_Id() {
			return student_id;
		}
		
		public void setStudent_Id(String facultyId) {
			student_id = facultyId;
		}
		
		public String getStudent_Name() {
			return student_name;
		}
		
		public void setStudent_Name(String fname) {
			student_name = fname;
		}
		
		public String get_branch() {
			return branch;
		}
		
		public void set_branch(String branch) {
			this.branch = branch;
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
		
		public void setMobileNo(String mob){
			mobile=mob;
		}
		
		public String getMobileNo(){
			return mobile;
		}

}

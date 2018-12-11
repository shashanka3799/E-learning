package com.elearn.beans;


public class UploadFileBean {
	
	private String faculty_id, file_name, upload_date, subject, file_path, test_id;
	
	public String get_faculty_id() {
		return faculty_id;
	}
	
	public void set_faculty_id(String facultyId) {
		faculty_id = facultyId;
	}
	
	public String get_file_name(){
		return file_name;
	}
	
	public void set_file_name(String fileName){
		file_name = fileName;
		System.out.println("set : "+file_name);
	}
	
	public String get_upload_date(){
		return upload_date;
	}
	
	public void set_date(String date){
		upload_date = date;
	}
	
	public String get_subject(){
		return subject;
	}
	
	public void set_subject(String subj){
		subject = subj;
	}
	
	public void set_file_path(String path){
		file_path = path;
	}
	
	public String get_file_path(){
		return file_path;
	}
	
	public void set_test_id(String fileId){
		test_id = fileId;
	}
	
	public String get_test_id(){
		return test_id;
	}
	
}

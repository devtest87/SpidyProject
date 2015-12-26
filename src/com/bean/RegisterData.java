package com.bean;

public class RegisterData {

	//success
//	{"error":"success","success_msg":"Thankyou for Registration,please check your mail for password."}
	
	//unsuccess
//	{"error":"unsuccess","error_msg":"Required parameters (name, email or password) is missing!"}
	
	private String exception = "noexception";
	private String error_msg;
	private String success_msg;
	private String error;
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	public String getSuccess_msg() {
		return success_msg;
	}
	public void setSuccess_msg(String success_msg) {
		this.success_msg = success_msg;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
}

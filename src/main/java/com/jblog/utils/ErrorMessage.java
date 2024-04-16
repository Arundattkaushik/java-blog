package com.jblog.utils;

import org.springframework.stereotype.Service;

@Service
public class ErrorMessage {

	private String msg;
	private String status;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ErrorMessage(String msg, String status) {
		super();
		this.msg = msg;
		this.status = status;
	}
	public ErrorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

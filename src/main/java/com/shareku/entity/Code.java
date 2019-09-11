package com.shareku.entity;

public class Code {

	private String code; //类型代码
	private String view; //代码显示的名称
	
	public Code() {
	}
	
	public Code(String code, String view) {
		this.code = code;
		this.view = view;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
}

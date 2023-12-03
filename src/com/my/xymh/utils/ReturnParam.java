package com.my.xymh.utils;

public class ReturnParam {

	private String desc;
	private Integer status;
	private Object result;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public ReturnParam() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReturnParam(String desc, Integer status, Object result) {
		super();
		this.desc = desc;
		this.status = status;
		this.result = result;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
}

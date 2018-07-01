package com.tk.util;

import java.io.Serializable;

public class JsonResultDO implements Serializable {

	public static final String JSON_EMPTY_ARRAY = "[]";

	/**
	 * Description: 
	 */
	private static final long serialVersionUID = 4505856039913374278L;

	private boolean success = true;

	private String msg;

	private int code = ResultCode.SUCCESS;

	private String jsessionid;

	private String ticket;

	private Object data;

	private int total;

	public JsonResultDO() {
	}

	public JsonResultDO(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getJsessionid() {
		return jsessionid;
	}

	public void setJsessionid(String jsessionid) {
		this.jsessionid = jsessionid;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}

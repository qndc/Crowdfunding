package com.atguigu.atcrowdfunding.util;

public class AjaxResult {

	private Integer status;
	private Object message;

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Object getMessage() {
		return this.message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public AjaxResult(Integer status, Object message) {
		this.status = status;
		this.message = message;
	}

	public AjaxResult() {
	}

	public String toString() {
		return "AjaxResult [status=" + this.status + ", message=" + this.message + "]";
	}

}

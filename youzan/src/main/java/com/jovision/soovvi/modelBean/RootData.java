package com.jovision.soovvi.modelBean;

/**
 * 作为http请求返回的数据对象
 * @author liuhailong
 *
 */
public class RootData {
	
	//返回请求结果的正确性
	private String result;
	//返回请求结果的信息
	private String msg;
	//请求的正式数据
	private Object data;
	//请求的任务信息
	private String errorCode;
	
	
	public RootData() {
	}
	
	
	public RootData(String result, String msg, Object data, String errorCode) {
		this.result = result;
		this.msg = msg;
		this.data = data;
		this.errorCode = errorCode;
	}


	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}

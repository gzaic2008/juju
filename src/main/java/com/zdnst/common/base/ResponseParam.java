package com.zdnst.common.base;

import java.io.Serializable;

public class ResponseParam implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3972480884860498033L;
	String hash;
	String message;
	String code;// 默认 0失败,1成功
	long timestamp = System.currentTimeMillis();
	Object data;// 返回数据的具体实体
	private String sid; // sessionid

	public ResponseParam() {
	}

	public ResponseParam(String hash, String message, String code, Object data,
			String sid, long timestamp) {
		this.hash = hash;
		this.message = message;
		this.code = code;
		this.data = data;
		this.sid = sid;
		this.timestamp = timestamp;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}

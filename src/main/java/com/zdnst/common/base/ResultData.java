/**
 * 
 */
package com.zdnst.common.base;

/**
 * @author zhihong.peng
 * 
 */
public class ResultData<T> implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1552014397715291170L;

	private String hash;
	private String message;
	private String code;// 默认 0失败,1成功
	private long timestamp = System.currentTimeMillis();
	private String sid; // sessionid
	private T data;

	public ResultData() {

	}

	public ResultData(T data) {
		this.data = data;

	}

	/**
	 * @param hash
	 * @param message
	 * @param code
	 * @param timestamp
	 * @param sid
	 * @param data
	 */
	public ResultData(String hash, String message, String code, long timestamp,
			String sid, T data) {
		this.hash = hash;
		this.message = message;
		this.code = code;
		this.timestamp = timestamp;
		this.sid = sid;
		this.data = data;
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

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		ResultData other = (ResultData) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (hash == null) {
			if (other.hash != null)
				return false;
		} else if (!hash.equals(other.hash))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		if (timestamp != other.timestamp)
			return false;
		return true;
	}

}

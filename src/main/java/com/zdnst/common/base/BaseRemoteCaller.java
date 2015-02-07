package com.zdnst.common.base;

import java.sql.Timestamp;
import java.util.List;

/**
 * 远程调用方法
 * 
 * @author zhihong.peng
 * 
 */
public class BaseRemoteCaller implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String METHOD_ADD = "1";
	public static final String METHOD_UPDATE = "2";
	public static final String METHOD_DELETE = "3";

	private String name; // 名称
	private String subject; // 主题名称
	private String method; // 方法
	private String application; // 应用
	private String host; // 主机
	private String company; // 公司
	private String sendUserID; // 发送用户ID
	private String sendUserName; // 发送用户名称
	private String sendTime; // 发送时间
	private String remoteIP;
	private Timestamp finishTime;

	private List<BaseField> fieldList;

	public BaseRemoteCaller() {

	}

	public BaseRemoteCaller(String name, String subject, String method,
			String application, String host) {

		this.name = name;
		this.subject = subject;
		this.method = method;
		this.application = application;
		this.host = host;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method
	 *            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the application
	 */
	public String getApplication() {
		return application;
	}

	/**
	 * @param application
	 *            the application to set
	 */
	public void setApplication(String application) {
		this.application = application;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the sendUserID
	 */
	public String getSendUserID() {
		return sendUserID;
	}

	/**
	 * @param sendUserID
	 *            the sendUserID to set
	 */
	public void setSendUserID(String sendUserID) {
		this.sendUserID = sendUserID;
	}

	/**
	 * @return the sendUserName
	 */
	public String getSendUserName() {
		return sendUserName;
	}

	/**
	 * @param sendUserName
	 *            the sendUserName to set
	 */
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}

	/**
	 * @return the sendTime
	 */
	public String getSendTime() {
		return sendTime;
	}

	/**
	 * @param sendTime
	 *            the sendTime to set
	 */
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	/**
	 * @return the remoteIP
	 */
	public String getRemoteIP() {
		return remoteIP;
	}

	/**
	 * @param remoteIP
	 *            the remoteIP to set
	 */
	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	/**
	 * @return the finishTime
	 */
	public Timestamp getFinishTime() {
		return finishTime;
	}

	/**
	 * @param finishTime
	 *            the finishTime to set
	 */
	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}

	/**
	 * @return the fieldList
	 */
	public List<BaseField> getFieldList() {
		return fieldList;
	}

	/**
	 * @param fieldList
	 *            the fieldList to set
	 */
	public void setFieldList(List<BaseField> fieldList) {
		this.fieldList = fieldList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseRemoteCaller [name=" + name + ", subject=" + subject
				+ ", method=" + method + ", application=" + application
				+ ", host=" + host + ", company=" + company + ", sendUserID="
				+ sendUserID + ", sendUserName=" + sendUserName + ", sendTime="
				+ sendTime + ", remoteIP=" + remoteIP + ", finishTime="
				+ finishTime + ", fieldList=" + fieldList + "]";
	}

	 

}

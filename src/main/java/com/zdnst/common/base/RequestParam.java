package com.zdnst.common.base;

import com.zdnst.common.utils.CommonUtils;


public class RequestParam implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3402487359191190114L;
	private String id;//
	private String userId;//用户ID
	private String hash; // sessionId
	private String name;
	private String deviceId;// 设备ID
	private String timestamp;//
	private String currentPage = "1";// 当前页 从1开始 默认1
	private String pageSize = "20";// 默认20
	private String type;// 类型
	private String condition;//条件
	private Object params;  // 自定义参数
	private String city;// 传入中文或者key值，
	private String ox;//经度
	private String oy;//纬度
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		
		return name;
	}

	public void setName(String name) {
		name=CommonUtils.fromDecodeUTF8(name);
		this.name = name;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Object getParams() {
		return params;
	}

	public void setParams(Object params) {
		this.params = params;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		condition=CommonUtils.fromDecodeUTF8(condition);
		this.condition = condition;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		city=CommonUtils.fromDecodeUTF8(city);
		this.city = city;
	}

	public String getOx() {
		return ox;
	}

	public void setOx(String ox) {
		this.ox = ox;
	}

	public String getOy() {
		return oy;
	}

	public void setOy(String oy) {
		this.oy = oy;
	}
	
	

}

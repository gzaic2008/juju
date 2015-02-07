package com.zdnst.common.base;

public class WebUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String img;
	private String sessionId;
	private String groupId;
	private String groupName;
	
	
	
	
	
	/**
	 * @param id
	 * @param name
	 * @param img
	 * @param sessionId
	 */
	public WebUser(String id, String name, String img, String sessionId) {
		this.id = id;
		this.name = name;
		this.img = img;
		this.sessionId = sessionId;
	}
	
	
	
	
	
	/**
	 * @param id
	 * @param name
	 * @param img
	 * @param sessionId
	 * @param groupId
	 * @param groupName
	 */
	public WebUser(String id, String name, String img, String sessionId,
			String groupId, String groupName) {
		this.id = id;
		this.name = name;
		this.img = img;
		this.sessionId = sessionId;
		this.groupId = groupId;
		this.groupName = groupName;
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
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
	
	

}

/**
 * Copyright(C) 2012-2014 GuangZhou zdnst Co., Ltd. All Rights Reserved.<br/>
 * 版权所有(C)2012-2014 <br/>
 * 公司名称：广州支点科技有限公司<br/>
 * 公司地址：广州市天河区天源路401号之三E2栋<br/>
 * 网址：http://www.100100system.com/<br/>
 * <p>标        题：</p>
 * <p>文  件  名：com.zdnst.common.utils.im.dto.ChatUser.java</p>
 * <p>部        门：研发一部
 * <p>版        本： 1.0</p>
 * <p>Compiler: JDK1.6.0_21</p>
 * <p>创  建  者：kui.he</p>
 * <p>创建时间：2014年9月25日上午11:45:12</p>
 * <p>修  改  者：</p>
 * <p>修改时间：</p>
 */
package com.zdnst.common.utils.im.dto;
/***
 * 
 * <p>描        述：环信用户对象</p>
 * <p>项目名称:maps1.1</p>
 * <p>所在位置:com.zdnst.common.utils.im.dtoChatUser.java</p>
 * <p>类名:ChatUser.java</p>
 * <p>创  建  人：kui.he</p>
 * <p>创建时间：2014年9月25日上午11:47:27</p>
 */
public class ChatUser {
	private String uuid;
	private String type;
	private Long created;
	private Long modified;
	private String username;
	private boolean activated;
	private String device_token;
	private String notifier_name;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public Long getModified() {
		return modified;
	}

	public void setModified(Long modified) {
		this.modified = modified;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public String getNotifier_name() {
		return notifier_name;
	}

	public void setNotifier_name(String notifier_name) {
		this.notifier_name = notifier_name;
	}

}

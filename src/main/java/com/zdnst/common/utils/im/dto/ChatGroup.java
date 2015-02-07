/**
 * Copyright(C) 2012-2014 GuangZhou zdnst Co., Ltd. All Rights Reserved.<br/>
 * 版权所有(C)2012-2014 <br/>
 * 公司名称：广州支点科技有限公司<br/>
 * 公司地址：广州市天河区天源路401号之三E2栋<br/>
 * 网址：http://www.100100system.com/<br/>
 * <p>标        题：</p>
 * <p>文  件  名：com.zdnst.common.utils.im.dto.ChatGroup.java</p>
 * <p>部        门：研发一部
 * <p>版        本： 1.0</p>
 * <p>Compiler: JDK1.6.0_21</p>
 * <p>创  建  者：kui.he</p>
 * <p>创建时间：2014年9月24日下午5:07:29</p>
 * <p>修  改  者：</p>
 * <p>修改时间：</p>
 */
package com.zdnst.common.utils.im.dto;
/***
 * 
 * <p>描        述：环信聊天群组对象</p>
 * <p>项目名称:maps1.1</p>
 * <p>所在位置:com.zdnst.common.utils.im.dtoChatGroup.java</p>
 * <p>类名:ChatGroup.java</p>
 * <p>创  建  人：kui.he</p>
 * <p>创建时间：2014年9月25日上午11:47:50</p>
 */
public class ChatGroup {
	// groupid=1408015801024, groupname=初一4班, owner=zdnst#juju_10128,
	// affiliations=1
	private String groupid;
	private String groupname;
	private String owner;
	private String affiliations;

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAffiliations() {
		return affiliations;
	}

	public void setAffiliations(String affiliations) {
		this.affiliations = affiliations;
	}

}

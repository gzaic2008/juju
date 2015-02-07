/**
 * Copyright(C) 2012-2014 GuangZhou zdnst Co., Ltd. All Rights Reserved.<br/>
 * 版权所有(C)2012-2014 <br/>
 * 公司名称：广州支点科技有限公司<br/>
 * 公司地址：广州市天河区天源路401号之三E2栋<br/>
 * 网址：http://www.100100system.com/<br/>
 * <p>标        题：</p>
 * <p>文  件  名：com.zdnst.maps.service.im.impl.ImMessageSyncServiceImpl.java</p>
 * <p>部        门：研发一部
 * <p>版        本： 1.0</p>
 * <p>Compiler: JDK1.6.0_21</p>
 * <p>创  建  者：kui.he</p>
 * <p>创建时间：2014年8月6日下午10:45:27</p>
 * <p>修  改  者：</p>
 * <p>修改时间：</p>
 */
package com.zdnst.common.utils.im;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.zdnst.common.utils.CommonUtils;
import com.zdnst.common.utils.SystemProperties;
import com.zdnst.common.utils.im.dto.ChatGroup;
import com.zdnst.common.utils.im.dto.ChatUser;

public class ImMessageUtil {

	static String host = "a1.easemob.com";
	static String appKey = "zdnst#juju";
	static String clientId = "YXA64RsAEBbxEeSoKmGtGo1Qdw";
	static String clientSecret = "YXA6YXutPE-jRJrMXln0tA1ApT5kEQA";

	/**
	 * @describe：获取验证 token
	 * @return
	 * @author:kui.he
	 * @time:2014年9月24日下午12:04:46
	 */
	private static String getUserToken() {
		host = SystemProperties.getProperties().getProperty("xmpp.hx.host");
		appKey = SystemProperties.getProperties().getProperty("xmpp.hx.appKey");//
		clientId = SystemProperties.getProperties().getProperty(
				"xmpp.hx.clientId");//
		clientSecret = SystemProperties.getProperties().getProperty(
				"xmpp.hx.clientSecret");//
		// 获取IM用户token
		Map<String, Object> getIMAccessTokenPostBody = new HashMap<String, Object>();
		getIMAccessTokenPostBody.put("grant_type", "client_credentials");
		getIMAccessTokenPostBody.put("client_id", clientId);
		getIMAccessTokenPostBody.put("client_secret", clientSecret);
		String imToken = EasemobUserAPI.getAccessToken(host, appKey, false,
				getIMAccessTokenPostBody);
		return imToken;
	}

	/***
	 * @describe：发送消息
	 * @param from
	 *            发送人
	 * @param to
	 *            接收人,多个以逗号隔开
	 * @param msgContent
	 *            消息内容,消息内容以key-value 模式
	 * @return
	 * @author:kui.he
	 * @time:2014年9月24日下午12:02:41
	 */
	public static String sendMessage(String from, String to,
			Map<String, String> msgContent) {
		String token = getUserToken();
		host = SystemProperties.getProperties().getProperty("xmpp.hx.host");
		appKey = SystemProperties.getProperties().getProperty("xmpp.hx.appKey");//
		ArrayList<String> toUsers = new ArrayList<String>();
		// 判断用户是否有多个
		String[] toUserIds = to.split(",");
		for (int i = 0; i < toUserIds.length; i++) {
			String uid = toUserIds[i];
			if (uid == null || uid.isEmpty()) {
				continue;
			}
			toUsers.add(uid);
		}
		String response = "";
		try {
			response = EasemobUserAPI.sendMessage(host, appKey, token, from,
					toUsers, msgContent);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return response;
	}
	
	/***
	 * @describe：发送消息
	 * @param from
	 *            发送人
	 * @param to
	 *            接收人,多个以逗号隔开
	 * @param msgContent
	 *            消息内容,消息内容以key-value 模式
	 * @return
	 * @author:kui.he
	 * @time:2014年9月24日下午12:02:41
	 */
	public static String sendTxtMessage(String from, String to,
			String msgContent) {
		String token = getUserToken();
		host = SystemProperties.getProperties().getProperty("xmpp.hx.host");
		appKey = SystemProperties.getProperties().getProperty("xmpp.hx.appKey");//
		ArrayList<String> toUsers = new ArrayList<String>();
		// 判断用户是否有多个
		String[] toUserIds = to.split(",");
		for (int i = 0; i < toUserIds.length; i++) {
			String uid = toUserIds[i];
			if (uid == null || uid.isEmpty()) {
				continue;
			}
			toUsers.add(uid);
		}
		String response = "";
		try {
			Map<String,String> msgMap=new HashMap<String,String>();
			msgMap.put("type", "txt");
			msgMap.put("msg", msgContent);
			response = EasemobUserAPI.sendMessage(host, appKey, token, from,
					toUsers, msgMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return response;
	}

	/**
	 * 
	 * @describe：获取app中所有的群组ID
	 * @return
	 * @author:kui.he
	 * @time:2014年9月24日下午12:08:34
	 */
	public static List<ChatGroup> chatgroups() {
		String token = getUserToken();
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		String url = "https://a1.easemob.com/" + orgName + "/" + appName
				+ "/chatgroups";
		String result = HttpsUtils.sendSSLRequest(url, token, null,
				HttpsUtils.Method_GET);
		JSONObject json = JSONObject.fromObject(result);
		if (json.containsKey("data")) {
			List<ChatGroup> list = JSONArray.toList(json.getJSONArray("data"),
					ChatGroup.class);
			return list;
		}
		return null;
	}

	/**
	 * @describe：获取一个或者多个群组的详情
	 * @param chatgroupId
	 *            群组ID,多个以逗号隔开
	 * @return
	 * @author:kui.he
	 * @time:2014年9月24日下午12:11:22
	 */
	public static String getChatgroups(String chatgroupId) {
		String token = getUserToken();
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		String url = "https://a1.easemob.com/" + orgName + "/" + appName
				+ "/chatgroups/" + chatgroupId;
		return HttpsUtils.sendSSLRequest(url, token, null,
				HttpsUtils.Method_GET);
	}

	/**
	 * @describe：创建一个圈子
	 * @param groupname
	 *            圈子名称
	 * @param desc
	 *            描述(可为空)
	 * @param pub
	 *            是否公开 (默认为true)
	 * @param owner
	 *            管理员
	 * @param members
	 *            圈子成员列表(可为空)
	 * @return 返回创建成功后的圈子ID,失败返回 null
	 * @author:kui.he
	 * @time:2014年9月24日下午12:17:25
	 */
	public static String createChatgroups(String groupname, String desc,
			String owner, String[] members) {
		String token = getUserToken();
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		// GET /{org_name}/{app_name}/chatgroups/{group_id1},{group_id2}
		String url = "https://a1.easemob.com/" + orgName + "/" + appName
				+ "/chatgroups/";
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("groupname", CommonUtils.getString(groupname, ""));// 群组名称,
		param.put("desc", CommonUtils.getString(desc, ""));// 群组描述, 此属性为必须的
		param.put("public", true);// 是否是公开群, 此属性为必须的
		param.put("approval", false);// 加入公开群是否需要批准, 没有这个属性的话默认是true, 此属性为可选的
		param.put("owner", owner);// 群组的管理员, 此属性为必须的
		if (members != null && members.length > 0) {
			param.put("members", members);// 群组成员,此属性为可选的
		}
		String result = HttpsUtils.sendSSLRequest(url, token,
				HttpsUtils.Map2Json(param), HttpsUtils.Method_POST);
		JSONObject json = JSONObject.fromObject(result);
		if (json.containsKey("data")) {
			return json.getJSONObject("data").getString("groupid");
		}
		return null;
	}

	/**
	 * @describe：根据圈子ID删除圈子
	 * @param groupId
	 * @return
	 * @author:kui.he
	 * @time:2014年9月24日下午3:55:46
	 */
	public static boolean deleteChatgroup(String groupId) {
		// DELETE /{org_name}/{app_name}/chatgroups/{group_id}
		EaseRequest eq = getRoot();
		String url = eq.getUrl() + "chatgroups/" + groupId;
		String result = HttpsUtils.sendSSLRequest(url, eq.getToken(), null,
				HttpsUtils.Method_DELETE);
		JSONObject json = JSONObject.fromObject(result);
		if (json.containsKey("data")) {
			return json.getJSONObject("data").getBoolean("success");
		}
		return false;
	}

	/***
	 * @describe：根据圈子ID获取圈子成员
	 * @param groupId
	 * @return
	 * @author:kui.he
	 * @time:2014年9月24日下午4:58:38
	 */
	public static String getGroupMembers(String groupId) {
		EaseRequest eq = getRoot();
		String url = eq.getUrl() + "chatgroups/" + groupId + "/users";
		return HttpsUtils.sendSSLRequest(url, eq.getToken(), null,
				HttpsUtils.Method_GET);
	}

	public static boolean addMembers(String groupId, String userId) {
		// POST
		// /{org_name}/{app_name}/chatgroups/{group_id}/users/{user_primary_key}
		EaseRequest eq = getRoot();
		String url = eq.getUrl() + "chatgroups/" + groupId + "/users/" + userId;
		String result = HttpsUtils.sendSSLRequest(url, eq.getToken(), "",
				HttpsUtils.Method_POST);
		JSONObject json = JSONObject.fromObject(result);
		// System.out.println(json);
		if (json.containsKey("data")) {
			return json.getJSONObject("data").getBoolean("result");
		}
		return false;
	}

	public static String deleteMembers(String groupId, String userId) {
		// POST
		// /{org_name}/{app_name}/chatgroups/{group_id}/users/{user_primary_key}
		EaseRequest eq = getRoot();
		String url = eq.getUrl() + "chatgroups/" + groupId + "users/" + userId;
		return HttpsUtils.sendSSLRequest(url, eq.getToken(), null,
				HttpsUtils.Method_DELETE);
	}

	/**
	 * 
	 * @describe：注册一个用户到环信
	 * @param account
	 * @return
	 * @author:kui.he
	 * @time:2014年9月25日上午10:58:11
	 */
	public static boolean addUser(String account) {
		EaseRequest eq = getRoot();
		String url = eq.getUrl() + "users/";
		// 先判断该用户是否存在
		String result = HttpsUtils.sendSSLRequest(url + account, eq.getToken(),
				null, HttpsUtils.Method_GET);
		if (JSONObject.fromObject(result).containsKey("entities")) {// 如果已经存在,直接返回
			return true;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", account);
		map.put("password", account);
		result = HttpsUtils.sendSSLRequest(url, eq.getToken(),
				HttpsUtils.Map2Json(map), HttpsUtils.Method_POST);
		JSONObject json = JSONObject.fromObject(result);
		if (json.containsKey("entities")) {
			//System.out.println(json.get("entities"));
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @describe：查询某个用户是否存在
	 * @param account
	 * @return
	 * @author:kui.he
	 * @time:2014年9月25日上午10:57:48
	 */
	public static ChatUser getUserByAccount(String account) {
		EaseRequest eq = getRoot();
		String url = eq.getUrl() + "users/" + account;
		String result = HttpsUtils.sendSSLRequest(url, eq.getToken(), null,
				HttpsUtils.Method_GET);
		JSONObject json = JSONObject.fromObject(result);
		if (json.containsKey("entities")) {
			List<ChatUser> users = JSONArray.toList(
					json.getJSONArray("entities"), ChatUser.class);
			if (users != null && !users.isEmpty()) {
				return users.get(0);
			}
		}
		return null;
	}

	private static EaseRequest getRoot() {
		String token = getUserToken();
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);

		EaseRequest request = new EaseRequest();
		String url = "https://a1.easemob.com/" + orgName + "/" + appName + "/";
		request.setToken(token);
		request.setOrgName(orgName);
		request.setAppName(appName);
		request.setUrl(url);
		return request;
	}

	public static void main(String[] args) throws Exception {
		Map map = new HashMap();
		map.put("type", "txt");
		map.put("msg", "content2");
		System.out.println(ImMessageUtil.sendTxtMessage("10000", "50101", "ssss"));
		// List<ChatGroup> list=ImMessageUtil.chatgroups();
		// for (ChatGroup chatGroup : list) {
		// String result=ImMessageUtil.deleteChatgroup(chatGroup.getGroupid());
		// System.out.println(result);
		// }
		// System.out.println(createChatgroups("测试圈子111","111", "119", null));
		// System.out.println(deleteChatgroup(createChatgroups("测试圈子111","111",
		// "119", null)));
		//System.out.println(addMembers("141161597591817", "114"));
		//System.out.println(getGroupMembers("141161597591817"));
		// System.out.println(getUserByAccount("131"));
		// System.out.println(ImMessageUtil.addUser("119"));
		// System.out.println(ImMessageUtil.getUserByAccount("119"));
		// System.out.println(ImMessageUtil.createChatgroups("juju+",
		// "juju+ desc",true, "110",new String[]{"110"}));
	}

}

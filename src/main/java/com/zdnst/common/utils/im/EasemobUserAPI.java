package com.zdnst.common.utils.im;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * REST API Demo : 用户管理REST API HttpClient4.3实现
 * 
 * Doc URL: http://developer.easemob.com/docs/emchat/rest/userapi.html
 * <p>描        述：</p>
 * <p>项目名称:maps1.0</p>
 * <p>所在位置:com.zdnst.maps.service.im.implEasemobUserAPI.java</p>
 * <p>类名:EasemobUserAPI.java</p>
 * <p>创  建  人：kui.he</p>
 * <p>创建时间：2014年8月6日下午10:49:03</p>
 */
public class EasemobUserAPI {

	/**
	 * 创建用户
	 * 
	 * @param host
	 *            IP或者域名
	 * @param port
	 *            端口
	 * @param appKey
	 *            easemob-demo#chatdemo
	 * @param postBody
	 *            封装了用户属性的json对象
	 * @param token
	 *            admin级别token
	 * @return
	 */
	public static String createNewUser(String host, String appKey,
			Map<String, Object> body, String token) {
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);

		String rest = orgName + "/" + appName + "/users";

		String reqURL = "https://" + host + "/" + rest;
		String result = HttpsUtils.sendSSLRequest(reqURL, token,
				HttpsUtils.Map2Json(body), HttpsUtils.Method_POST);
		return result;
	}

	/**
	 * 删除用户
	 * 
	 * @param host
	 *            IP或者域名
	 * @param port
	 *            端口
	 * @param appKey
	 *            easemob-demo#chatdemo
	 * @param id
	 *            usename or uuid
	 * @param token
	 *            admin级别token
	 * @return
	 */
	public static String deleteUser(String host, String appKey, String id,
			String token) {

		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);

		String rest = orgName + "/" + appName + "/users/" + id;

		String reqURL = "https://" + host + "/" + rest;
		String result = HttpsUtils.sendSSLRequest(reqURL, token, null,
				HttpsUtils.Method_DELETE);

		return result;
	}

	/**
	 * 获取token
	 * 
	 * @param host
	 *            IP或者域名
	 * @param port
	 *            端口
	 * @param appKey
	 *            easemob-demo#chatdemo
	 * @param isAdmin
	 *            org管理员token true, IM用户token false
	 * @param postBody
	 *            POST请求体
	 * @return
	 */
	public static String getAccessToken(String host, String appKey,
			Boolean isAdmin, Map<String, Object> postBody) {
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		String accessToken = "";
		String rest = "management/token";
		if (!isAdmin) {
			rest = orgName + "/" + appName + "/token";
		}

		String reqURL = "https://" + host + "/" + rest;
		String result = HttpsUtils.sendSSLRequest(reqURL, null,
				HttpsUtils.Map2Json(postBody), HttpsUtils.Method_POST);
		Map<String, String> resultMap = HttpsUtils.Json2Map(result);

		accessToken = resultMap.get("access_token");

		return accessToken;
	}

	public static void main(String[] args) {
		// String host = "a1.easemob.com";
		// String appKey = "zdnst#juju";

		String host = "a1.easemob.com";
		String appKey = "easemob-playground#test1";
		// 获取IM用户token
		Map<String, Object> getIMAccessTokenPostBody = new HashMap<String, Object>();
		getIMAccessTokenPostBody.put("grant_type", "client_credentials");
		getIMAccessTokenPostBody.put("client_id", "YXA64RsAEBbxEeSoKmGtGo1Qdw");
		getIMAccessTokenPostBody.put("client_secret",
				"YXA6YXutPE-jRJrMXln0tA1ApT5kEQA");
		String imToken = EasemobUserAPI.getAccessToken(host, appKey, false,
				getIMAccessTokenPostBody);
		//System.out.println(imToken);

		// 获取管理员token
		Map<String, Object> getAccessTokenPostBody = new HashMap<String, Object>();
		
		getAccessTokenPostBody.put("grant_type", "password");
		getAccessTokenPostBody.put("username", "zhangjianguo");
		getAccessTokenPostBody.put("password", "zhangjianguo");
		
		// String adminToken = EasemobRESTSSLAPI.getAccessToken(host, appKey,
		// true,
		// getAccessTokenPostBody);
		// System.out.println(adminToken);

		// 创建用户
		Map<String, Object> createNewUserPostBody = new HashMap<String, Object>();
		createNewUserPostBody.put("username", "testuser2");
		createNewUserPostBody.put("password", "testuser2");
		createNewUserPostBody.put("addr", "BJFS");
		String adminToken = "YWMtVWWpUAhTEeSQZieN8wRN4QAAAUdFQji_f3OeR3Me_gfQIW5zWOa6smv6Wyg";
		EasemobUserAPI.createNewUser(host, appKey, createNewUserPostBody,
				adminToken);

		// 删除用户
		String id = "testuser2";
		EasemobUserAPI.deleteUser(host, appKey, id, adminToken);
	}

	/**
	 * 
	 * @describe：获取指定用户的消息信息
	 * @param host IP或者域名
	 * @param appKey  easemob-demo#chatdemo
	 * @param id 
	 * @param token   admin级别token
	 * @param condition 查询条件
	 * @return
	 * @author:kui.he	
	 * @time:2014年8月6日下午4:06:03
	 * 
	 */
	public synchronized static String getMessage(String host, String appKey, String token,String condition) {

		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
        
		StringBuffer bf=new StringBuffer();
		bf.append("https://");
		bf.append(host);
		bf.append("/");
		
		bf.append(orgName + "/" + appName + "/chatmessages/");
		if(!condition.isEmpty()){
	        bf.append("?");
	        bf.append(condition);
	    }
		//System.out.println("请求地址:"+bf.toString());
		String result = HttpsUtils.sendSSLRequest(bf.toString(), token, null,
				HttpsUtils.Method_GET);
		return result;
	}
	
	/**
	 * 
	 * @describe：TODO
	 * @param host
	 * @param appKey
	 * @param token
	 * @param from
	 * @param toUsers
	 * @param message
	 * @return
	 * @author:kui.he
	 * @time:2014年8月7日下午2:15:06
	 */
	public synchronized static String sendMessage(String host,String appKey,String token,String from,ArrayList<String> toUsers,Map<String,String> message){
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
       
		String url="https://a1.easemob.com/"+orgName+"/"+appName+"/messages";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("target_type", "users");
		param.put("target",toUsers);
		param.put("msg", message);
		param.put("from", from);
		
		String result = HttpsUtils.sendSSLRequest(url, token, HttpsUtils.Map2Json(param),
				HttpsUtils.Method_POST);
		return result;
	}

}

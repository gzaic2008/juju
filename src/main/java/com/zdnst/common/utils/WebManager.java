package com.zdnst.common.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.zdnst.common.base.BaseCode;
import com.zdnst.common.base.WebUser;

public class WebManager {
	private static Logger logger = Logger.getLogger(WebManager.class);

	public static String getSessionId(HttpServletRequest request) {

		String sessionId = request.getSession(true).getId();
		return sessionId;
	}

	public static void sessionInvalid(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			session.invalidate();
		}
	}

	public static WebUser getWebUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session != null) {
			return (WebUser) session.getAttribute("webuser");
		}
		return null;

	}

	public static String getImagePath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath()
				+ "/image/";
	}

	/**
	 * 根据请求数据判断必传数据是否已经上传整齐
	 * 
	 * @param request
	 * @param result
	 * @param para
	 * @return
	 */
	public static boolean validRequestRequire(HttpServletRequest request,
			Map result, String[] para) {
		boolean flag = true;
		for (int i = 0; i < para.length; i++) {
			if (CommonUtils.getParameter(request, para[i]) == null) {
				flag = false;
				break;
			}
		}
		if (!flag) {
			result.put("code", BaseCode.ERROR_CODE200);
			result.put("error_message",
					BaseCode.resultMsg.get(BaseCode.ERROR_CODE200));
		}
		return flag;
	}

	/**
	 * 根据请求数据判断必传数据是否已经上传整齐（只要有key即算整齐)
	 * 
	 * @param request
	 * @param para
	 * @return
	 */
	public static boolean validRequestRequire(HttpServletRequest request,
			String[] para) {
		boolean flag = true;
		for (int i = 0; i < para.length; i++) {
			if (!CommonUtils.existRequestKey(request, para[i])) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	/***
	 * 获取请求的头信息
	 * 
	 * @describe：TODO
	 * @param request
	 *            请求对象
	 * @return
	 * @author:kui.he
	 * @time:2014年9月4日下午4:37:53
	 */
	public static Map<String, String> getHeadersInfo(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = (String) headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}
		map.put("server-path", getServerPath(request));
		map.put("http-method", request.getMethod());

		map.put("http-method", request.getMethod());
		map.put("request-uri", request.getRequestURI());
		map.put("request-url", request.getRequestURL().toString());
		map.put("scheme", request.getScheme());
		map.put("protocol", request.getProtocol());// 获取客户端请求协议
		map.put("user-agent", request.getHeader("User-Agent"));

		Set<String> keys = map.keySet();
		for (String key : keys) {
			logger.info(key + ":" + map.get(key));
		}
		return map;
	}

	/**
	 * 判断请求客户端是否是浏览器
	 * 
	 * @describe：TODO
	 * @param request
	 * @return
	 * @author:kui.he
	 * @time:2014年9月5日上午9:48:53
	 */
	public static boolean isBrowser(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		if (CommonUtils.isEmpty(userAgent)) {
			return false;
		}
		userAgent = userAgent.toLowerCase();
		// 浏览器请求的一些常规参数
		String[] keys = new String[] { "browser", "uc", "mozilla", "safari",
				"applewebkit", "chrome" };//
		if (CommonUtils.isContains(userAgent, keys)) {
			return true;
		}
		return false;

	}
	
	
	/**
	 * 判断请求客户端是什么类型:1是浏览器、2是安卓、4是ios
	 * @describe：TODO
	 * @param request
	 * @return
	 * @author:yongqin.zhong
	 * @time:Nov 27, 20145:55:45 PM
	 */
	public static int  getAzIosOrBrowser(HttpServletRequest request) {
		int type=4;
		String userAgent = request.getHeader("user-agent");
		if (CommonUtils.isNotEmpty(userAgent)) {
			userAgent = userAgent.toLowerCase();
			// 浏览器请求的一些常规参数
			String[] browserKeys = new String[] { "browser", "uc", "mozilla", "safari",
					"applewebkit", "chrome" };//
			String[] androidKeys = new String[] { "android"};//
			if (CommonUtils.isContains(userAgent, browserKeys)) {
				type=1;
			}else if(CommonUtils.isContains(userAgent, androidKeys)){
				type=2;
			}
		}
		
		return type;

	}
	

	public static String getServerPath(HttpServletRequest request) {
		StringBuffer url=new StringBuffer();
		url.append(request.getScheme());
		if(CommonUtils.isNotEmpty(request.getServerName())){
			url.append("://" + request.getServerName());
		}
		if(request.getServerPort()!=0&&request.getServerPort()!=80){
			url.append(":"+ request.getServerPort());
		}
		if(CommonUtils.isNotEmpty(request.getContextPath())){
			url.append(request.getContextPath());
		}
		return url.toString();
	}

}

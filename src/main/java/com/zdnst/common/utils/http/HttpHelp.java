package com.zdnst.common.utils.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.zdnst.common.utils.http.utils.HttpRequester;
import com.zdnst.common.utils.http.utils.HttpRespons;

public class HttpHelp {

	/**
	 * 发送消息
	 * 
	 * @param webserviceUrl
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public String sendMessage(String webserviceUrl,
			String params) throws IOException {
		HttpClient httpClient = new HttpClient();
		//System.out.println("Request webservice address:" + webserviceUrl);
		PostMethod postMethod = new PostMethod(webserviceUrl);
		Header header = new Header("Content-type",
				"application/json; charset=utf-8");
		postMethod.setRequestHeader(header);
		String resposeContent = "";
		postMethod.setRequestEntity(new StringRequestEntity(params));
		try {
			httpClient.executeMethod(postMethod);
			// postMethod.getParams().setContentCharset("utf-8");
			resposeContent = postMethod.getResponseBodyAsString();
			//System.out.println("webservice response:" + resposeContent);
		} catch (HttpException e1) {
			System.err.println("webservice request have exception!"
					+ e1.getMessage());
			throw e1;
		} catch (IOException e1) {
			System.err.println("webservice request have exception!"
					+ e1.getMessage());
			e1.printStackTrace();
			throw e1;
		}
		return resposeContent;
	}

	public synchronized static String sendGet(String webserviceUrl,
			String params) throws IOException {
		HttpClient httpClient = new HttpClient();
		//System.out.println("Request webservice address:" + webserviceUrl);
		GetMethod getMethod = new GetMethod(webserviceUrl);

		PostMethod postMethod = new PostMethod(webserviceUrl);
		Header header = new Header("Content-type",
				"application/json; charset=utf-8");
		postMethod.setRequestHeader(header);
		String resposeContent = "";
		postMethod.setRequestEntity(new StringRequestEntity(params));
		try {
			httpClient.executeMethod(postMethod);
			postMethod.getParams().setContentCharset("utf-8");
			resposeContent = postMethod.getResponseBodyAsString();
			//System.out.println("webservice response:" + resposeContent);
		} catch (HttpException e1) {
			System.err.println("webservice request have exception!"
					+ e1.getMessage());
			throw e1;
		} catch (IOException e1) {
			System.err.println("webservice request have exception!"
					+ e1.getMessage());
			e1.printStackTrace();
			throw e1;
		}
		return resposeContent;
	}

	/* 发送HTTP请求 */
	public synchronized static String sendPostJson(String serverUrl,
			String jsonContent)  throws IOException{
		Map<String, String> header = new HashMap<String, String>();
		header.put("Content-type", "application/json; charset=utf-8");
		// 安全接口需要的参数
		HttpRequester wsrequest = new HttpRequester();
		try {
			HttpRespons hr;
			hr = wsrequest.sendPost(serverUrl, jsonContent,header);
			//System.out.println("invoking webService success!");
			return hr.getContent();
		} catch (IOException e) {
			//System.err.println("invoking webService failure!" + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	/* 发送HTTP请求 */
	public synchronized static String sendMessage(String serverUrl,
			Map<String, String> paramMap) {
		//System.out.println("outerNet safe Interface url:" + serverUrl);
		// 安全接口需要的参数
		HttpRequester wsrequest = new HttpRequester();
		try {
			HttpRespons hr;
			hr = wsrequest.sendPost(serverUrl, paramMap);
			//System.out.println("invoking webService success!");
			return hr.getContent();
		} catch (IOException e) {
			//System.err.println("invoking webService failure!" + e.getMessage());
			e.printStackTrace();
		}
		return "";
	}

}

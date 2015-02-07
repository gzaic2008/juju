package com.zdnst.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;



/***
 * 
 *  httpclient utility 
 * 
 * @author zhihong.peng
 *
 */

public class ZDHttpClient {

	/**
	 * 
	 * @param link
	 *            连接Url
	 * @param method
	 *            调用方法： post , get
	 * @param xmlData
	 *            xml数据
	 * @param returnFormat
	 *            返回格式: xml, json
	 * @param isAsync
	 *            是否使用异步
	 * @return
	 * @throws Exception
	 */
	public static Object executeRequest(String link, String method,
			String xmlData, String returnFormat, boolean isAsync)
			throws Exception {

		if (isAsync) {
			return null;
		} else {
			return doHttpRequestSynch(link, method, xmlData, returnFormat);
		}

	}

	
	//异步调用
	private static Object doHttpRequestAsynch(String link, String method,
			String xmlData, String returnFormat) throws Exception {

		// 创建默认的httpClient实例.
		AsyncHttpClient client = new AsyncHttpClient();
		HttpResponse httpResponse = null;
		String content = "";

		try {

			List<NameValuePair> formparams = new ArrayList<NameValuePair>();

			formparams.add(new BasicNameValuePair("url", link));
			formparams.add(new BasicNameValuePair("method", method));
			formparams.add(new BasicNameValuePair("xml", xmlData));
			formparams
					.add(new BasicNameValuePair("returnFormat", returnFormat));

			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(
					formparams, "UTF-8");
			Scanner scanner = new Scanner(uefEntity.getContent());
			StringBuilder getMethodParams = new StringBuilder();

			while (scanner.hasNext())
				getMethodParams.append(scanner.next().trim());
			scanner.close();
			if (method != null && method.equalsIgnoreCase("get")) {
				HttpGet httpGet = new HttpGet(link + "?"
						+ getMethodParams.toString());// 创建httpget.
				 
				
				
				Future<Response> f = client.prepareGet("http://www.google.com.hk/").execute();
				
				
				//httpResponse = client.execute(httpGet);// 执行get请求.

			} else if (method != null && method.equalsIgnoreCase("post")) {
				HttpPost httpPost = new HttpPost(link);
				httpPost.setEntity(uefEntity);
				//httpResponse = client.execute(httpPost);
			} else {
				throw new Exception("不支持" + method + ",只支持get和post方法");

			}
			HttpEntity entity = httpResponse.getEntity();// 获取响应实体
			if (entity != null) {
				content = EntityUtils.toString(entity);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			//httpClient.getConnectionManager().shutdown();// 关闭连接,释放资源
		}

		return content;

	}

	
	//
	private static Object doHttpRequestSynch(String link, String method,
			String xmlData, String returnFormat) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();// 创建默认的httpClient实例.
		HttpResponse httpResponse = null;
		String content = "";

		try {

			List<NameValuePair> formparams = new ArrayList<NameValuePair>();

			formparams.add(new BasicNameValuePair("url", link));
			formparams.add(new BasicNameValuePair("method", method));
			formparams.add(new BasicNameValuePair("xml", xmlData));
			formparams
					.add(new BasicNameValuePair("returnFormat", returnFormat));

			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(
					formparams, "UTF-8");
			Scanner scanner = new Scanner(uefEntity.getContent());
			StringBuilder getMethodParams = new StringBuilder();

			while (scanner.hasNext())
				getMethodParams.append(scanner.next().trim());
			scanner.close();
			if (method != null && method.equalsIgnoreCase("get")) {
				HttpGet httpGet = new HttpGet(link + "?"
						+ getMethodParams.toString());// 创建httpget.
				httpResponse = httpClient.execute(httpGet);// 执行get请求.

			} else if (method != null && method.equalsIgnoreCase("post")) {
				HttpPost httpPost = new HttpPost(link);
				httpPost.setEntity(uefEntity);
				httpResponse = httpClient.execute(httpPost);
			} else {
				throw new Exception("不支持" + method + ",只支持get和post方法");

			}
			HttpEntity entity = httpResponse.getEntity();// 获取响应实体
			if (entity != null) {
				content = EntityUtils.toString(entity);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();// 关闭连接,释放资源
		}

		return content;

	}

}

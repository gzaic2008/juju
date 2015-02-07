/**
 * Copyright(C) 2012-2014 GuangZhou zdnst Co., Ltd. All Rights Reserved.<br/>
 * 版权所有(C)2012-2014 <br/>
 * 公司名称：广州支点科技有限公司<br/>
 * 公司地址：广州市天河区天源路401号之三E2栋<br/>
 * 网址：http://www.100100system.com/<br/>
 * <p>标        题：</p>
 * <p>文  件  名：com.zdnst.common.utils.EaseIMUtil.java</p>
 * <p>部        门：研发一部
 * <p>版        本： 1.0</p>
 * <p>Compiler: JDK1.6.0_21</p>
 * <p>创  建  者：yongqin.zhong</p>
 * <p>创建时间：Sep 23, 20147:53:40 PM</p>
 * <p>修  改  者：</p>
 * <p>修改时间：</p>
 */
package com.zdnst.common.utils.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/**
 * 环信IM聊天
 * <p>描        述：</p>
 * <p>项目名称:maps1.1</p>
 * <p>所在位置:com.zdnst.common.utilsEaseIMUtil.java</p>
 * <p>类名:EaseIMUtil.java</p>
 * <p>创  建  人：yongqin.zhong</p>
 * <p>创建时间：Sep 23, 20147:53:45 PM</p>
 */
public class EaseIMUtil {

	
	
	/**
	 * 创建群组
	 * @describe：TODO
	 * @return
	 * @author:yongqin.zhong
	 * @time:Sep 23, 20147:56:02 PM
	 */
	public static String createImGroupId() {
		String imGroupId="";
		CloseableHttpClient httpClient = EaseIMUtil.createSSLClientDefault();

		HttpGet get = new HttpGet("https://a1.easemob.com/easemob-demo/chatappAdmin/chatappAdmin/roughest/status");

		//get.setURI(new URI("你的https://地址"));

		try {
			CloseableHttpResponse  response=httpClient.execute(get);
		        HttpEntity entity = response.getEntity();
		        String html = EntityUtils.toString(entity);   
		        //System.out.println(html);
		        httpClient.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-zdnst catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-zdnst catch block
			e.printStackTrace();
		}
		return imGroupId;
	}
	
	public static CloseableHttpClient createSSLClientDefault(){

		try {

		             SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

		                 //信任所有

		                 public boolean isTrusted(X509Certificate[] chain,

		                                 String authType) throws CertificateException {

		                     return true;

		                 }

		             }).build();

		             SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

		             return HttpClients.custom().setSSLSocketFactory(sslsf).build();

		         } catch (KeyManagementException e) {

		             e.printStackTrace();

		         } catch (NoSuchAlgorithmException e) {

		             e.printStackTrace();

		         } catch (KeyStoreException e) {

		             e.printStackTrace();

		         }

		         return  HttpClients.createDefault();

		}
	
	
	
	public static void main(String[] args) {
		try {
			EaseIMUtil.createImGroupId();
		} catch (Exception e) {
			// TODO Auto-zdnst catch block
			e.printStackTrace();
		}

	}

}

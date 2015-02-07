/**
 * Copyright(C) 2012-2014 GuangZhou zdnst Co., Ltd. All Rights Reserved.<br/>
 * 版权所有(C)2012-2014 <br/>
 * 公司名称：广州支点科技有限公司<br/>
 * 公司地址：广州市天河区天源路401号之三E2栋<br/>
 * 网址：http://www.100100system.com/<br/>
 * <p>标        题：</p>
 * <p>文  件  名：com.zdnst.common.utils.CaptureHtml.java</p>
 * <p>部        门：研发一部
 * <p>版        本： 1.0</p>
 * <p>Compiler: JDK1.6.0_21</p>
 * <p>创  建  者：yongqin.zhong</p>
 * <p>创建时间：Aug 6, 20149:33:46 AM</p>
 * <p>修  改  者：</p>
 * <p>修改时间：</p>
 */
package com.zdnst.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;



/**
 * 网页信息抓取范例
 * <p>描        述：</p>
 * <p>项目名称:maps1.0</p>
 * <p>所在位置:com.zdnst.common.utilsCaptureHtml.java</p>
 * <p>类名:CaptureHtml.java</p>
 * <p>创  建  人：yongqin.zhong</p>
 * <p>创建时间：Aug 6, 20149:58:16 AM</p>
 */
public class CaptureHtml {
	
	private static Logger logger = Logger.getLogger(CaptureHtml.class);

    public void captureHtml(String ip)  {  
        try {
			String strURL = "http://ip.chinaz.com/?IP=" + ip;  
			URL url = new URL(strURL);  
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();  
			InputStreamReader input = new InputStreamReader(httpConn  
			        .getInputStream(), "utf-8");  
			BufferedReader bufReader = new BufferedReader(input);  
			String line = "";  
			StringBuilder contentBuf = new StringBuilder();  
			while ((line = bufReader.readLine()) != null) {  
			    contentBuf.append(line);  
			}  
			String buf = contentBuf.toString();  
			int beginIx = buf.indexOf("查询结果[");  
			int endIx = buf.indexOf("上面三项依次显示的是");  
			String result = buf.substring(beginIx, endIx);  
			logger.info("captureHtml()的结果：\n" + result);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
    }  
	
	
	public static void main(String[] args) {
		CaptureHtml cap=new CaptureHtml();
		cap.captureHtml("111.142.55.73");

	}

}

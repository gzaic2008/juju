
/**
 * Copyright(C) 2012-2014 GuangZhou zdnst Co., Ltd. All Rights Reserved.<br/>
 * 版权所有(C)2012-2014 <br/>
 * 公司名称：广州支点科技有限公司<br/>
 * 公司地址：广州市天河区天源路401号之三E2栋<br/>
 * 网址：http://www.100100system.com/<br/>
 * <p>标        题：</p>
 * <p>文  件  名：com.zdnst.maps.utils.SendMsgThread.java</p>
 * <p>部        门：研发一部
 * <p>版        本： 1.0</p>
 * <p>Compiler: JDK1.6.0_21</p>
 * <p>创  建  者：yongqin.zhong</p>
 * <p>创建时间：Jun 11, 201411:42:19 AM</p>
 * <p>修  改  者：</p>
 * <p>修改时间：</p>
 */
package com.zdnst.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class SendMsgThread extends Thread {
	
	private static final Log log = LogFactory.getLog(SendMsgThread.class);
	
	private String mobile;
	private String content;
	
	public SendMsgThread(){
		
	}
	
	public SendMsgThread(String mobi,String cont){
		mobile=mobi;
		content=cont;
	}
	
	public void run() { 
		try { 
			String result = SendMsgUtil.SendMsg(mobile, content);
			log.info("Send Message request:mobile="+mobile+"&content="+content);
			log.info("Send Message result:"+result);
		} catch (Exception e) { 
			e.getStackTrace();
		} 
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}

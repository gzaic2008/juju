
package com.zdnst.common.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.zdnst.common.base.SysConstant;




	


/**
 * 短信发送工具类
 * @author yongqin.zhong
 *
 */
public class SendMsgUtil {

	private static final Log log = LogFactory.getLog(SendMsgUtil.class);
	
	/**
	 * 通过线程方式发送短息，
	 * @describe：TODO
	 * @param mobile
	 * @param content
	 * @param isVerify,true表示发送验证码需要进行验证，false表示不需要验证码验证或者发送内容无验证码
	 * @author:yongqin.zhong
	 * @time:Jun 12, 20144:38:34 PM
	 */
	public  static void sendThreadMsg(String mobile,String content,boolean isVerify){
		StringBuffer msg=new StringBuffer(content);
		if(isVerify){
			String verfyCode = CommonUtils.generateRamdomNum();
			msg.append(verfyCode);
			ZDNSTUtil.SENDMOBILE_MSG_MAP.put(mobile, verfyCode);
			ZDNSTUtil.SENDMOBILE_TIME_MAP.put(mobile,
					CommonUtils.getCurTimestamp());
		}
		SendMsgThread SendMsgThread=new SendMsgThread(mobile, msg.toString());
		SendMsgThread.start();
	}
	
	
	public  static String SendMsg(String mobile,String content){
		String result= "";
		try {
			String msgUserName=SysConstant.SMS_USER_NORMAL;
			String msgUserPwd=SysConstant.SMS_PASSWORD_NORMAL;
			if(content!=null&&content.indexOf("http:")!=-1){
				msgUserName=SysConstant.SMS_USER_SPECIAL;
				msgUserPwd=SysConstant.SMS_PASSWORD_SPECIAL;
			}
			String url=SystemProperties.getProperties().getProperty(SysConstant.SMS_URL);
			String msgSn=SystemProperties.getProperties().getProperty(msgUserName);//序列号
			String msgPwd=SystemProperties.getProperties().getProperty(msgUserPwd);//密码
			if(CommonUtils.isNotEmpty(content)){
				// 创建HttpClient实例     
			    HttpClient httpclient = new DefaultHttpClient();  
			    StringBuffer urlBf=new StringBuffer(); 
			    urlBf.append(url);
			    urlBf.append("id=");
			    //urlBf.append(CommonUtils.getFromBASE64(msgSn));
			    urlBf.append(msgSn);
			    urlBf.append("&pwd=");
			    urlBf.append(msgPwd);
			    //urlBf.append(CommonUtils.getFromBASE64(msgPwd));
			    urlBf.append("&to=");
			    urlBf.append(mobile);
			    urlBf.append("&content=");
			    urlBf.append(URLEncoder.encode(content, "GB2312"));
			    urlBf.append("&time=");
			    // 创建Get方法实例     
			    HttpGet httpgets = new HttpGet(urlBf.toString());    
			    HttpResponse response = httpclient.execute(httpgets);    
			    HttpEntity entity = response.getEntity();    
			    if (entity != null) {    
			        InputStream instreams = entity.getContent();    
			        result = convertStreamToString(instreams);  
			        log.info("Send msgsn="+msgSn+"&content="+content+"&mobile:"+mobile+" msg:"+result);
			        // Do not need the rest    
			        httpgets.abort();    
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String convertStreamToString(InputStream is) {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }  
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		//SendMsg("15813306682","fdfdd");
		
		String msgSn=SystemProperties.getProperties().getProperty("msgSn");//序列号
		String msgPwd=SystemProperties.getProperties().getProperty("msgPwd");//密码
		System.out.println(CommonUtils.getBASE64("zdjjbar"));    
		System.out.println(CommonUtils.getBASE64("x415312"));
		System.out.println(CommonUtils.getBASE64("zdjjapp"));
		System.out.println(SendMsgUtil.SendMsg("15813306682","dddd"));
		
		try {
			//System.out.println(CommonUtils.getFromBASE64(msgSn)+":"+CommonUtils.getFromBASE64(msgPwd));
		} catch (Exception e) {
			// TODO Auto-zdnst catch block
			e.printStackTrace();
		}
	}
	
	

}
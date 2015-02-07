package com.zdnst.common.utils;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.ModelAndView;

import com.zdnst.common.base.BaseCode;
import com.zdnst.common.base.ResponseParam;


/**
 * 将Object转为json输出
 * 修改人：kylin.woo
 * 修改时间：2014-05-14
 * @author pzh
 * @version maps1.0
 */
public class JsonView {
	
	public static final String CODE = "code";
    public static final String DATA = "data";
    public static final String ERROR_MESSAGE = "message";
    private static final Log log = LogFactory.getLog(JsonView.class);
	
	/**
	 * 将Object转为json输出
	 * @param model Object  
	 * @param response HttpServletResponse
	 * @return ModelAndView 返回类型为spring的ModelAndView or {@code null}
	 */
	public static ModelAndView render(Object obj, HttpServletResponse response){  
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();  
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;  
        try {  
            jsonConverter.write(obj, jsonMimeType, new ServletServerHttpResponse(response));  
        } catch (Exception e) {
        	log.error("Object to Json error");
            e.printStackTrace();
        } 
        return null;  
    } 
	
	
	/**
	 * 将data转为json输出
	 * @param model Object  
	 * @param response HttpServletResponse
	 * @return ModelAndView 返回类型为spring的ModelAndView or {@code null}
	 */
	public static ModelAndView dataToJson(Object data, HttpServletResponse response){  
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();  
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;  
        try {  
        	ResponseParam resParam=new ResponseParam();
        	resParam.setCode(BaseCode.SUCCESS_CODE);
        	resParam.setData(data);
        	resParam.setMessage("");
        	return dataToJson(resParam, response);
        } catch (Exception e) {
        	log.error("Object to Json error");
            e.printStackTrace();
        } 
        return null;  
    } 
	
	public static ModelAndView dataToJson(String hash,Object data, HttpServletResponse response){ 
		ResponseParam result=new ResponseParam();
		result.setHash(hash);
		result.setData(data);
		return dataToJson(result, response);
	}
	
	public static ModelAndView dataToJson(Object data,long timestamp, HttpServletResponse response){ 
		ResponseParam result=new ResponseParam();
		result.setData(data);
		result.setTimestamp(timestamp);
		return dataToJson(result, response);
	}
	
	public static ModelAndView dataToJson(String hash,Object data,String code,String message, HttpServletResponse response){ 
		ResponseParam result=new ResponseParam();
		result.setHash(hash);
		result.setData(data);
		result.setCode(code);
		result.setMessage(message);
		return dataToJson(result, response);
	}
	

	/**
	 * 将error转为json输出
	 * @param model Object  
	 * @param response HttpServletResponse
	 * @return ModelAndView 返回类型为spring的ModelAndView or {@code null}
	 */
	public static ModelAndView addErrorToJson(String errorCode,String errorMsg, HttpServletResponse response){  
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();  
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;  
        try {  
            ResponseParam resParam=new ResponseParam();
        	resParam.setCode(errorCode);
        	resParam.setData(null);
        	resParam.setMessage(errorMsg);
        	//response.setStatus(600);
        	dataToJson(resParam, response);
        	
//        	log.info(resParam);
//          jsonConverter.write(resParam, jsonMimeType, new ServletServerHttpResponse(response));
            
        } catch (Exception e) {
        	log.error("Object to Json error");
            e.printStackTrace();
        } 
        return null;  
    }
	
	/**
	 * 将error转为json输出
	 * @param model Object  
	 * @param response HttpServletResponse
	 * @return ModelAndView 返回类型为spring的ModelAndView or {@code null}
	 */
	public static ModelAndView addErrorToJson(String errorCode, HttpServletResponse response){  
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();  
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;  
        try {  
        	ResponseParam resParam=new ResponseParam();
        	resParam.setCode(errorCode);
        	resParam.setData(null);
        	if(BaseCode.resultMsg.get(errorCode)!=null){
        		resParam.setMessage(BaseCode.resultMsg.get(errorCode).toString());
        		log.info(errorCode+"="+BaseCode.resultMsg.get(errorCode).toString());
        	}else{
        		resParam.setMessage(errorCode);
        		log.info(errorCode+"="+errorCode);
        	}
//        	response.setStatus(600);
        	
//            jsonConverter.write(resParam, jsonMimeType, new ServletServerHttpResponse(response));  
        	
        	return dataToJson(resParam, response);
        } catch (Exception e) {
        	log.error("Object to Json error");
            e.printStackTrace();
        } 
        return null;  
    }
	
	/**
	 * 将 ResponseParam 转为json输出(如果没给code默认为1[成功],如果没有给消息内容默认为空)
	 * @param resParam ResponseParam  
	 * @param response HttpServletResponse
	 * @return ModelAndView 返回类型为spring的ModelAndView or {@code null}
	 * @author kui.he
	 * 
	 */
	public static ModelAndView dataToJson(ResponseParam resParam, HttpServletResponse response){ 
		MappingJacksonHttpMessageConverter jsonConverter = new MappingJacksonHttpMessageConverter();  
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;  
        try {  
        	if (CommonUtils.isEmpty(resParam.getCode())) {
        		resParam.setCode(BaseCode.SUCCESS_CODE);
			}
        	
        	if (CommonUtils.isEmpty(resParam.getMessage())) {
        		resParam.setMessage("");
			}
        	log.info(resParam);
        	
        	setResponseHeader(resParam, response);
        	
            jsonConverter.write(resParam, jsonMimeType, new ServletServerHttpResponse(response));  
            
        } catch (Exception e) {
        	log.error("Object to Json error");
            e.printStackTrace();
        } 
        return null; 
	}
	
	/**
	 * 设置response的头
	 * @describe：TODO
	 * @param resParam
	 * @param response
	 * @author:kui.he
	 * @time:2014年9月5日下午4:17:02
	 */
	private static void setResponseHeader(ResponseParam resParam, HttpServletResponse response){
		response.setHeader("hash", resParam.getHash());
    	response.setHeader("message", resParam.getMessage());
    	response.setHeader("code", resParam.getCode());
    	response.setHeader("timestamp", resParam.getTimestamp()+"");
	}
}

package com.zdnst.common.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.zdnst.common.mybatis.page.Page;
import com.zdnst.common.utils.CommonUtils;
import com.zdnst.common.utils.JsonView;
 

public class BaseController {
	/***
	 * 系统默认跳转页面
	 */
	protected final static String JUMP_PAGE="pub/pageJump";
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleException(Exception ex, HttpServletRequest request) {
		// return new ModelAndView().addObject("error", "错误信息");
		return new ModelAndView().addObject("code", BaseCode.ERROR_CODE110);
	}
	
	/**
	 * @describe：成功时的返回
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 * @author:kui.he
	 * @time:2014年10月6日上午11:57:59
	 */
	public ModelAndView success(Object data,HttpServletRequest request,HttpServletResponse response){
		return JsonView.dataToJson(request.getSession().getId(),data, response);
	}
	
	/**
	 * @describe：返回失败
	 * @param data
	 * @param request
	 * @param response
	 * @return
	 * @author:kui.he
	 * @time:2014年10月6日上午11:57:59
	 */
	public ModelAndView error(String errorCode,HttpServletResponse response){
		return JsonView.addErrorToJson(errorCode, response);
	}
	
	public ModelAndView error(String errorCode,String errMsg,HttpServletResponse response){
		return JsonView.addErrorToJson(errorCode,errMsg, response);
	}

	/**
	 * @describe：从基础的请求头里面获取到参数并封装为map返回
	 * @param param
	 * @return
	 * @author:kui.he
	 * @time:2014年9月15日上午11:42:44
	 */
	protected Map getParam(RequestParam param) {
		Map<String, Object> maparam = new HashMap<String, Object>();
		maparam.put("userId", CommonUtils.getString(param.getUserId(), ""));
		maparam.put("condition",CommonUtils.getString(param.getCondition(), ""));
		maparam.put("id",CommonUtils.getString(param.getId(), ""));
		maparam.put("name",CommonUtils.getString(param.getName(), ""));
		maparam.put("type",CommonUtils.getString(param.getType(), ""));
		
//		maparam.put("pageSize", CommonUtils.getString(param.getPageSize(), "5"));
//		maparam.put("currentPage",CommonUtils.getString(param.getCurrentPage(), "1"));
		
		Page page = new Page();
		String currentPage = param.getCurrentPage();
		String pageSize = param.getPageSize();
		if (CommonUtils.isNotEmpty(currentPage)
				&& CommonUtils.isNotEmpty(pageSize)) {
			page.setCurrentPage(CommonUtils.toInt(currentPage));
			page.setPageSize(CommonUtils.toInt(pageSize));
		}
		maparam.put("page", page);
		return maparam;
	}
	
	/**
	 * @describe：封装对分页数据返回结果 含有key[nextPage 是否还有下一页(0 没有、1有),list 对应的数据集合]
	 * @param datalist
	 * @param pageSize
	 * @return
	 * @author:kui.he
	 * @time:2014年9月15日下午4:17:31
	 */
	protected Map getResult(List datalist,String pageSize,String currentPage) {
		Map result=new HashMap<String,Object>();
		 //判断是否有下一页(如果获取的数据大于等于每页显示的行数就认为还有下一页)
		 if (datalist.size()>=CommonUtils.getInt(pageSize)) {
			 result.put("endStatus","1");//有下一页
		 }else{
			 result.put("endStatus","0");//没有下一页
		 }
		 result.put("currentPage", CommonUtils.getString(currentPage, "1"));
		 result.put("list", datalist);
		 return result;
	}

	/**
	 * 
	 * @describe：根据用户请求得到请求的用户Id
	 * @param param
	 * @param request
	 * @return
	 * @author:kui.he
	 * @time:2014年9月19日上午11:51:32
	 */
	protected String getUserId(RequestParam param,HttpServletRequest request) {
		String userId="";
		if (param!=null) {
			userId=param.getUserId();
		}
		if (CommonUtils.isEmpty(userId)) {
			userId=CommonUtils.getString(request.getSession().getAttribute("userId"),"");
		}else{
			request.getSession().setAttribute("userId", userId);
		}return userId;
	}
	/**
	 * @describe：将用户对象部分信息写入到会话中(登录时使用)
	 * @param user
	 * @param request
	 * @author:kui.he
	 * @time:2014年9月19日下午12:02:30
	 */
	protected void setUserSession( HttpServletRequest request) {
		HttpSession session=request.getSession();
		 
	}
	/**
	 * 
	 * @describe：获取远程请求客户端的请求头信息
	 * @param request
	 * @return
	 * @author:kui.he
	 * @time:2014年9月20日下午12:24:58
	 */
	protected String getHeaderUserAgent(HttpServletRequest request) {
		return CommonUtils.getString(request.getHeader("User-Agent"), "");
	}
	/**
	 * 
	 * @describe：获取远程请求客户端的IP地址
	 * @param request
	 * @return
	 * @author:kui.he
	 * @time:2014年9月20日下午12:24:29
	 */
	protected String getRequesttRemoteAddr(HttpServletRequest request){
		return CommonUtils.getIpAddr(request);
	}
	/**
	 * 得到请求者(客户端)的所在城市
	 * @describe：TODO
	 * @param request
	 * @return
	 * @author:kui.he
	 * @time:2014年10月23日上午11:56:31
	 */
	protected String getRequestCity(HttpServletRequest request) {
		//首先得到请求者的IP地址
		
		return "";
	}
}

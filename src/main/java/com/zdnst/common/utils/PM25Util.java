/**
 * Copyright(C) 2012-2014 GuangZhou zdnst Co., Ltd. All Rights Reserved.<br/>
 * 版权所有(C)2012-2014 <br/>
 * 公司名称：广州支点科技有限公司<br/>
 * 公司地址：广州市天河区天源路401号之三E2栋<br/>
 * 网址：http://www.100100system.com/<br/>
 * <p>标        题：</p>
 * <p>文  件  名：com.zdnst.common.utils.PM25Util.java</p>
 * <p>部        门：研发一部
 * <p>版        本： 1.0</p>
 * <p>Compiler: JDK1.6.0_21</p>
 * <p>创  建  者：yongqin.zhong</p>
 * <p>创建时间：Aug 6, 20149:58:08 AM</p>
 * <p>修  改  者：</p>
 * <p>修改时间：</p>
 */
package com.zdnst.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * PM2.5获取工具类
 * <p>描        述：</p>
 * <p>项目名称:maps1.0</p>
 * <p>所在位置:com.zdnst.common.utilsPM25Util.java</p>
 * <p>类名:PM25Util.java</p>
 * <p>创  建  人：yongqin.zhong</p>
 * <p>创建时间：Aug 6, 20149:58:38 AM</p>
 */
public class PM25Util {
	private static final Log log = LogFactory.getLog(PM25Util.class);
	public static Map<String,String> pm25CityNameMap=new HashMap<String,String>();
	
	public static Map<String,String> getAllCityPM25(){
		try {
			String pm25Url="http://www.pm25.in/api/querys/all_cities.json?token=5j1znBVAsnSf5xQyNQyq";
			StringBuilder builder = null;
			URL url;
			url = new URL(pm25Url);
			
			HttpURLConnection httpConn = (HttpURLConnection) url
					.openConnection();
			// 设置连接超时
			httpConn.setConnectTimeout(1 * 1000);
			httpConn.setRequestMethod("GET");

			builder = new StringBuilder();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpConn.getInputStream(), "utf-8"));
			String s = null;
			while ((s = reader.readLine()) != null) {
				builder.append(s);
			}
			
			//String wJsonStr="[{\"aqi\":53,\"area\":\"广州\",\"pm2_5\":36,\"pm2_5_24h\":60,\"quality\":\"良\",\"primary_pollutant\":\"颗粒物(PM2.5),颗粒物(PM10)\",\"time_point\":\"2014-08-04T11:00:00Z\"}]";
			String wJsonStr = builder.toString();
			//System.out.println(wJsonStr);
			if(CommonUtils.isNotEmpty(wJsonStr)&&!wJsonStr.contains("error")){
				wJsonStr = CommonUtils.fromDecodeUTF8(wJsonStr);
				JSONArray joList = JSONArray.fromObject(wJsonStr);
				if(joList!=null){
					for(int i=0;i<joList.size();i++){
						JSONObject jsonObject= (JSONObject)joList.get(i);
						String area = jsonObject.getString("area").toString();//pm25
						String position_name = jsonObject.getString("position_name").toString();//市监测中心
						String pm25 = jsonObject.getString("pm2_5").toString();//pm25
						String pm25_24h = jsonObject.getString("pm2_5_24h").toString();//pm2_5_24h
						if(position_name!=null&&position_name.contains("监测")){
							pm25CityNameMap.put(area, pm25);
						}
					}
					
				}
			}else{
				log.info("pm25 error message:"+wJsonStr);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return pm25CityNameMap;
	}
	
	
	public static String getPM2_5ByCityName(String cityName){
		String pm25="";
		if(CommonUtils.isEmpty(cityName))
		cityName=Constants.SYS_DEFAULT_CITY_NAME;
			try {

				String resultMap=(String)pm25CityNameMap.get(cityName);
				if(resultMap==null){
					String weatherUrl="http://www.pm25.in/api/querys/pm2_5.json?city="+cityName
							+"&token=5j1znBVAsnSf5xQyNQyq&avg=true&stations=no";
					StringBuilder builder = null;
					URL url;
					url = new URL(weatherUrl);
					
					HttpURLConnection httpConn = (HttpURLConnection) url
							.openConnection();
					// 设置连接超时
					httpConn.setConnectTimeout(1 * 1000);
					httpConn.setRequestMethod("GET");
		
					builder = new StringBuilder();
		
					BufferedReader reader = new BufferedReader(new InputStreamReader(
							httpConn.getInputStream(), "utf-8"));
					String s = null;
					while ((s = reader.readLine()) != null) {
						builder.append(s);
					}
					
					//String wJsonStr="[{\"aqi\":53,\"area\":\"广州\",\"pm2_5\":36,\"pm2_5_24h\":60,\"quality\":\"良\",\"primary_pollutant\":\"颗粒物(PM2.5),颗粒物(PM10)\",\"time_point\":\"2014-08-04T11:00:00Z\"}]";
					String wJsonStr = builder.toString();
					//System.out.println(wJsonStr);
					if(CommonUtils.isNotEmpty(wJsonStr)&&!wJsonStr.contains("error")){
						wJsonStr = CommonUtils.fromDecodeUTF8(wJsonStr);
						JSONArray jo = JSONArray.fromObject(wJsonStr);
						if(jo!=null){
							JSONObject jsonObject= (JSONObject)jo.get(0);
							pm25 = jsonObject.getString("pm2_5").toString();//风况
							if(CommonUtils.isNotEmpty(pm25))
								pm25CityNameMap.put(cityName, pm25);
				        	//System.out.println(resultMap);
						}
					}else{
						log.info("pm25:"+"cityName="+cityName+"&error:"+wJsonStr);
					}
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		return pm25;
	}
	
	
	public static void main(String[] args) {
		//System.out.println(getAllCityPM25());
		//System.out.println(getPM2_5ByCityName("广州"));
	}

}

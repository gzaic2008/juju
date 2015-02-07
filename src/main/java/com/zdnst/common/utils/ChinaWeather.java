package com.zdnst.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 得到未来6天的天气(含今天) 中国国家气象局
 * @author yongqin.zhong
 *
 */
public class ChinaWeather { 
	
	private static final Log log = LogFactory.getLog(ChinaWeather.class);
	
	public static Map<String,Object> weatherCurDateCacheMap=new HashMap<String,Object>();
	
	public static Map<String,Object> weatherCaches=new HashMap<String,Object>();
	
    static Map<String, String> weatherMap = new HashMap<String, String>();
	static String[] weatherName1 = { "暴雨", "大暴雨", "特大暴雨", "大雨转暴雨", "暴雨转大暴雨",
			"大暴雨转特大暴雨" };// 暴雨
	static String[] weatherName2 = { "雷阵雨伴有冰雹" };// 冰雹
	static String[] weatherName3 = { "多云", "阴", "雷阵雨转多云" };// 多云
	static String[] weatherName4 = { "雷暴" };// 雷暴
	static String[] weatherName5 = { "雷阵雨" };// 雷阵雨
	static String[] weatherName6 = { "晴", "扬沙", "强沙尘暴", "多云转晴", "沙尘暴" };// 晴
	static String[] weatherName7 = { "浮尘", "晴转多云" };// 晴转多云
	static String[] weatherName8 = { "雨夹雪", "小雨", "中雨", "小雨转中雨", "中雨转大雨" };// 晴转雨
	static String[] weatherName9 = { "霾","雾" };// 雾霾
	static String[] weatherName10 = {"阵雪", "小雪", "中雪", "大雪", "暴雪",
		"小雪转中雪", "中雪转大雪", "大雪转暴雪"  };// 雪
	static String[] weatherName11 = { "阵雨", "大雨", "冻雨" };// 阵雨

	static {

		for (int i = 0; i < weatherName1.length; i++) {
			weatherMap.put(weatherName1[i], "1");
		}
		for (int i = 0; i < weatherName2.length; i++) {
			weatherMap.put(weatherName2[i], "2");
		}
		for (int i = 0; i < weatherName3.length; i++) {
			weatherMap.put(weatherName3[i], "3");
		}
		for (int i = 0; i < weatherName4.length; i++) {
			weatherMap.put(weatherName4[i], "4");
		}
		for (int i = 0; i < weatherName5.length; i++) {
			weatherMap.put(weatherName5[i], "5");
		}
		for (int i = 0; i < weatherName6.length; i++) {
			weatherMap.put(weatherName6[i], "6");
		}
		for (int i = 0; i < weatherName7.length; i++) {
			weatherMap.put(weatherName7[i], "7");
		}
		for (int i = 0; i < weatherName8.length; i++) {
			weatherMap.put(weatherName8[i], "8");
		}
		for (int i = 0; i < weatherName9.length; i++) {
			weatherMap.put(weatherName9[i], "9");
		}
		for (int i = 0; i < weatherName10.length; i++) {
			weatherMap.put(weatherName10[i], "10");
		}
		for (int i = 0; i < weatherName11.length; i++) {
			weatherMap.put(weatherName11[i], "11");
		}

	}

	
	public static Map<String, String> getCurDateWeather(String cityCode) {
		Map<String, String> resultMap =null;
		try {
			if(CommonUtils.isEmpty(cityCode)){
				cityCode=Constants.SYS_DEFAULT_CITY_WEATHER_CODE;
			}
			
			resultMap=(Map<String, String>)weatherCurDateCacheMap.get(cityCode);
			if(resultMap==null){
				resultMap = new HashMap<String, String>();
				String weatherUrl="http://www.weather.com.cn/data/cityinfo/"+ cityCode+ ".html";
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
				String wJsonStr = builder.toString();
				if(CommonUtils.isNotEmpty(wJsonStr)){
					wJsonStr = CommonUtils.fromDecodeUTF8(wJsonStr);
					JSONObject jo = JSONObject.fromObject(wJsonStr);
					JSONObject info=jo.getJSONObject("weatherinfo"); 
					
		        	
		        	resultMap.put("temperature", info.getString("temp1").toString());//温度
		        	
					resultMap.put("week", CommonUtils.getXqByDate(CommonUtils.getAfterStrDay(new Date(), 0)));//日期
					String weather = info.getString("weather").toString();//风况
					String type = weatherMap.get(weather);
					if (CommonUtils.isNotEmpty(type)) {
						resultMap.put("dayPictureUrl", type);
					} else {
						resultMap.put("dayPictureUrl", "3");
					}
	
					//resultMap.put("nightPictureUrl",
							//obj.getString("nightPictureUrl"));
					resultMap.put("weather", weather);//天气
					//resultMap.put("wind", info.getString("wind"+i).toString());//风况
					
					resultMap.put("city", info.getString("city").toString());//城市
					resultMap.put("date", CommonUtils.getAfterStrDay(new Date(), 0));
					weatherCurDateCacheMap.put(cityCode, resultMap);
		        	//System.out.println(resultMap);
					log.info("getCurDateWeather "+cityCode+":"+resultMap);
				}
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return resultMap;
	}
	
	public static Map<String, String> getCurDateWeather2(String cityCode) {
		Map<String, String> resultMap =null;
		try {
			if(CommonUtils.isEmpty(cityCode)){
				cityCode=Constants.SYS_DEFAULT_CITY_WEATHER_CODE;
			}
			
			resultMap=(Map<String, String>)weatherCurDateCacheMap.get(cityCode);
			if(resultMap==null){
				resultMap = new HashMap<String, String>();
				String weatherUrl="http://www.weather.com.cn/data/sk/"+ cityCode+ ".html";
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
				String wJsonStr = builder.toString();
				if(CommonUtils.isNotEmpty(wJsonStr)){
					//wJsonStr = CommonUtils.fromDecodeUTF8(wJsonStr);
					JSONObject jo = JSONObject.fromObject(wJsonStr);
					JSONObject info=jo.getJSONObject("weatherinfo"); 
					
		        	
		        	resultMap.put("temperature", info.getString("temp").toString()+"℃");//温度
		        	
					resultMap.put("week", CommonUtils.getXqByDate(CommonUtils.getAfterStrDay(new Date(), 0)));//日期
					String weather = info.getString("WD").toString();//风况
					String type = weatherMap.get(weather);
					if (CommonUtils.isNotEmpty(type)) {
						resultMap.put("dayPictureUrl", type);
					} else {
						resultMap.put("dayPictureUrl", "3");
					}
	
					//resultMap.put("nightPictureUrl",
							//obj.getString("nightPictureUrl"));
					resultMap.put("weather", weather);//天气
					//resultMap.put("wind", info.getString("wind"+i).toString());//风况
					
					resultMap.put("city", info.getString("city").toString());//城市
					resultMap.put("date", CommonUtils.getAfterStrDay(new Date(), 0));
					weatherCurDateCacheMap.put(cityCode, resultMap);
		        	//System.out.println(resultMap);
				}
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return resultMap;
	}
	
	
	public static List<Map> getFurWeather(String cityCode,String cityName) {
		List<Map> resultList=null;
		try {
			if(CommonUtils.isEmpty(cityCode)){
				cityCode=Constants.SYS_DEFAULT_CITY_WEATHER_CODE;
			}
			
			resultList=(List<Map>)weatherCaches.get(cityCode);
			if(resultList==null){
				resultList=new ArrayList<Map>();
				String weatherUrl="http://m.weather.com.cn/atad/"+ cityCode+ ".html";
				//String weatherUrl="http://m.weather.com.cn/data/"+ cityCode+ ".html";
				StringBuilder builder = null;
				URL url;
				url = new URL(weatherUrl);
				HttpURLConnection httpConn = (HttpURLConnection) url
						.openConnection();
				// 设置连接超时
				httpConn.setConnectTimeout(5 * 1000);
				httpConn.setRequestMethod("GET");
	
				builder = new StringBuilder();
	
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						httpConn.getInputStream(), "utf-8"));
				String s = null;
				while ((s = reader.readLine()) != null) {
					builder.append(s);
				}
				String wJsonStr = builder.toString();
				if(CommonUtils.isNotEmpty(wJsonStr)){
					wJsonStr = CommonUtils.fromDecodeUTF8(wJsonStr);
					JSONObject jo = JSONObject.fromObject(wJsonStr);
					JSONObject info=jo.getJSONObject("weatherinfo"); 
					 //得到1到6天的天气情况
			        for(int i=1;i<=6;i++){
			        	Map<String, String> resultMap = new HashMap<String, String>();
			        	String temperature=info.getString("temp"+i).toString();
			        	if(temperature.indexOf("~")!=-1){
			        		temperature=temperature.substring(0,temperature.indexOf("~"));
			        	}
			        	if(i==1){
			        		Map<String, String> currentDayMap=getCurDateWeather2(cityCode);
			        		if(currentDayMap!=null&&currentDayMap.get("temperature")!=null){
			        			temperature=currentDayMap.get("temperature");
			        		}
			        		String pm25=PM25Util.pm25CityNameMap.get(cityName);
			        		if(CommonUtils.isEmpty(pm25)&&cityName!=null)
			        			pm25=PM25Util.pm25CityNameMap.get(cityName.substring(0, cityName.length()-1));
			        		if(CommonUtils.isEmpty(pm25)&&cityName!=null)
			        			pm25=PM25Util.getPM2_5ByCityName(cityName);
			        		resultMap.put("pm25", pm25);//pm25
			        	}
			        	resultMap.put("temperature", temperature);//温度
						resultMap.put("week", CommonUtils.getXqByDate(CommonUtils.getAfterStrDay(new Date(), i-1)));//日期
						String weather = info.getString("weather"+i).toString();//风况
						String type = weatherMap.get(weather);
						if (CommonUtils.isNotEmpty(type)) {
							resultMap.put("dayPictureUrl", type);
						} else {
							resultMap.put("dayPictureUrl", "3");
						}
		
						//resultMap.put("nightPictureUrl",
								//obj.getString("nightPictureUrl"));
						resultMap.put("weather", info.getString("weather"+i).toString());//天气
						resultMap.put("wind", info.getString("wind"+i).toString());//风况
						
						resultMap.put("city", info.getString("city").toString());//城市
						resultMap.put("date", CommonUtils.getAfterStrDay(new Date(), i-1));
						
			        	//map.put("fl", info.getString("fl"+i).toString());//风速
			        	//map.put("index", info.getString("index").toString());// 今天的穿衣指数 
			        	//map.put("index_uv", info.getString("index_uv").toString());// 紫外指数 
			        	//map.put("index_tr", info.getString("index_tr").toString());// 旅游指数 
			        	//map.put("index_co", info.getString("index_co").toString());// 舒适指数 
			        	//map.put("index_cl", info.getString("index_cl").toString());// 晨练指数 
			        	//map.put("index_xc", info.getString("index_xc").toString());//洗车指数 
			        	//map.put("index_d", info.getString("index_d").toString());//天气详细穿衣指数 
			        	resultList.add(resultMap);
			        	
			        	//System.out.println(resultMap);
			        }
			        log.info("getFurWeather "+cityCode+":"+resultList);
			        weatherCaches.put(cityCode, resultList);
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return resultList;
	}
	
	
	
	
	
	

	public static void main(String[] args) {
		
		//System.out.println(ChinaWeather.getCurDateWeather("101280101"));

	}



} 
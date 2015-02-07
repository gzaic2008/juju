package com.zdnst.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 百度天气接口
 * @author yongqin.zhong
 *
 */
public class BaiDuWeather {

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
		Map<String, String> resultMap = new HashMap<String, String>();

		try {
			String city = URLEncoder.encode(cityCode, "UTF-8");
			String weatherUrl = "http://api.map.baidu.com/telematics/v3/weather?location="
					+ city + "&output=json&ak=B122767f9cf32ad2c5a17d97835d053e";
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
			wJsonStr = CommonUtils.fromDecodeUTF8(wJsonStr);
			JSONObject jo = JSONObject.fromObject(wJsonStr);
			String error = jo.getString("error");
			if ("0".equals(error)) {
				JSONArray results = jo.getJSONArray("results");
				if (results != null && results.size() > 0) {
					JSONObject result = results.getJSONObject(0);
					JSONArray weatherData = result.getJSONArray("weather_data");
					if (weatherData != null && weatherData.size() > 0) {
						JSONObject obj = (JSONObject) weatherData.get(0);
						resultMap.put("day", obj.getString("date"));
						String date = obj.getString("date");
						String temperature = "";
						if (date != null && date.length() > 17) {
							temperature = date.substring(14, 17);
						}
						String weather = obj.getString("weather");
						String type = weatherMap.get(weather);
						if (CommonUtils.isNotEmpty(type)) {
							resultMap.put("dayPictureUrl", type);
						} else {
							resultMap.put("dayPictureUrl", "3");
						}

						resultMap.put("nightPictureUrl",
								obj.getString("nightPictureUrl"));
						resultMap.put("weather", weather);
						resultMap.put("wind", obj.getString("wind"));
						resultMap.put("temperature", temperature);
					}
				}
			}
			// System.out.println(resultMap);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return resultMap;
	}
	
	
	public static List<Map> getFurWeather(String cityCode,int day) {
		List<Map> resultList=new ArrayList<Map>();
		try {
			String city = URLEncoder.encode(cityCode, "UTF-8");
			//String weatherUrl = "http://api.map.baidu.com/telematics/v3/weather?location="
			//		+ city + "&output=json&ak=B122767f9cf32ad2c5a17d97835d053e";
			String weatherUrl="http://www.google.com/ig/api?hl=zh_cn&weather="+ city;
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
			wJsonStr = CommonUtils.fromDecodeUTF8(wJsonStr);
			JSONObject jo = JSONObject.fromObject(wJsonStr);
			String error = jo.getString("error");
			if ("0".equals(error)) {
				JSONArray results = jo.getJSONArray("results");
				if (results != null && results.size() > 0) {
					JSONObject result = results.getJSONObject(0);
					JSONArray weatherData = result.getJSONArray("weather_data");
					if (weatherData != null && weatherData.size() > 0) {
						
						for(int i=0;i<weatherData.size();i++){
							if(i>=day){
								break;
							}
							Map<String, String> resultMap = new HashMap<String, String>();
							JSONObject obj = (JSONObject) weatherData.get(i);
							
							String date = obj.getString("date");
							if(i==0){
							String temperature = "";
							if (date != null && date.length() > 17) {
								temperature = date.substring(14, 17);
							}
								resultMap.put("temperature", temperature);
								resultMap.put("week", date.substring(0,2));
							}else{
								resultMap.put("temperature", obj.getString("temperature"));
								resultMap.put("week", date);
							}
							String weather = obj.getString("weather");
							String type = weatherMap.get(weather);
							if (CommonUtils.isNotEmpty(type)) {
								resultMap.put("dayPictureUrl", type);
							} else {
								resultMap.put("dayPictureUrl", "3");
							}
	
							//resultMap.put("nightPictureUrl",
									//obj.getString("nightPictureUrl"));
							resultMap.put("weather", weather);
							resultMap.put("wind", obj.getString("wind"));
							
							resultMap.put("city", cityCode);
							resultMap.put("date", CommonUtils.getAfterStrDay(new Date(), i));
							
							resultList.add(resultMap);
						}
					}
				}
			}
			// System.out.println(resultMap);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return resultList;
	}
	

	public static void main(String[] args) {
		
		//System.out.println(BaiDuWeather.getFurWeather("广州市",3));

	}

}

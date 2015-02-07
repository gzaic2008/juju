package com.zdnst.common.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherUtil {

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
		return ChinaWeather.getCurDateWeather(cityCode);
	}
	
	
	public static List<Map> getFurWeather(String cityCode,String cityName) {
		return ChinaWeather.getFurWeather(cityCode,cityName);
	}
	

	public static void main(String[] args) {
		
		//System.out.println(WeatherUtil.getFurWeather("10012","广州市"));

	}

}

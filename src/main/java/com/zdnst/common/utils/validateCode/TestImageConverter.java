package com.zdnst.common.utils.validateCode;
import java.io.File;

public class TestImageConverter {
	public static String _SCALE_ = "_等比_";
	public static String _NOSCALE_ = "_非等比_";
	public static String _WH_ = "_固定宽高_";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String dir = "D:/test/";
		String imgSrc = dir + "IMG_0195.JPG";
//		imgSrc = dir + "desk.bmp";
		Float scale = null;
		Integer fixed_width = 500;
		Integer fixed_height = 400;
		
		try {
			//等比缩放
			testScale(imgSrc , scale ,fixed_width ,fixed_height);
			//非等比缩放
			testNoScale(imgSrc ,fixed_width ,fixed_height);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 等比缩放
	 * @param imgSrc
	 * @param fixed_width
	 * @param fixed_height
	 * @throws Exception 
	 */
	public static void testScale(String imgSrc ,Float scale ,Integer fixed_width ,Integer fixed_height) throws Exception{
		File f = new File(imgSrc);
		int lastIndex = f.getAbsolutePath().lastIndexOf(File.separator);
		//父目录
		String path = f.getAbsolutePath().substring(0 , lastIndex+1);
		
		ImageConverter.zoomImage(imgSrc, path+ _SCALE_ + _WH_ + f.getName(), scale, fixed_width, fixed_height);
	}
	
	public static void testNoScale(String imgSrc ,Integer fixed_width ,Integer fixed_height) throws Exception{
		File f = new File(imgSrc);
		int lastIndex = f.getAbsolutePath().lastIndexOf(File.separator);
		//父目录
		String path = f.getAbsolutePath().substring(0 , lastIndex+1);
		
		ImageConverter.zoomImage(imgSrc, path+ _WH_ + f.getName(), fixed_width, fixed_height);
	}
}


/**
 * Copyright(C) 2012-2014 GuangZhou zdnst Co., Ltd. All Rights Reserved.<br/>
 * 版权所有(C)2012-2014 <br/>
 * 公司名称：广州支点科技有限公司<br/>
 * 公司地址：广州市天河区天源路401号之三E2栋<br/>
 * 网址：http://www.100100system.com/<br/>
 * <p>标        题：</p>
 * <p>文  件  名：com.zdnst.common.utils.ImageTools.java</p>
 * <p>部        门：研发一部
 * <p>版        本： 1.0</p>
 * <p>Compiler: JDK1.6.0_21</p>
 * <p>创  建  者：kylin.woo</p>
 * <p>创建时间：2014年6月20日下午1:25:07</p>
 * <p>修  改  者：</p>
 * <p>修改时间：</p>
 */
package com.zdnst.common.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.apache.log4j.Logger;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageTools {
	
	private static Logger logger = Logger.getLogger(ImageTools.class);

	/**
	 * 指定大小进行缩放   缩放到至少长宽都比要求的大
	 * <desc>
	 *  1.size(200,300) 若图片横比200小，高比300小，不变
	 *  2.若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
	 *  3.若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
	 * <desc>
	 * @param File 
	 * @param width 图片宽
	 * @param height 图片高
	 * @param targetPath 要存放图片的路径
	 * @throws IOException
	 * @return 成功返回图片存放路径
	 */
	public static void scaleBySize(InputStream inputStream,String fileName,ServletOutputStream out) throws IOException {
		//获取图片的原长、宽  ssss*400x200.jpg
		String str = "";
		if(fileName.indexOf("*")!=-1){
			str = fileName.substring(fileName.indexOf("*")+1,fileName.indexOf("_"));
		}else{
			String subFileName=fileName.substring(fileName.indexOf("_")+1,fileName.length());
			str = subFileName.substring(0,subFileName.indexOf("_"));
		}
		String endTag = fileName.substring(fileName.lastIndexOf(".")+1);   //后缀
		if(str.indexOf("x")!=-1){
			String[] oldSizes = str.split("x");
			Pattern pattern = Pattern.compile("[0-9]*"); 
			boolean isNum = pattern.matcher(oldSizes[0]).matches()&&pattern.matcher(oldSizes[1]).matches();  //都是数字
			if(isNum){
				int oldWidth = Integer.parseInt(oldSizes[0]);   //原图宽
				int oldHeight = Integer.parseInt(oldSizes[1]);  //原图高
				String newSize = fileName.substring(fileName.lastIndexOf("_")+1,fileName.lastIndexOf("."));//缩放要求的尺寸
				String[] newSizes = newSize.split("x");
				isNum = pattern.matcher(newSizes[0]).matches()&&pattern.matcher(newSizes[1]).matches(); 
				if(isNum){
					int newWidth = Integer.parseInt(newSizes[0]);   //要求要压缩的宽
					int newHeight = Integer.parseInt(newSizes[1]);  //要求要压缩的高
					float wRatio =  Float.valueOf(oldWidth) /Float.valueOf(newWidth);
					float hRatio =  Float.valueOf(oldHeight) /Float.valueOf(newHeight);
					//计算假设等比压缩后能不能达到两边都不小于要求的长宽 
					if(oldWidth<newWidth&&oldHeight<newHeight){
						//按照指定大小缩放
						//Thumbnails.of(inputStream).size(newWidth, newHeight).keepAspectRatio(false).outputFormat(endTag).toOutputStream(out);
						
						//根据短边放大再裁剪
						if(wRatio<hRatio){  //对比原图的宽大还是高大。因为要按大边等比压缩
							int targetWidth=(int)(oldHeight/wRatio);
							BufferedImage bufferedImage =  Thumbnails.of(inputStream).size(newWidth,targetWidth ).keepAspectRatio(false).asBufferedImage();//等比压缩
							Thumbnails.of(bufferedImage).sourceRegion(Positions.CENTER, newWidth,     
									newHeight).size(newWidth, newHeight).outputFormat(endTag).toOutputStream(out);
						}else{
							int targetHeight=(int)(oldWidth/hRatio);
							BufferedImage bufferedImage =  Thumbnails.of(inputStream).size(targetHeight, newHeight).keepAspectRatio(false).asBufferedImage();//等比压缩
							Thumbnails.of(bufferedImage).sourceRegion(Positions.CENTER, newWidth,     
									newHeight).size(newWidth, newHeight).outputFormat(endTag).toOutputStream(out);
						}
						
						
						//原图出去 
						//Thumbnails.of(inputStream).size(oldWidth, oldHeight).outputFormat(endTag).toOutputStream(out);
					}else if(oldWidth<newWidth){
						//按照指定大小缩放
						//Thumbnails.of(inputStream).size(newWidth, newHeight).keepAspectRatio(false).outputFormat(endTag).toOutputStream(out);
						
						//根据短边放大再裁剪
						int targetWidth=(int)(oldHeight/wRatio);
						BufferedImage bufferedImage =  Thumbnails.of(inputStream).size(newWidth,targetWidth ).keepAspectRatio(false).asBufferedImage();//等比压缩
						Thumbnails.of(bufferedImage).sourceRegion(Positions.CENTER, newWidth,     
								newHeight).size(newWidth, newHeight).outputFormat(endTag).toOutputStream(out);
						
						
						//如果原图宽小了 ，则根据高裁剪出去
						//Thumbnails.of(inputStream).sourceRegion(Positions.CENTER, oldWidth,     
						//		newHeight).size(oldWidth,newHeight).outputFormat(endTag).toOutputStream(out);
					}else if(oldHeight<newHeight){
						//按照指定大小缩放
						//Thumbnails.of(inputStream).size(newWidth, newHeight).keepAspectRatio(false).outputFormat(endTag).toOutputStream(out);
						
						int targetHeight=(int)(oldWidth/hRatio);
						BufferedImage bufferedImage =  Thumbnails.of(inputStream).size(targetHeight, newHeight).keepAspectRatio(false).asBufferedImage();//等比压缩
						Thumbnails.of(bufferedImage).sourceRegion(Positions.CENTER, newWidth,     
								newHeight).size(newWidth, newHeight).outputFormat(endTag).toOutputStream(out);
						
						//如果原图高小了 ，则根据宽裁剪出去   
						//Thumbnails.of(inputStream).sourceRegion(Positions.CENTER, newWidth,     
						//		oldHeight).size(newWidth,oldHeight).outputFormat(endTag).toOutputStream(out);
						//Thumbnails.of(inputStream).size(oldWidth,oldHeight).outputFormat(endTag).toOutputStream(out);
					}else{//原图宽高都比要求的图片大
						
						//float wRatio =  new BigDecimal(oldWidth / newWidth).setScale(10, BigDecimal.ROUND_HALF_UP).floatValue();  //如果按宽比压缩的压缩比
						//float hRatio = new BigDecimal(oldHeight / newHeight).setScale(10, BigDecimal.ROUND_HALF_UP).floatValue();//如果按高等比压缩的压缩比
						if(wRatio<hRatio){  //对比原图的宽大还是高大。因为要按大边等比压缩
							//被压缩后的比例宽小，则按照宽进行压缩
							BufferedImage bufferedImage =  Thumbnails.of(inputStream).size(newWidth, newWidth*2).asBufferedImage();//等比压缩
							Thumbnails.of(bufferedImage).sourceRegion(Positions.CENTER, newWidth,     
									newHeight).size(newWidth, newHeight).outputFormat(endTag).toOutputStream(out);
							/**
							
							//按宽等比压缩，则计算高等比压缩出来是否小于要求的高
							int hPredict = (int)Math.ceil((new BigDecimal(newHeight / wRatio).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()));  //预计出来的高
							if(hPredict<newHeight){
								//如果宽压到目标高的比例
								float temp = new BigDecimal(oldWidth / newHeight).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
								int tarWidth = (int)((float)newHeight*temp+0.1);
								//按重新计算的等比压缩要求的宽进行缩放再裁剪，而不是按原目标等比压缩裁剪
								BufferedImage bufferedImage =  Thumbnails.of(inputStream).size(tarWidth, newHeight).asBufferedImage();//等比压缩
								Thumbnails.of(bufferedImage).sourceRegion(Positions.CENTER, newWidth,     
										newHeight).size(newWidth, newHeight).outputFormat(endTag).toOutputStream(out);
							}else{//按宽等比压缩后预计高等比压也不小于要求的高，则按原参数等比压缩再裁剪
								BufferedImage bufferedImage =  Thumbnails.of(inputStream).size(newWidth, oldHeight).asBufferedImage();
								Thumbnails.of(bufferedImage).sourceRegion(Positions.CENTER, newWidth,     
										newHeight).size(newWidth, newHeight).outputFormat(endTag).toOutputStream(out);
							}*/
						}else{
							//被压缩后的比例宽大，则按照高进行压缩
							BufferedImage bufferedImage =  Thumbnails.of(inputStream).size(newHeight*2, newHeight).asBufferedImage();//等比压缩
							Thumbnails.of(bufferedImage).sourceRegion(Positions.CENTER, newWidth,     
									newHeight).size(newWidth, newHeight).outputFormat(endTag).toOutputStream(out);
							
							/**
							//按高等比压缩，则计算宽等比压缩出来是否小于要求的宽
							int wPredict = (int)Math.ceil((new BigDecimal(newWidth / hRatio).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()));  //预计出来的宽
							if(wPredict<newWidth){
								//如果高压到目标宽的比例
								float temp = new BigDecimal(oldHeight / newWidth).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
								int tarHeight = (int)((float)newWidth*temp+0.1);
								BufferedImage bufferedImage =  Thumbnails.of(inputStream).size(newWidth, tarHeight).asBufferedImage();//等比压缩
								Thumbnails.of(bufferedImage).sourceRegion(Positions.CENTER, newWidth,     
										newHeight).size(newWidth, newHeight).outputFormat(endTag).toOutputStream(out);
							}else{
								BufferedImage bufferedImage =  Thumbnails.of(inputStream).size(newWidth, newHeight).asBufferedImage();
								Thumbnails.of(bufferedImage).sourceRegion(Positions.CENTER, newWidth,     
										newHeight).size(newWidth, newHeight).outputFormat(endTag).toOutputStream(out);
							}*/
							
						}
					}
				}
			}
		}else{
			logger.error("ERROR：获取原图片长宽失败！");
		}
	}
	
	
	/**
	 * 指定固定高度进行缩放
	 * @param inputStream
	 * @param fileName
	 * @param out
	 * @throws IOException
	 */
	public static void scaleByHeight(InputStream inputStream,String fileName,ServletOutputStream out) throws IOException {
		//获取图片的原长、宽  ssss*400x200.jpg
		String str = "";
		if(fileName.indexOf("*")!=-1){
			str = fileName.substring(fileName.indexOf("*")+1,fileName.indexOf("_"));
		}else{
			String subFileName=fileName.substring(fileName.indexOf("_")+1,fileName.length());
			str = subFileName.substring(0,subFileName.indexOf("_"));
		}
		String endTag = fileName.substring(fileName.lastIndexOf(".")+1);   //后缀
		if(str.indexOf("x")!=-1){
			String[] oldSizes = str.split("x");
			Pattern pattern = Pattern.compile("[0-9]*"); 
			boolean isNum = pattern.matcher(oldSizes[0]).matches()&&pattern.matcher(oldSizes[1]).matches();  //都是数字
			if(isNum){
				int oldWidth = Integer.parseInt(oldSizes[0]);   //原图宽
				int oldHeight = Integer.parseInt(oldSizes[1]);  //原图高
				String newSize = fileName.substring(fileName.lastIndexOf("_")+1,fileName.lastIndexOf("."));//缩放要求的尺寸
				String[] newSizes = newSize.split("x");
				isNum = pattern.matcher(newSizes[0]).matches()&&pattern.matcher(newSizes[1]).matches(); 
				if(isNum){
					int newWidth = Integer.parseInt(newSizes[0]);   //要求要压缩的宽
					int newHeight = Integer.parseInt(newSizes[1]);  //要求要压缩的高
					float wRatio =  Float.valueOf(oldWidth) /Float.valueOf(newWidth);
					float hRatio =  Float.valueOf(oldHeight) /Float.valueOf(newHeight);
					//计算假设等比压缩后能不能达到两边都不小于要求的长宽 
					if(oldHeight<newHeight){
						//按照指定高缩放
						
						int targetHeight=(int)(oldWidth/hRatio);
						Thumbnails.of(inputStream).size(targetHeight, newHeight).keepAspectRatio(false).outputFormat(endTag).toOutputStream(out);;//等比压缩
					}else{//原图高比要求图片大
						//按照高进行压缩
						Thumbnails.of(inputStream).size(newHeight*2, newHeight).outputFormat(endTag).toOutputStream(out);
					}
				}
			}
		}else{
			logger.error("ERROR：获取原图片长宽失败！");
		}
	}
	
	/**
	 * 指定固定宽度进行缩放
	 * @param inputStream
	 * @param fileName
	 * @param out
	 * @throws IOException
	 */
	public static void scaleByLength(InputStream inputStream,String fileName,ServletOutputStream out) throws IOException {
		//获取图片的原长、宽  ssss*400x200.jpg
		String str = "";
		if(fileName.indexOf("*")!=-1){
			str = fileName.substring(fileName.indexOf("*")+1,fileName.indexOf("_"));
		}else{
			String subFileName=fileName.substring(fileName.indexOf("_")+1,fileName.length());
			str = subFileName.substring(0,subFileName.indexOf("_"));
		}
		String endTag = fileName.substring(fileName.lastIndexOf(".")+1);   //后缀
		if(str.indexOf("x")!=-1){
			String[] oldSizes = str.split("x");
			Pattern pattern = Pattern.compile("[0-9]*"); 
			boolean isNum = pattern.matcher(oldSizes[0]).matches()&&pattern.matcher(oldSizes[1]).matches();  //都是数字
			if(isNum){
				int oldWidth = Integer.parseInt(oldSizes[0]);   //原图宽
				int oldHeight = Integer.parseInt(oldSizes[1]);  //原图高
				String newSize = fileName.substring(fileName.lastIndexOf("_")+1,fileName.lastIndexOf("."));//缩放要求的尺寸
				String[] newSizes = newSize.split("x");
				isNum = pattern.matcher(newSizes[0]).matches()&&pattern.matcher(newSizes[1]).matches(); 
				if(isNum){
					int newWidth = Integer.parseInt(newSizes[0]);   //要求要压缩的宽
					int newHeight = Integer.parseInt(newSizes[1]);  //要求要压缩的高
					float wRatio =  Float.valueOf(oldWidth) /Float.valueOf(newWidth);
					float hRatio =  Float.valueOf(oldHeight) /Float.valueOf(newHeight);
					//原图宽度比目标小
					if(oldWidth<newWidth){
						//根据宽放大输出
						int targetWidth=(int)(oldHeight/wRatio);
						Thumbnails.of(inputStream).size(newWidth,targetWidth ).keepAspectRatio(false).outputFormat(endTag).toOutputStream(out);
					}else{//原图宽都比要求的图片大
						//则按照宽进行压缩
						Thumbnails.of(inputStream).size(newWidth, newWidth*2).outputFormat(endTag).toOutputStream(out);//等比压缩
					}
				}
			}
		}else{
			logger.error("ERROR：获取原图片长宽失败！");
		}
	}
	
	
	public static void scaleByHeight_bank(InputStream inputStream,String fileName,OutputStream os) throws IOException {
		//获取图片的原长、宽
		//String str = fileName.substring(fileName.indexOf("*")+1,fileName.indexOf("_"));
		
		String str = "";
		if(fileName.indexOf("*")!=-1){
			str = fileName.substring(fileName.indexOf("*")+1,fileName.indexOf("_"));
		}else{
			String subFileName=fileName.substring(fileName.indexOf("_")+1,fileName.length());
			str = subFileName.substring(0,subFileName.indexOf("_"));
		}
		
		String endTag = fileName.substring(fileName.lastIndexOf(".")+1);
		if(str.indexOf("x")!=-1){
			String[] oldSizes = str.split("x");
			Pattern pattern = Pattern.compile("[0-9]*"); 
			boolean isNum = pattern.matcher(oldSizes[0]).matches()&&pattern.matcher(oldSizes[1]).matches();  //都是数字
			if(isNum){
				int oldWidth = Integer.parseInt(oldSizes[0]);   //原图宽
				int oldHeight = Integer.parseInt(oldSizes[1]);  //原图高
				String newSize = fileName.substring(fileName.lastIndexOf("_")+1,fileName.lastIndexOf("."));//缩放要求的尺寸
				String[] newSizes = newSize.split("x");
				isNum = pattern.matcher(newSizes[0]).matches()&&pattern.matcher(newSizes[1]).matches(); 
				if(isNum){
					int newWidth = Integer.parseInt(newSizes[0]);   //要求要压缩的宽
					int newHeight = Integer.parseInt(newSizes[1]);  //要求要压缩的高
					if(oldHeight<newHeight){  //如果原图高小于要求的高
						Thumbnails.of(inputStream).outputFormat(endTag).toOutputStream(os);
					}else{
						float hRatio = new BigDecimal(oldHeight / newHeight).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();//如果按高等比压缩的压缩比
						int wPredict = (int)Math.ceil((new BigDecimal(oldWidth / hRatio).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue()));  //预计出来的宽
						Thumbnails.of(inputStream).size(wPredict,newHeight).outputFormat(endTag).toOutputStream(os);
					}
				}
				
			}
		}
		
		
		//Thumbnails.of(inputStream).size(w, h).outputFormat(endTag).toOutputStream(os);
	}
	
	/**
	 * 指定大小进行缩放
	 * <desc>
	 *  1.size(200,300) 若图片横比200小，高比300小，不变
	 *  2.若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
	 *  3.若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
	 * <desc>
	 * @param File 
	 * @param width 图片宽
	 * @param height 图片高
	 * @param targetPath 要存放图片的路径
	 * @throws IOException
	 * @return 成功返回图片存放路径
	 */
	public static void scaleBySize(BufferedImage bufferedImage,String fileName,int w,int h,OutputStream os) throws IOException {
		String endTag = fileName.substring(fileName.lastIndexOf(".")+1);
		Thumbnails.of(bufferedImage).size(w, h).outputFormat(endTag).toOutputStream(os);
	}
	
	public static void scaleBySize(InputStream in,String fileName,OutputStream os) throws IOException {
		String sizes = fileName.substring(fileName.lastIndexOf("_")+1,fileName.lastIndexOf("."));
		String[] wh = sizes.split("x");
		Thumbnails.of(in).size(Integer.parseInt(wh[0]), Integer.parseInt(wh[1])).toOutputStream(os);
	}
	
	/**
	 * 指定大小进行缩放
	 * <desc>
	 *  1.size(200,300) 若图片横比200小，高比300小，不变
	 *  2.若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
	 *  3.若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
	 * <desc>
	 * @param File 
	 * @param width 图片宽
	 * @param height 图片高
	 * @param targetPath 要存放图片的路径
	 * @throws IOException
	 * @return 成功返回图片存放路径
	 */
	public static void scaleBySize(InputStream inputStream,String fileName,int w,int h,OutputStream os) throws IOException {
		String endTag = fileName.substring(fileName.lastIndexOf(".")+1);
		Thumbnails.of(inputStream).size(w, h).outputFormat(endTag).toOutputStream(os);
	}
	
	
	
	/**
	 * 指定大小进行缩放
	 * <desc>
	 *  1.size(200,300) 若图片横比200小，高比300小，不变
	 *  2.若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
	 *  3.若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
	 * <desc>
	 * @param File 
	 * @param width 图片宽
	 * @param height 图片高
	 * @param targetPath 要存放图片的路径
	 * @throws IOException
	 * @return 成功返回图片存放路径
	 */
	public static String scaleBySize(File file,int width,int height,String targetPath) throws IOException {
		try {
			String filename = file.getName();
			String endTag = filename.substring(filename.lastIndexOf(".")+1);
			String path = targetPath+"/"+filename+"_"+width+"x"+height+"."+endTag;
			Thumbnails.of(file).size(width, height).toFile(path);
			return path;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 按照比例进行缩放
	 * @param File 
	 * @param float 缩放的比例
	 * @param targetPath 要存放图片的路径
	 * @throws IOException
	 * @return 成功返回图片存放路径
	 */
	public static String scaleByRatio (File file,float ratiof,String targetPath) throws IOException {
		try {
			String filename = file.getName();
			String endTag = filename.substring(filename.lastIndexOf(".")+1);
			String path = targetPath+"/"+filename+"_"+(ratiof*100)+"."+endTag;
			Thumbnails.of(file).scale(0.25f).toFile(path);
			return path;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 不按照比例，指定大小进行缩放(图片可能会变形)
	 * @param File 
	 * @param width 图片宽
	 * @param height 图片高
	 * @param targetPath 要存放图片的路径
	 * @throws IOException
	 * @return 成功返回图片存放路径
	 */
	public static String scaleByNoRatio(File file,int width,int height,String targetPath) throws IOException {
		try {
			String filename = file.getName();
			String endTag = filename.substring(filename.lastIndexOf(".")+1);
			String path = targetPath+"/"+filename+"_"+width+"x"+height+"."+endTag;
			Thumbnails.of(file).size(120, 120).keepAspectRatio(false).toFile(path);
			return path;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 旋转图片
	 * @param File 
	 * @param rotate(角度),正数：顺时针, 负数：逆时针
	 * @param targetPath 要存放图片的路径
	 * @throws IOException
	 * @return 成功返回图片存放路径
	 */
	public static String rotate(File file,int rotate,String targetPath) throws IOException {
		try {
			String filename = file.getName();
			String endTag = filename.substring(filename.lastIndexOf(".")+1);
			String path = "";
			if(rotate<0){
				path = targetPath+"/"+filename+"_"+"-"+rotate+"."+endTag;
			}else{
				path = targetPath+"/"+filename+"_"+"+"+rotate+"."+endTag;
			}
			Thumbnails.of(file).size(1280, 1024).rotate(rotate).toFile(path);
			return path;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 水印(位置，水印图，透明度)
	 * @param File file 要设置的图片
	 * @param File mark 水印图
	 * @param 要设置的位置  默认"CENTER"(可传:"CENTER","CENTER_LEFT","CENTER_RIGHT","TOP_CENTER","TOP_LEFT","TOP_RIGHT","BOTTOM_CENTER","BOTTOM_LEFT","BOTTOM_RIGHT")
	 * @param Float quality1f 传入图片的透明度（质量，如0.5f,默认0.5）
	 * @param Float quality2f 输出图片的透明度（质量，如0.8f默认0.5）
	 * @param targetPath 输出图片的位置
	 * @throws IOException
	 */
	public static String waterMark(File file,File mark,String positon,Float quality1f,Float quality2f,String targetPath) throws IOException {
		
		try {
			String filename = file.getName();
			String endTag = filename.substring(filename.lastIndexOf(".")+1);
			String path = targetPath+"/"+filename+"_watermark";
			Float srcQual = null;
			Float targetQual = null;
			if(quality1f==null){
				srcQual = 0.5f;
			}else{
				srcQual = quality1f;
			}
			if(quality2f==null){
				targetQual = 0.8f;
			}else{
				targetQual = quality2f;
			}
			if(positon==null){
				path = path+"_center."+endTag;
				Thumbnails.of(file).watermark(
						Positions.CENTER,
						ImageIO.read(mark), srcQual)
						.outputQuality(targetQual).toFile(path);
				return path;
			}else{
				if("CENTER".equalsIgnoreCase(positon)){
					path = path+"_"+positon.toLowerCase()+"."+endTag;
					Thumbnails.of(file).watermark(
							Positions.CENTER,
							ImageIO.read(mark), srcQual)
							.outputQuality(targetQual).toFile(path);
					return path;
				}else if("CENTER_LEFT".equalsIgnoreCase(positon)){
					path = path+"_"+positon.toLowerCase()+"."+endTag;
					Thumbnails.of(file).watermark(
							Positions.CENTER_LEFT,
							ImageIO.read(mark), srcQual)
							.outputQuality(targetQual).toFile(path);
					return path;
				}else if("CENTER_RIGHT".equalsIgnoreCase(positon)){
					path = path+"_"+positon.toLowerCase()+"."+endTag;
					Thumbnails.of(file).watermark(
							Positions.CENTER_RIGHT,
							ImageIO.read(mark), srcQual)
							.outputQuality(targetQual).toFile(path);
					return path;
				}else if("TOP_CENTER".equalsIgnoreCase(positon)){
					path = path+"_"+positon.toLowerCase()+"."+endTag;
					Thumbnails.of(file).watermark(
							Positions.TOP_CENTER,
							ImageIO.read(mark), srcQual)
							.outputQuality(targetQual).toFile(path);
					return path;
				}else if("TOP_LEFT".equalsIgnoreCase(positon)){
					path = path+"_"+positon.toLowerCase()+"."+endTag;
					Thumbnails.of(file).watermark(
							Positions.TOP_LEFT,
							ImageIO.read(mark), srcQual)
							.outputQuality(targetQual).toFile(path);
					return path;
				}else if("TOP_RIGHT".equalsIgnoreCase(positon)){
					path = path+"_"+positon.toLowerCase()+"."+endTag;
					Thumbnails.of(file).watermark(
							Positions.TOP_RIGHT,
							ImageIO.read(mark), srcQual)
							.outputQuality(targetQual).toFile(path);
					return path;
				}else if("BOTTOM_CENTER".equalsIgnoreCase(positon)){
					path = path+"_"+positon.toLowerCase()+"."+endTag;
					Thumbnails.of(file).watermark(
							Positions.BOTTOM_CENTER,
							ImageIO.read(mark), srcQual)
							.outputQuality(targetQual).toFile(path);
					return path;
				}else if("BOTTOM_LEFT".equalsIgnoreCase(positon)){
					path = path+"_"+positon.toLowerCase()+"."+endTag;
					Thumbnails.of(file).watermark(
							Positions.BOTTOM_LEFT,
							ImageIO.read(mark), srcQual)
							.outputQuality(targetQual).toFile(path);
					return path;
				}else if("BOTTOM_RIGHT".equalsIgnoreCase(positon)){
					path = path+"_"+positon.toLowerCase()+"."+endTag;
					Thumbnails.of(file).watermark(
							Positions.BOTTOM_RIGHT,
							ImageIO.read(mark), srcQual)
							.outputQuality(targetQual).toFile(path);
					return path;
				}else{
					return "位置参数不正确";
				}
				
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 裁剪
	 * @param File
	 * @param 要设置的位置  默认"CENTER"(可传:"CENTER","CENTER_LEFT","CENTER_RIGHT",
	 * "TOP_CENTER","TOP_LEFT","TOP_RIGHT","BOTTOM_CENTER","BOTTOM_LEFT","BOTTOM_RIGHT")
	 * @param int width 
	 * @param targetPath 输出图片的位置
	 * @throws IOException
	 */
	public static void region(File file,String position,int width,int height,String targetPath) throws IOException {
		/**
		 * 图片中心400*400的区域
		 */
		Thumbnails.of(file).sourceRegion(Positions.CENTER, width,
				height).size(width, height).keepAspectRatio(false).toFile(targetPath);
//		/**
//		 * 图片右下400*400的区域
//		 */
//		Thumbnails.of("images/test.jpg").sourceRegion(Positions.BOTTOM_RIGHT,
//				400, 400).size(200, 200).keepAspectRatio(false).toFile(
//				"C:/image_region_bootom_right.jpg");
//		/**
//		 * 指定坐标
//		 */
//		Thumbnails.of("images/test.jpg").sourceRegion(600, 500, 400, 400).size(
//				200, 200).keepAspectRatio(false).toFile(
//				"C:/image_region_coord.jpg");
	}
	
	/**
	 * 裁剪
	 * @param File
	 * @param 要设置的位置  默认"CENTER"(可传:"CENTER","CENTER_LEFT","CENTER_RIGHT",
	 * "TOP_CENTER","TOP_LEFT","TOP_RIGHT","BOTTOM_CENTER","BOTTOM_LEFT","BOTTOM_RIGHT")
	 * @param int width 
	 * @param targetPath 输出图片的位置
	 * @throws IOException
	 */
	public static BufferedImage region(InputStream inputStream,String fileName,int width,int height) throws IOException {
		
		if(fileName.indexOf("CENTER_LEFT")!=-1){
			return Thumbnails.of(inputStream).sourceRegion(Positions.CENTER_LEFT, width,
					height).size(width, height).keepAspectRatio(false).asBufferedImage();
		}else if(fileName.indexOf("CENTER_RIGHT")!=-1){
			return Thumbnails.of(inputStream).sourceRegion(Positions.CENTER_RIGHT, width,
					height).size(width, height).keepAspectRatio(false).asBufferedImage();
		}else if(fileName.indexOf("TOP_CENTER")!=-1){
			return Thumbnails.of(inputStream).sourceRegion(Positions.TOP_CENTER, width,
					height).size(width, height).keepAspectRatio(false).asBufferedImage();
		}else if(fileName.indexOf("TOP_LEFT")!=-1){
			return Thumbnails.of(inputStream).sourceRegion(Positions.TOP_LEFT, width,
					height).size(width, height).keepAspectRatio(false).asBufferedImage();
		}else if(fileName.indexOf("TOP_RIGHT")!=-1){
			return Thumbnails.of(inputStream).sourceRegion(Positions.TOP_RIGHT, width,
					height).size(width, height).keepAspectRatio(false).asBufferedImage();
		}else if(fileName.indexOf("BOTTOM_CENTER")!=-1){
			return Thumbnails.of(inputStream).sourceRegion(Positions.BOTTOM_CENTER, width,
					height).size(width, height).keepAspectRatio(false).asBufferedImage();
		}else if(fileName.indexOf("BOTTOM_LEFT")!=-1){
			return Thumbnails.of(inputStream).sourceRegion(Positions.BOTTOM_LEFT, width,
					height).size(width, height).keepAspectRatio(false).asBufferedImage();
		}else if(fileName.indexOf("BOTTOM_RIGHT")!=-1){
			return Thumbnails.of(inputStream).sourceRegion(Positions.BOTTOM_RIGHT, width,
					height).size(width, height).keepAspectRatio(false).asBufferedImage();
		}else{
			return Thumbnails.of(inputStream).sourceRegion(Positions.CENTER, width,
					height).size(width, height).keepAspectRatio(false).asBufferedImage();
		}
	}
	
	public static void region(BufferedImage bufferImage,OutputStream os,String fileName) throws IOException {
		
		Integer width = null;
		Integer height = null;
		int w = bufferImage.getWidth();
		int h = bufferImage.getHeight();
		
		if(fileName.indexOf("_")!=-1){
			String name = fileName.substring(0,fileName.lastIndexOf("."));//去除后缀
			String endTag = fileName.substring(fileName.lastIndexOf(".")+1);
			String size = name.substring(name.lastIndexOf("_")+1);
			String[] sizes = size.split("x");
			Pattern pattern = Pattern.compile("[0-9]*"); 
			boolean isNum = pattern.matcher(sizes[0]).matches();
			if(isNum){
				width = Integer.parseInt(sizes[0]);
				height = Integer.parseInt(sizes[1]);
				w = bufferImage.getWidth();
				h = bufferImage.getHeight();
				if(width.intValue()>w){
					width = w;
				}
				if(height.intValue()>h){
					height = h;
				}
			}else{
				
				width = 80;   //默认压成80宽
				height = 80;  //默认压成80高
				if(80>w){
					width = w;
				}
				if(80>h){
					height = h;
				}
			}
			
			if(fileName.indexOf("CENTER_LEFT")!=-1){
				Thumbnails.of(bufferImage).sourceRegion(Positions.CENTER_LEFT, width,height)
				.size(width, height).outputFormat(endTag).toOutputStream(os);
//				Thumbnails.of(bufferImage).sourceRegion(Positions.CENTER_LEFT, width,height)
//				.size(width, height).keepAspectRatio(false).toFile(new File("c:\\test2.jpg"));
			}else if(fileName.indexOf("CENTER_RIGHT")!=-1){
				Thumbnails.of(bufferImage).sourceRegion(Positions.CENTER_RIGHT, width,height)
				.size(width, height).keepAspectRatio(false).outputFormat(endTag).toOutputStream(os);
			}else if(fileName.indexOf("TOP_CENTER")!=-1){
				Thumbnails.of(bufferImage).sourceRegion(Positions.TOP_CENTER, width,height)
				.size(width, height).keepAspectRatio(false).outputFormat(endTag).toOutputStream(os);
			}else if(fileName.indexOf("TOP_LEFT")!=-1){
				Thumbnails.of(bufferImage).sourceRegion(Positions.TOP_LEFT, width,height)
				.size(width, height).keepAspectRatio(false).outputFormat(endTag).toOutputStream(os);
			}else if(fileName.indexOf("TOP_RIGHT")!=-1){
				Thumbnails.of(bufferImage).sourceRegion(Positions.TOP_RIGHT, width,height)
				.size(width, height).keepAspectRatio(false).outputFormat(endTag).toOutputStream(os);
			}else if(fileName.indexOf("BOTTOM_CENTER")!=-1){
				Thumbnails.of(bufferImage).sourceRegion(Positions.BOTTOM_CENTER, width,height)
				.size(width, height).keepAspectRatio(false).outputFormat(endTag).toOutputStream(os);
			}else if(fileName.indexOf("BOTTOM_LEFT")!=-1){
				Thumbnails.of(bufferImage).sourceRegion(Positions.BOTTOM_LEFT, width,height)
				.size(width, height).keepAspectRatio(false).outputFormat(endTag).toOutputStream(os);
			}else if(fileName.indexOf("BOTTOM_RIGHT")!=-1){
				Thumbnails.of(bufferImage).sourceRegion(Positions.BOTTOM_RIGHT, width,height)
				.size(width, height).keepAspectRatio(false).outputFormat(endTag).toOutputStream(os);
			}else{
				Thumbnails.of(bufferImage).sourceRegion(Positions.CENTER, width,height)
				.size(width, height).keepAspectRatio(false).outputFormat(endTag).toOutputStream(os);
			}
			
			//return Thumbnails.of(file).sourceRegion(Positions.CENTER, width,height).size(width, height).keepAspectRatio(false).asBufferedImage();
		}
		
	}

	/**
	 * 转化图像格式
	 * @param File
	 * @param String format 传图片格式，如‘png’
	 * @throws IOException
	 */
	public static String outputFormat(File file,String format,String targetPath) throws IOException {

		String filename = file.getName();
		String path = targetPath + "/" + filename + "_" + format + "." + format;

		Thumbnails.of(file).outputFormat(format.toLowerCase()).toFile(path);
		return path;
//		Thumbnails.of("images/test.jpg").size(1280, 1024).outputFormat("png")
//				.toFile("C:/image_1280x1024.png");
//		Thumbnails.of("images/test.jpg").size(1280, 1024).outputFormat("gif")
//				.toFile("C:/image_1280x1024.gif");
	}

	/**
	 * 输出到OutputStream
	 * 
	 * @throws IOException
	 */
	public static String toOutputStream(File file,String targetPath) throws IOException {
		/**
		 * toOutputStream(流对象)
		 * OutputStream os = new FileOutputStream(
				"C:/image_1280x1024_OutputStream.png");
		Thumbnails.of("images/test.jpg").size(1280, 1024).toOutputStream(os);
		 *
		 */
		try {
			OutputStream os = new FileOutputStream(file);
			Thumbnails.of(targetPath).toOutputStream(os);
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		
	}

	/**
	 * 输出到BufferedImage
	 * 
	 * @throws IOException
	 */
	public static String bufferedImage(File file,String targetPath,String format) throws IOException {
		/**
		 * asBufferedImage() 返回BufferedImage
		 */
		String filename = file.getName();
		String endTag = filename.substring(filename.lastIndexOf(".")+1);
		String path = targetPath+"/"+filename+"_BufferedImage"+"."+endTag;
		BufferedImage thumbnail = Thumbnails.of(file).asBufferedImage();
		ImageIO.write(thumbnail, format, new File(path));
		return path;
	}
	
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\1234567heng_80x80.jpg");
		//region(file,"center",300,100,"c:\\123456t.jpg");
		
		//scaleBySize(file,600,100,"c:\\");
		InputStream inputStream = new FileInputStream(file);
		OutputStream out = new FileOutputStream(new File("c:\\test3.jpg"));
		
		//BufferedImage img = scaleBySize(inputStream,file.getName());
		//ImageIO.write(img,"jpg", new File("C:/test1.jpg"));
		//region(img,out,"1234567heng_80x80.jpg");
//		Thumbnails.of(img).sourceRegion(Positions.CENTER_LEFT, 100,50)
//		.size(100, 50).outputFormat("jpg").toOutputStream(out);
		
	}
	
}

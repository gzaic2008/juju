package com.zdnst.common.base;

public class FileVO {
	
	private String fileName;//存储的文件名称
	private Long fileSize;//文件大小
	private Integer fileWeight;//图片长度
	private Integer fileHeight;//图片宽度
	private String fileSuffix;//文件后缀
	private String fileUploadName;//上传的原文件名  added by pzh 2014-10-11
	
	
	
	
	
	public String getFileUploadName() {
		return fileUploadName;
	}
	public void setFileUploadName(String fileUploadName) {
		this.fileUploadName = fileUploadName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public Integer getFileWeight() {
		return fileWeight;
	}
	public void setFileWeight(Integer fileWeight) {
		this.fileWeight = fileWeight;
	}
	public Integer getFileHeight() {
		return fileHeight;
	}
	public void setFileHeight(Integer fileHeight) {
		this.fileHeight = fileHeight;
	}
	public String getFileSuffix() {
		return fileSuffix;
	}
	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}
	
	

}

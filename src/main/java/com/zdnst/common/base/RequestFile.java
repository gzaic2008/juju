package com.zdnst.common.base;

import java.util.List;
import java.util.Map;

public class RequestFile extends RequestParam {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String imgs;//文件名称，多个以","分隔
	
	private String actionCoverImg;//活动封面
	
	private String medias;//录音名称，多个以","分隔
	
	private List<FileVO> fileVoList;//文件对象列表
	
	private Map<String,String> voteMap;//投票图片集

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		this.imgs = imgs;
	}

	public List<FileVO> getFileVoList() {
		return fileVoList;
	}

	public void setFileVoList(List<FileVO> fileVoList) {
		this.fileVoList = fileVoList;
	}

	public String getMedias() {
		return medias;
	}

	public void setMedias(String medias) {
		this.medias = medias;
	}

	public Map<String, String> getVoteMap() {
		return voteMap;
	}

	public void setVoteMap(Map<String, String> voteMap) {
		this.voteMap = voteMap;
	}

	public String getActionCoverImg() {
		return actionCoverImg;
	}

	public void setActionCoverImg(String actionCoverImg) {
		this.actionCoverImg = actionCoverImg;
	}
	
	

}

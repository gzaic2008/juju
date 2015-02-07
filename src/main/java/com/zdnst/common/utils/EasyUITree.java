/**
 * Copyright(C) 2012-2014 GuangZhou zdnst Co., Ltd. All Rights Reserved.<br/>
 * 版权所有(C)2012-2014 <br/>
 * 公司名称：广州支点科技有限公司<br/>
 * 公司地址：广州市天河区天源路401号之三E2栋<br/>
 * 网址：http://www.100100system.com/<br/>
 * <p>标        题：</p>
 * <p>文  件  名：com.zdnst.common.utils.ComboTree.java</p>
 * <p>部        门：研发一部
 * <p>版        本： 1.0</p>
 * <p>Compiler: JDK1.6.0_21</p>
 * <p>创  建  者：huazhou.yang</p>
 * <p>创建时间：2014年8月7日下午1:52:03</p>
 * <p>修  改  者：</p>
 * <p>修改时间：</p>
 */
package com.zdnst.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 包括easyui的Tree,ComboTree等树结构
 * <p>描        述：</p>
 * <p>项目名称:maps1.0</p>
 * <p>所在位置:com.zdnst.common.utils.ComboTree.java</p>
 * <p>类名:ComboTree.java</p>
 * <p>创  建  人：huazhou.yang</p>
 * <p>创建时间：2014年8月7日下午1:57:12</p>
 */
public class EasyUITree implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//easyui-tree属性
	private String id;
	private String text;
	private String iconCls;
	//多选是否选中 easyui-tree属性
	private boolean checked;
	//行是否选择 easyui-tree属性
	private boolean selected;
	//状态easyui-tree属性
	private String state; //open,closed
	//是否是叶子节点
	private boolean leaf = true;
	//子级
	private List<EasyUITree> children;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean isLeaf) {
		this.leaf = isLeaf;
	}
	public List<EasyUITree> getChildren() {
		return children;
	}
	public void setChildren(List<EasyUITree> children) {
		this.children = children;
	}
	
	//添加子级
	public void addChild(EasyUITree child) {
		if(children==null){
			children = new ArrayList<EasyUITree>();
		}
		// 如果子级不存在，则添加
        if (!canFindTree(child.getId())) {  
            children.add(child);  
            setLeaf(false);
        } 
	}
	
	//以ID判断是否存在节点
	public boolean canFindTree(String id){
		boolean blReturn = false;
		if (StringUtils.isBlank(id) || children == null || children.size() == 0) {
			return blReturn;
		}
		if(findTree(children,id)!=null){
			blReturn = true;
		}
		return blReturn;
	}
	
	//以ID找节点
	public EasyUITree findTree(List<EasyUITree> children,String id){
		if (StringUtils.isBlank(id) || children == null || children.size() == 0) {
			return null;
		}
		for (EasyUITree one : children) {
			if (id.equals(one.getId())) {
				return one;
			} else {
				EasyUITree tree = findTree(one.getChildren(), id);
				if (tree != null) {
					return tree;
				}
			}
		}
		return null;
	}
	@Override
	public String toString() {
		return "EasyUITree [id=" + id + ", text=" + text + ", iconCls="
				+ iconCls + ", checked=" + checked + ", selected=" + selected
				+ ", state=" + state + ", leaf=" + leaf + ", children="
				+ children + "]";
	}
	
}

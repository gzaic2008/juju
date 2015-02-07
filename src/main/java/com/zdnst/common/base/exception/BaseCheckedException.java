package com.zdnst.common.base.exception;

/**
 * 
 * <p>描        述：检查性异常基类</p>
 * <p>项目名称:maps1.0</p>
 * <p>所在位置:com.zdnst.maps.exception.BaseCheckedException.java</p>
 * <p>类名:BaseCheckedException.java</p>
 * <p>创  建  人：huazhou.yang</p>
 * <p>创建时间：2014年7月31日下午2:54:39</p>
 */
public class BaseCheckedException extends Exception {
	private static final long serialVersionUID = 3056348980845434259L;
	
	private String code = null;// 错误代码

	public BaseCheckedException() {
        super();
    }
	
	public BaseCheckedException(String message) {
		super(message);
	}
	
	public BaseCheckedException(Throwable cause) {
		super(cause);
	}
	
	public BaseCheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseCheckedException(String code, String message) {
		this(message);
		this.code = code;
	}

	public BaseCheckedException(String code, String message, Throwable cause) {
		this(message, cause);
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "[code=" + code + "," + super.toString() + "]";
	}
}

package com.zdnst.common.base.exception;

/**
 * 
 * <p>描        述：非检查性异常（运行时异常）基类</p>
 * <p>项目名称:maps1.0</p>
 * <p>所在位置:com.zdnst.maps.exception.BaseRuntimeException.java</p>
 * <p>类名:BaseRuntimeException.java</p>
 * <p>创  建  人：huazhou.yang</p>
 * <p>创建时间：2014年7月31日下午2:55:26</p>
 */
public class BaseRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 3666558969950546685L;
	
	private String code = null;// 错误代码

	public BaseRuntimeException() {
        super();
    }
	
	public BaseRuntimeException(String message) {
		super(message);
	}

	public BaseRuntimeException(Throwable cause) {
		super(cause);
	}
	
	public BaseRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseRuntimeException(String code, String message) {
		this(message);
		this.code = code;
	}
	
	public BaseRuntimeException(String code, String message, Throwable cause) {
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

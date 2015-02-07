package com.zdnst.common.base.exception;



/**
 * 自定义异常类
* @author zhongyq
* @version 1.0.0, 2015-5-27
*/
public class ZdnstException extends BaseRuntimeException{
	private static final long serialVersionUID = -8211729708677743232L;
//	private static Logger logger = Logger.getLogger(BaseRuntimeException.class);
	public ZdnstException(String code) {
		super();
		this.setCode(code);
	}

	public ZdnstException(Throwable cause) {
		super(cause);
		if (cause instanceof ZdnstException) {
			this.setCode(((ZdnstException) cause).getCode());
		}
	}

	public ZdnstException(String code, Throwable cause) {
		super(code, null, cause);
	}

	public ZdnstException(String code, String message) {
		super(code, message);
	}

	public ZdnstException(String code, String message, Throwable cause) {
		super(code, message, cause);
	}
}

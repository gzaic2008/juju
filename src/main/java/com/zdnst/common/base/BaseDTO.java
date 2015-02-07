package com.zdnst.common.base;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public abstract class BaseDTO {

	public Object toVO(Object dest) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(dest, this);
		return dest;
	}
}

package com.zdnst.common.base;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class BaseVO {

	public Object toDTO(Object dest) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.copyProperties(dest, this);
		return dest;
	}
}

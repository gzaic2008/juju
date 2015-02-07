package com.zdnst.common.persistent;

import java.lang.reflect.Field;

public abstract class BaseDO {

	public String toString() {
		Field[] fields = this.getClass().getDeclaredFields();
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(this.getClass().getName());
		strBuf.append("(");
		for (int i = 0; i < fields.length; i++) {
			Field fd = fields[i];
			fd.setAccessible(true);
			strBuf.append(fd.getName() + ":");
			try {
				strBuf.append(fd.get(this));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (i != fields.length - 1)
				strBuf.append("|");
		}

		strBuf.append(")");
		return strBuf.toString();
	}

	 
}

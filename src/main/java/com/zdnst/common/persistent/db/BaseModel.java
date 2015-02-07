package com.zdnst.common.persistent.db;

import java.io.Serializable;
import java.lang.reflect.Field;

public abstract class BaseModel implements Serializable {
	private static final long serialVersionUID = -665036712667731957L;

	// /**
	// * 排序 升 降
	// */
	// private String order;
	// /**
	// * 排序字段
	// */
	// private String orderBy;
	// private String orderType;
	// /**
	// * 分页用当前页号
	// */
	// private Integer page = 1;
	// /**
	// * 分页用记录开始位置
	// */
	// private Integer startPos;
	// /**
	// * 分页用页面大小
	// */
	// private Integer pageSize = 20;
	//
	// /**
	// * 记录创建时间
	// */
	// private Date createTime;
	// /**
	// * 记录最后一次修改时间
	// */
	// private Date updateTime;
	// /**
	// * 创建人ID
	// */
	// private Integer creatorID;
	// /**
	// * 创建人用户名
	// */
	// private String creatorUserName;
	// /**
	// * 创建人姓名
	// */
	// private String creatorName;

	// public abstract Object getId();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		try {
			for (Field f : fields) {
				f.setAccessible(true);
				builder.append(f.getName() + "," + f.get(this));
			}
		} catch (Exception e) { // Suppress
			builder.append("toString builder encounter an error");
		}
		return builder.toString();
	}

	public void doNull() {
		Class<?> cla = this.getClass();
		Field fields[] = cla.getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				if (field.getType() == String.class) {
					if (field.get(this) == null) {
						field.set(this, "");
					}
				}
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// if (field.getType() == long.class) {
			// if (field.getLong(this)<0) {
			// field.setLong(this, Long.parseLong("0"));
			// }
			//
			// } else if (field.getType() == double.class) {
			// if (field.getDouble(this)<0) {
			// field.setDouble(this, Double.parseDouble("0"));
			// }
			// } else if (field.getType() == float.class) {
			// if (field.getDouble(this)<0) {
			// field.setDouble(this, Double.parseDouble("0"));
			// }
			//
			// } else if (field.getType() == int.class) {
			//
			// if (field.getInt(this)<0) {
			// field.setInt(this, Double.parseDouble("0"));
			// }
			// } else if (field.getType() == boolean.class) {
			// jsonStr += "\"" + field.getName() + "\":"
			// + field.getBoolean(obj) + ",";
			//
			// } else if (field.getType() == String.class) {
			// if (field.get(this)==null) {
			// field.setInt(this, Double.parseDouble("0"));
			// }
			//
			// } else if (field.getType() == List.class) {
			// String value = simpleListToJsonStr((List<?>) field.get(obj),
			// claList);
			// jsonStr += "\"" + field.getName() + "\":" + value + ",";
			// } else {
			// if (claList != null && claList.size() != 0
			// && claList.contains(field.getType())) {
			// String value = simpleObjectToJsonStr(field.get(obj),
			// claList);
			// jsonStr += "\"" + field.getName() + "\":" + value + ",";
			// } else {
			// jsonStr += "\"" + field.getName() + "\":null,";
			// }
			// }
		}
	}
}

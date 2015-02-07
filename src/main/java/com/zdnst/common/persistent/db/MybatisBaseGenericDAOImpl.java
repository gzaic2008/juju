package com.zdnst.common.persistent.db;

import java.io.Serializable;

import com.zdnst.common.utils.ReflectionUtils;

/**
 * 基于Mybatis的基础泛型DAO实现类。
 * 
 * @author pengzh
 * 
 * @param <T>
 *            业务实体类型
 * @param <ID>
 *            ID类型 ，如：String、Long、Integer 等
 */
public abstract class MybatisBaseGenericDAOImpl<T, PK extends Serializable> {

	public static final String SQLNAME_SEPARATOR = ".";

	public static final String SQL_SAVE = "save";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_GETBYID = "selectByPrimaryKey";
	public static final String SQL_DELETEBYID = "deleteById";
	public static final String SQL_DELETEBYIDS = "deleteByIds";
	public static final String SQL_FINDPAGEBY = "findPageBy";
	public static final String SQL_FINDLISTBY = "findListBy";
	public static final String SQL_GETCOUNTBY = "getCountBy";

	private static final String SORT_NAME = "SORT";

	private static final String DIR_NAME = "DIR";
	/** 不能用于SQL中的非法字符（主要用于排序字段名） */
	public static final String[] ILLEGAL_CHARS_FOR_SQL = { ",", ";", " ", "\"",
			"%" };

	/**
	 * 获取默认SqlMapping命名空间。 使用泛型参数中业务实体类型的全限定名作为默认的命名空间。
	 * 如果实际应用中需要特殊的命名空间，可由子类重写该方法实现自己的命名空间规则。
	 * 
	 * @return 返回命名空间字符串
	 */
	@SuppressWarnings("unchecked")
	protected String getDefaultSqlNamespace() {
		Class<T> clazz = ReflectionUtils.getClassGenricType(this.getClass());
		String nameSpace = clazz.getName();
		return nameSpace;
	}

	public abstract IGenericDao getDao();

	/**
	 * 将SqlMapping命名空间与给定的SqlMapping名组合在一起。
	 * 
	 * @param sqlName
	 *            SqlMapping名
	 * @return 组合了SqlMapping命名空间后的完整SqlMapping名
	 */
	protected String getSqlName(String sqlName) {
		return sqlNamespace + SQLNAME_SEPARATOR + sqlName;
	}

	/**
	 * SqlMapping命名空间
	 */
	private String sqlNamespace = getDefaultSqlNamespace();

	/**
	 * 获取SqlMapping命名空间
	 * 
	 * @return SqlMapping命名空间
	 */
	public String getSqlNamespace() {
		return sqlNamespace;
	}

	/**
	 * 设置SqlMapping命名空间。 此方法只用于注入SqlMapping命名空间，以改变默认的SqlMapping命名空间，
	 * 不能滥用此方法随意改变SqlMapping命名空间。
	 * 
	 * @param sqlNamespace
	 *            SqlMapping命名空间
	 */
	public void setSqlNamespace(String sqlNamespace) {
		this.sqlNamespace = sqlNamespace;
	}

	/**
	 * 生成主键值。 默认情况下什么也不做； 如果需要生成主键，需要由子类重写此方法根据需要的方式生成主键值。
	 * 
	 * @param ob
	 *            要持久化的对象
	 */
	protected void generateId(T ob) {

	}

	public T selectByPrimaryKey(PK id) {
		return (T) this.getDao().selectByPrimaryKey(id);
		// return (T) this.getSqlSession().selectOne(getSqlName(SQL_GETBYID),
		// id);
	}

}

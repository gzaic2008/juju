package com.zdnst.common.persistent;

import java.util.List;
import java.util.Map;

public interface IBaseDAO<T extends IDomainObject> {

	int insert(T record);

	//
	// int insertSelective(T record);

	T selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(T record);

	//
	int updateByPrimaryKey(T record);

	//
	int deleteByPrimaryKey(String id);

	// List<T> listPageByCondition(Map<String, Object> map);
	//
	// List<T> queryByCondition(Map<String, Object> map);
	//
	// T fetch(Map<String, Object> map);

}

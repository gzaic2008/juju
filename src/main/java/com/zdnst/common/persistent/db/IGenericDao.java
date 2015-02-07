package com.zdnst.common.persistent.db;

import java.io.Serializable;

/**
 * GenericDao DAO层泛型接口，定义基本的DAO功能
 * 
 * @author pzh
 * @since 0.1
 * @param <T>
 *            实体类
 * @param <PK>
 *            主键类，必须实现Serializable接口
 * 
 * @see
 */
public abstract interface IGenericDao<T, PK extends Serializable> {

	T selectByPrimaryKey(PK id);

}

package com.zdnst.maps.group.dao;

import com.zdnst.common.persistent.db.IGenericDao;
import com.zdnst.maps.group.entity.UrUser;

public interface IUserDao extends IGenericDao<UrUser, String> {

	public void saveUser(UrUser user);

	public UrUser getUser(String id);

}

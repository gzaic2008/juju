package com.zdnst.maps.group.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdnst.common.persistent.db.IGenericDao;
import com.zdnst.common.persistent.db.MybatisBaseGenericDAOImpl;
import com.zdnst.maps.group.dao.IUserDao;
import com.zdnst.maps.group.dao.impl.mybatis.UrUserMapper;
import com.zdnst.maps.group.entity.UrUser;

@Service("userDao")
public class UrUserDaoMbImpl extends MybatisBaseGenericDAOImpl<UrUser, String>
		implements IUserDao {

	@Autowired
	UrUserMapper urUserMapper;

	public IGenericDao getDao() {
		return this.urUserMapper;
	}

	// @Override
	// public int deleteByPrimaryKey(String userid) {
	// return urUserMapper.deleteByPrimaryKey(userid);
	// }
	//
	// @Override
	// public int insert(UrUser record) {
	// record.setCreateTime(CommonUtils.getCurTimestamp());
	// record.setUpdateTime(CommonUtils.getCurTimestamp());
	// record.setUserFlag("0");
	// record.setGroupFlag("0");
	// record.setDelFlag("0");
	// //
	// int result = urUserMapper.insert(record);
	// // initialization(record);
	// return result;
	// }

	// @Override
	// public int insertSelective(UrUser record) {
	// record.setCreateTime(CommonUtils.getCurTimestamp());
	// record.setUpdateTime(CommonUtils.getCurTimestamp());
	// record.setDelFlag("0");
	// record.setUserFlag("0");
	// record.setGroupFlag("0");
	//
	// int result = urUserMapper.insertSelective(record);
	// initialization(record);
	// return result;
	// }

	// @Override
	// public UrUser selectByPrimaryKey(String userid) {
	// return urUserMapper.selectByPrimaryKey(userid);
	// }

	// @Override
	// public int updateByPrimaryKeySelective(UrUser record) {
	// record.setUpdateTime(CommonUtils.getCurTimestamp());
	// record.setCreateTime(null);
	// return urUserMapper.updateByPrimaryKeySelective(record);
	// }
	//
	// @Override
	// public int updateByPrimaryKey(UrUser record) {
	// record.setUpdateTime(CommonUtils.getCurTimestamp());
	// return urUserMapper.updateByPrimaryKey(record);
	// }

	// @Override
	// public List<UrUser> listPageUrUsers(Map<String, Object> map) {
	// return null;
	// }
	//
	// @Override
	// public List<Map> getAllUrUserInfo(Map<String, Object> map) {
	// return urUserMapper.getAllUrUserInfo(map);
	// }
	//
	// @Override
	// public UrUser getOnlyUrUserInfo(Map<String, Object> map) {
	// return urUserMapper.getOnlyUrUserInfo(map);
	// }
	//
	// @Override
	// public List<UrUser> getUsersByMobiles(Map<String, Object> map) {
	// return urUserMapper.getUsersByMobiles(map);
	// }
	//
	// @Override
	// public List<Map> getUserDutyInfo(Map<String, Object> map) {
	// return urUserMapper.getUserDutyInfo(map);
	// }
	//
	// @Override
	// public List<Map> listPageUserBySerName(Map<String, Object> map) {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public List<Map> listPageFindUsers(Map<String, Object> map) {
	// // TODO Auto-generated method stub
	// return null;
	// }

}

package com.zdnst.maps.group.dao.impl;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

	@Autowired
	protected RedisTemplate<Serializable, Serializable> redisTemplate;

	public IGenericDao getDao() {
		return this.urUserMapper;
	}

	public void saveUser(final UrUser user) {

		// redisTemplate.delete(redisKey);
		// redisTemplate.opsForValue().set(redisKey, condition);
		// redisTemplate.expire(redisKey, 24 * 365, TimeUnit.HOURS);

		String redisKey = "validcode";

		redisTemplate.delete(redisKey);
		redisTemplate.opsForValue().set(redisKey, "4567");
		redisTemplate.expire(redisKey, 3, TimeUnit.MINUTES);

		// 保存到redis
		// redisTemplate.execute(new RedisCallback<Object>() {
		// @Override
		// public Object doInRedis(RedisConnection connection)
		// throws DataAccessException {
		// connection.set(
		// redisTemplate.getStringSerializer().serialize(
		// "user.uid." + user.getUserId()),
		// redisTemplate.getStringSerializer().serialize(
		// user.getUserName()));
		// return null;
		// }
		// });

	}

	public UrUser getUser(final String id) {
		String redisKey = "validcode";
//		String verifyResult = redisTemplate.opsForValue().get(
//				SETTING_VERIFY + pub_openId + "_" + userident);// 从redis当中获取权限校验结果
//		Object querySettings = redisTemplate.opsForHash().get("usersettings_",
//				pub_openId + userident + UserSettingType.QUERY);// 从reidis当中获取用户设置的有效期
//		
		 
		
		String verifyResult = redisTemplate.opsForValue().get(redisKey,0,1);// 从redis当中获取权限校验结果
		
		System.out.println(verifyResult);
		
//		Object querySettings = redisTemplate.opsForHash().get("usersettings_",
//				pub_openId + userident + UserSettingType.QUERY);// 从reidis当中获取用户设置的有效期
		

//		return redisTemplate.execute(new RedisCallback<UrUser>() {
//			@Override
//			public UrUser doInRedis(RedisConnection connection)
//					throws DataAccessException {
//				byte[] key = redisTemplate.getStringSerializer().serialize(
//						"user.uid." + id);
//				if (connection.exists(key)) {
//					byte[] value = connection.get(key);
//					String name = redisTemplate.getStringSerializer()
//							.deserialize(value);
//					UrUser user = new UrUser();
//					user.setUserName(name);
//					user.setUserId(id);
//					return user;
//				}
//				return null;
//			}
//		});

		return null;
		
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

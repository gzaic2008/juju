package com.zdnst.maps.flow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.zdnst.common.persistent.mongodb.MongoDBManager;
import com.zdnst.maps.AbstractTestCase;
import com.zdnst.maps.group.dao.IUserDao;
import com.zdnst.maps.group.entity.UrUser;

public class FlowService extends AbstractTestCase {

	// @Resource
	// private TnFlowTaskHisDAO flowTaskHisDao;
	//
	// // @Resource
	// // private AppOrderDAO appOrderDao;
	//
	// // @Resource
	// // private AtActionDao atActionDao;
	//
	// @Resource
	// //IZdFlowworktaskService zdTaskInstanceService;
	//
	// @Resource
	// //TnFlowDefTypeDAO flowdeftypedao;

	@Resource
	IUserDao userDao;

	// @Test
	public void testGetTaskDetail() {
		System.out.println("--------单元测试111---------");
		// TnFlowTaskHis t = flowTaskHisDao.selectByPrimaryKey("3333");
		// System.out.println(t);

		System.out.println("--------单元测试 end---------");

	}

	public static String toJSONString(Object object,
			SerializerFeature... features) {
		SerializeWriter out = new SerializeWriter();
		String s;
		JSONSerializer serializer = new JSONSerializer(out);
		SerializerFeature arr$[] = features;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			SerializerFeature feature = arr$[i$];
			serializer.config(feature, true);
		}

		serializer.getValueFilters().add(new ValueFilter() {
			public Object process(Object obj, String field, Object value) {
				if (null != value) {
					if (value instanceof java.util.Date) {
						return String.format("%1$tF %1tT", value);
					}
					return value;
				} else {
					String fieldType = "";
					try {
						fieldType = obj.getClass().getDeclaredField(field)
								.getGenericType().toString();

					} catch (NoSuchFieldException e) {
						// TODO Auto-zdnst catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-zdnst catch block
						e.printStackTrace();
					}

					if (fieldType.equals("class java.lang.String")) {
						return "";
					}
					return null;
				}
			}
		});
		serializer.write(object);
		s = out.toString();
		out.close();
		return s;
	}

	@Test
	// @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void testAddOrder() {

		System.out.println("--------单元测试 testAddOrder---------");

		Map para = new HashMap();
		para.put("condition", "parentid is null");

		String id = "3E574A77-669A-403B-ABA8-B031943A7AC0";
		// id = "55555";
		// UrUser uo1 = userDao.selectByPrimaryKey(id);

		UrUser uo = new UrUser();
		uo.setUserId("00000");
		uo.setUserName("test");

		// userDao.insert(uo);

		// System.out.println(uo1);

		//
		// ConvertUtils.register(new Converter() {
		// public Object convert(Class type, Object value) {
		// if (value == null)
		// return "";
		// return value;
		//
		// }
		// }, String.class);

		Person p = new Person();
		// p.setBirthday(null);
		Map map = new HashMap();
		map.put("birthday", "test");
		map.put("name", "null");
		
		
		

		// class filter extends ValueFilter {
		// @Override
		// public Object process(Object obj, String s, Object v) {
		// if (v == null)
		// return "";
		// return v;
		// }
		// }

		// SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
		//
		// ValueFilter filter = new ValueFilter(){
		//
		// };
		//
		// com.alibaba.fastjson.serializer.ValueFilter
		//

		// SerializeWriter out = new SerializeWriter();
		// JSONSerializer serializer = new JSONSerializer(out);
		// ValueFilter filter = new ValueFilter() {// 该过滤器，让value的ImageIcon值变成了
		// // 表达文件地址的字符串值。
		// public Object process(Object source, String name, Object value) {
		// if (value==null) {
		// return "";
		// }
		// return value;
		// }
		//
		// };
		// String s = JSON.toJSONString(p, filter,
		// SerializerFeature.PrettyFormat,
		// SerializerFeature.WriteClassName);
		//
		// s = JSON.toJSONString(p, filter );
		//
		// String ss = JSON.toJSONString(map,
		// SerializerFeature.WriteNullStringAsEmpty);
		//
		// // String ss = JSON.toJSONString(map);

		String s = toJSONString(p);

		// System.out.println("tojson:" + s);

		String collName = "maps_group_audit";

		Map user = new HashMap();
		user.put("id", "git3");

		// user.put("name", "快速打开");

		// MongoDBManager.getInstance().add(user, collName);

		// MongoDBManager.getInstance().

		// MongoDBManager.getInstance().getAllCollections();

		// DB db = MongoDBManager.getInstance().getDb();
		//
		// BasicDBObject query = new BasicDBObject();
		// query.put("id", "git");
		// DBCursor cursor =
		// MongoDBManager.getInstance().getCollection(collName)
		// .find(query);
		//
		// try {
		// while (cursor.hasNext()) {
		//
		//
		// System.out.println(cursor.next());
		// }
		// } finally {
		// cursor.close();
		// }

		// DBObject data = MongoDBManager.getInstance().findOne(map, collName);

		// DBObject data =
		// MongoDBManager.getInstance().findById("54c1a826926a7140d7090278",
		// collName);

		try {
			// List data = MongoDBManager.getInstance().findPage(collName,
			// user,0,2);
			// System.out.println(data);

			Map user1 = new HashMap();

			user1.put("keyid", "keyid");
			user1.put("objid", "testobj");
			user1.put("objname", "git3");

			Person p1 = new Person();
			p1.setName("qiye");
			
			List<Person> list1 = new ArrayList<Person>();
			list1.add(p1);
			
			user1.put("list", list1);
			

			// MongoDBManager.getInstance().update(user, user1, collName);
			// MongoDBManager.getInstance().deleteById("54c1a8e7926a7bd452c88837",
			// collName);

			
			
			MongoDBManager.getInstance().add(user1, collName);

		} catch (Exception e) {
			// TODO Auto-zdnst catch block
			e.printStackTrace();
		}

		// System.out.println( );

		// System.out.println( db.getCollection(collName).getCount());

		// ConvertUtilsBean convertUtils = new ConvertUtilsBean();
		// convertUtils.register(new Converter() {
		// public Object convert(Class type, Object value) {
		// if (value == null)
		// return "";
		// return value;
		//
		// }
		// }, String.class);
		//
		// BeanUtilsBean beanUtils = new BeanUtilsBean(convertUtils,
		// new PropertyUtilsBean());
		//
		// try {
		//
		// beanUtils.populate(p, map);
		// //BeanUtils.populate(p, map);
		//
		// } catch (IllegalAccessException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (InvocationTargetException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// System.out.println(p);

		// List list = flowdeftypedao.getListByCondition(para);

		// TnFlowDefType tt = flowdeftypedao.selectByPrimaryKey("11");
		// System.out.println("TnFlowDefType==========" + tt);

		// System.out.println("list==========" + list);

		// AppOrder order = new AppOrder();
		// order.setOrderId("3333");
		// order.setCeoUser("累死了分手快乐解放路");
		//
		// try {
		// appOrderDao.insert(order);
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		System.out.println("--------单元测试  testAddOrder end---------");
	}

}

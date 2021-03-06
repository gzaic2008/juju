package com.zdnst.common.persistent;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;
import com.zdnst.common.utils.SystemProperties;

public class MongoDBManager {
	private static Logger log = Logger.getLogger(MongoDBManager.class);

	private static String mongoDBHost = "192.168.1.63";
	private static int mongoDBPort = 27017;
	private static String dbName = "juhui3";

	private static String dbUser = "";
	private static String dbPwd = "";

	private static String collName = "maps";

	private static MongoDBManager instance;

	private DB db;

	private MongoDBManager() {

		MongoClient mongoClient;
		try {

			mongoDBHost = SystemProperties.getProperties().getProperty(
					"mongdb.host");
			mongoDBPort = Integer.parseInt(SystemProperties.getProperties()
					.getProperty("mongdb.port"));
			dbName = SystemProperties.getProperties().getProperty("mongdb.db");
			dbUser = SystemProperties.getProperties()
					.getProperty("mongdb.user");
			dbPwd = SystemProperties.getProperties().getProperty(
					"mongdb.password");

			ServerAddress addr = new ServerAddress(mongoDBHost, mongoDBPort);

			MongoClientOptions options = new MongoClientOptions.Builder()
					.socketKeepAlive(true).socketTimeout(0).build();

			mongoClient = new MongoClient(addr, options);
			// 连接到数据库
			db = mongoClient.getDB(dbName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public static MongoDBManager getInstance() {
		if (instance == null) {
			instance = new MongoDBManager();
		}
		return instance;
	}

	public DB getDb() {
		return this.db;
	}

	public void insert4JsonStr(String json) throws Exception {

		DBCollection collection = getDb().getCollection(collName);
		json = "{'database' : 'mkyongDB','table' : 'hosting','detail' : {'records' : 99, 'index' : 'vps_index1', 'active' : 'true'}}}";
		DBObject dbObject = (DBObject) JSON.parse(json);

		collection.insert(dbObject);

	}

	public void insert4Map(String json) {

	}

	/**
	 * 获取集合（表）
	 * 
	 * @param collection
	 */
	public static DBCollection getCollection(String collection) {
		return getInstance().getDb().getCollection(collection);
	}

//	/**
//	 * ----------------------------------分割线------------------------------------
//	 * --
//	 */
//	/**
//	 * 插入
//	 * 
//	 * @param collection
//	 * @param map
//	 */
//	public void insert(String collection, Map<String, Object> map) {
//		try {
//			DBObject dbObject = map2Obj(map);
//			getCollection(collection).insert(dbObject);
//		} catch (MongoException e) {
//			log.error("MongoException:" + e.getMessage());
//		}
//	}
//
//	/**
//	 * 批量插入
//	 * 
//	 * @param collection
//	 * @param list
//	 */
//	public void insertBatch(String collection, List<Map<String, Object>> list) {
//		if (list == null || list.isEmpty()) {
//			return;
//		}
//		try {
//			List<DBObject> listDB = new ArrayList<DBObject>();
//			for (int i = 0; i < list.size(); i++) {
//				DBObject dbObject = map2Obj(list.get(i));
//				listDB.add(dbObject);
//			}
//			getCollection(collection).insert(listDB);
//		} catch (MongoException e) {
//			log.error("MongoException:" + e.getMessage());
//		}
//	}
//
//	/**
//	 * 删除
//	 * 
//	 * @param collection
//	 * @param map
//	 */
//	public void delete(String collection, Map<String, Object> map) {
//		DBObject obj = map2Obj(map);
//		getCollection(collection).remove(obj);
//	}
//
//	/**
//	 * 删除全部
//	 * 
//	 * @param collection
//	 * @param map
//	 */
//	public void deleteAll(String collection) {
//		List<DBObject> rs = findAll(collection);
//		if (rs != null && !rs.isEmpty()) {
//			for (int i = 0; i < rs.size(); i++) {
//				getCollection(collection).remove(rs.get(i));
//			}
//		}
//	}
//
//	/**
//	 * 批量删除
//	 * 
//	 * @param collection
//	 * @param list
//	 */
//	public void deleteBatch(String collection, List<Map<String, Object>> list) {
//		if (list == null || list.isEmpty()) {
//			return;
//		}
//		for (int i = 0; i < list.size(); i++) {
//			getCollection(collection).remove(map2Obj(list.get(i)));
//		}
//	}
//
//	/**
//	 * 计算满足条件条数
//	 * 
//	 * @param collection
//	 * @param map
//	 */
//	public long getCount(String collection, Map<String, Object> map) {
//		return getCollection(collection).getCount(map2Obj(map));
//	}
//
//	/**
//	 * 计算集合总条数
//	 * 
//	 * @param collection
//	 * @param map
//	 */
//	public long getCount(String collection) {
//		return getCollection(collection).find().count();
//	}
//
//	/**
//	 * 更新
//	 * 
//	 * @param collection
//	 * @param setFields
//	 * @param whereFields
//	 */
//	public void update(String collection, Map<String, Object> setFields,
//			Map<String, Object> whereFields) {
//		//DBObject obj1 = map2Obj(setFields);
//		//DBObject obj2 = map2Obj(whereFields);
//		//getCollection(collection).updateMulti(obj1, obj2);
//	}
//
//	/**
//	 * 查找对象（根据主键_id）
//	 * 
//	 * @param collection
//	 * @param _id
//	 */
//	public DBObject findById(String collection, String _id) {
//		DBObject obj = new BasicDBObject();
//		obj.put("_id", ObjectId.massageToObjectId(_id));
//		return getCollection(collection).findOne(obj);
//	}
//
//	/**
//	 * 查找集合所有对象
//	 * 
//	 * @param collection
//	 */
//	public List<DBObject> findAll(String collection) {
//		return getCollection(collection).find().toArray();
//	}
//
//	/**
//	 * 查找（返回一个对象）
//	 * 
//	 * @param map
//	 * @param collection
//	 */
//	public DBObject findOne(String collection, Map<String, Object> map) {
//		DBCollection coll = getCollection(collection);
//		return coll.findOne(map2Obj(map));
//	}
//
//	/**
//	 * 查找（返回一个List<DBObject>）
//	 * 
//	 * @param <DBObject>
//	 * @param map
//	 * @param collection
//	 * @throws Exception
//	 */
//	public List<DBObject> find(String collection, Map<String, Object> map)
//			throws Exception {
//		DBCollection coll = getCollection(collection);
//		DBCursor c = coll.find(map2Obj(map));
//		if (c != null)
//			return c.toArray();
//		else
//			return null;
//	}

}

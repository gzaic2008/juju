package com.zdnst.common.persistent.mongodb;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bson.types.BasicBSONList;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
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
	private static DB db;

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

			// MongoOptions options = new MongoOptions();
			// options.autoConnectRetry = true;l
			// options.connectionsPerHost = 1000;
			// options.maxWaitTime = 5000;
			// options.socketTimeout = 0;
			// options.connectTimeout = 15000;
			// options.threadsAllowedToBlockForConnectionMultiplier = 5000;
			// 事实上，Mongo实例代表了一个数据库连接池，即使在多线程的环境中，一个Mongo实例对我们来说已经足够了
			// mongo = new Mongo(new ServerAddress(DBMongoConfig.getHost(),
			// DBMongoConfig.getPort()), options);
			// mongo = new
			// Mongo(DBMongoConfig.getHost(),DBMongoConfig.getPort());
			// or, to connect to a replica set, supply a seed list of members
			// Mongo m = new Mongo(Arrays.asList(new ServerAddress("localhost",
			// 27017),
			// new ServerAddress("localhost", 27018),
			// new ServerAddress("localhost", 27019)));

			// 注意Mongo已经实现了连接池，并且是线程安全的。
			// 大部分用户使用mongodb都在安全内网下，但如果将mongodb设为安全验证模式，就需要在客户端提供用户名和密码：
			// boolean auth = db.authenticate(myUserName, myPassword);

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

	/**
	 * 添加操作
	 * 
	 * 
	 * @param map
	 * @param collectionName
	 */
	public static void add(Map<String, Object> map, String collectionName)
			throws Exception {
		DBObject dbObject = new BasicDBObject(map);
		getCollection(collectionName).insert(dbObject);
	}

	/**
	 * 添加操作
	 * 
	 * 
	 * @param list
	 * @param collectionName
	 */
	public static void add(List<Map<String, Object>> list, String collectionName)
			throws Exception {
		for (Map<String, Object> map : list) {
			add(map, collectionName);
		}
	}

	/**
	 * 删除操作
	 * 
	 * 
	 * @param map
	 * @param collectionName
	 */
	public static void delete(Map<String, Object> map, String collectionName)
			throws Exception {
		DBObject dbObject = new BasicDBObject(map);
		getCollection(collectionName).remove(dbObject);
	}

	/**
	 * 删除操作,根据主键
	 * 
	 * 
	 * @param id
	 * @param collectionName
	 */
	public static void deleteById(String id, String collectionName)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("_id", new ObjectId(id));
		delete(map, collectionName);
	}

	/**
	 * 删除全部
	 * 
	 * 
	 * @param collectionName
	 */
	public static void deleteAll(String collectionName) throws Exception {
		getCollection(collectionName).drop();
	}

	/**
	 * 修改操作</br> 会用一个新文档替换现有文档,文档key结构会发生改变</br>
	 * 比如原文档{"_id":"123","name":"zhangsan","age":12}当根据_id修改age
	 * value为{"age":12}新建的文档name值会没有,结构发生了改变 <br>
	 * ------------------------------<br>
	 * 
	 * @param whereMap
	 * @param valueMap
	 * @param collectionName
	 */
	public static void update(Map<String, Object> whereMap,
			Map<String, Object> valueMap, String collectionName)
			throws Exception {
		executeUpdate(collectionName, whereMap, valueMap, new UpdateCallback() {
			public DBObject doCallback(DBObject valueDBObject) {
				return valueDBObject;
			}
		});
	}

	/**
	 * 修改操作,使用$set修改器</br> 用来指定一个键值,如果键不存在,则自动创建,会更新原来文档, 不会生成新的, 结构不会发生改变 <br>
	 * 
	 * 
	 * @param whereMap
	 * @param valueMap
	 * @param collectionName
	 */
	public static void updateSet(Map<String, Object> whereMap,
			Map<String, Object> valueMap, String collectionName)
			throws Exception {
		executeUpdate(collectionName, whereMap, valueMap, new UpdateCallback() {
			public DBObject doCallback(DBObject valueDBObject) {
				return new BasicDBObject("$set", valueDBObject);
			}
		});
	}

	/**
	 * 修改操作,使用$inc修改器</br> 修改器键的值必须为数字</br> 如果键存在增加或减少键的值, 如果不存在创建键 <br>
	 * -
	 * 
	 * @param whereMap
	 * @param valueMap
	 * @param collectionName
	 */
	public static void updateInc(Map<String, Object> whereMap,
			Map<String, Integer> valueMap, String collectionName)
			throws Exception {
		executeUpdate(collectionName, whereMap, valueMap, new UpdateCallback() {
			public DBObject doCallback(DBObject valueDBObject) {
				return new BasicDBObject("$inc", valueDBObject);
			}
		});
	}

	/**
	 * 修改
	 * 
	 * 
	 * @param collectionName
	 * @param whereMap
	 * @param valueMap
	 * @param updateCallback
	 */
	private static void executeUpdate(String collectionName, Map whereMap,
			Map valueMap, UpdateCallback updateCallback) throws Exception {
		DBObject whereDBObject = new BasicDBObject(whereMap);
		DBObject valueDBObject = new BasicDBObject(valueMap);
		valueDBObject = updateCallback.doCallback(valueDBObject);
		getCollection(collectionName).update(whereDBObject, valueDBObject);
	}

	interface UpdateCallback {

		DBObject doCallback(DBObject valueDBObject);
	}

	/**
	 * 获取集合(表) <br>
	 * ------------------------------<br>
	 * 
	 * @param collectionName
	 * @return
	 */
	public static DBCollection getCollection(String collectionName)
			throws Exception {
		return db.getCollection(collectionName);
	}

	/**
	 * 
	 * @describe：获取所有的集合
	 * @author:zhihong.peng
	 * @time:Jan 23, 2015 9:36:25 AM
	 */
	public static Set<String> getAllCollections() throws Exception {
		// Set<String> colls = db.getCollectionNames();
		return db.getCollectionNames();

	}

	/**
	 * 查询单个,按主键查询
	 * 
	 * 
	 * @param id
	 * @param collectionName
	 */
	public static DBObject findById(String id, String collectionName)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("_id", new ObjectId(id));
		return findOne(map, collectionName);
	}

	/**
	 * 查找（返回一个List<DBObject>）
	 * 
	 * @param <DBObject>
	 * @param map
	 * @param collection
	 * @throws Exception
	 */
	public static List<DBObject> find(String collection, Map<String, Object> map)
			throws Exception {
		DBCollection coll = getCollection(collection);
		BasicDBObject query = (BasicDBObject) getMapped(map);
		DBCursor cursor = coll.find(query);
		List<DBObject> data = null;
		try {

			if (cursor != null)
				data = cursor.toArray();
		} catch (Exception e) {
			throw e;
		} finally {
			cursor.close();
		}
		return data;
	}

	/**
	 * 查找（返回一个List<DBObject>）
	 * 
	 * @param <DBObject>
	 * @param map
	 * @param collection
	 * @throws Exception
	 */
	public static List<DBObject> find(String collection, BasicDBObject query)
			throws Exception {
		DBCollection coll = getCollection(collection);
		DBCursor cursor = coll.find(query);
		List<DBObject> data = null;
		try {
			if (cursor != null)
				data = cursor.toArray();
		} catch (Exception e) {
			throw e;
		} finally {
			cursor.close();
		}
		return data;
	}

	/**
	 * 查找（返回一个List<DBObject>）
	 * 
	 * @param <DBObject>
	 * @param map
	 * @param collection
	 * @throws Exception
	 */
	public static List<DBObject> findPage(String collection,
			Map<String, Object> map, int start, int limit) throws Exception {
		DBCollection coll = getCollection(collection);
		BasicDBObject query = (BasicDBObject) getMapped(map);
		DBCursor cursor = coll.find(query).skip(start).limit(limit);
		;
		List<DBObject> data = null;
		try {

			if (cursor != null)
				data = cursor.toArray();
		} catch (Exception e) {
			throw e;
		} finally {
			cursor.close();
		}
		return data;
	}

	/**
	 * 查找（返回一个List<DBObject>）
	 * 
	 * @param <DBObject>
	 * @param map
	 * @param collection
	 * @throws Exception
	 */
	public static List<DBObject> findPage(String collection,
			BasicDBObject query, int start, int limit) throws Exception {
		DBCollection coll = getCollection(collection);
		DBCursor cursor = coll.find(query).skip(start).limit(limit);
		List<DBObject> data = null;
		try {
			if (cursor != null)
				data = cursor.toArray();
		} catch (Exception e) {
			throw e;
		} finally {
			cursor.close();
		}
		return data;
	}

	/**
	 * 查询单个
	 * 
	 * 
	 * @param map
	 * @param collectionName
	 */
	public static DBObject findOne(Map<String, Object> map,
			String collectionName) throws Exception {
		DBObject dbObject = getMapped(map);
		DBObject object = getCollection(collectionName).findOne(dbObject);
		return object;
	}

	/**
	 * 
	 * 
	 * 
	 * 
	 * @param map
	 * @return
	 */
	private static DBObject getMapped(Map<String, Object> map) {
		DBObject dbObject = new BasicDBObject();
		Iterator<Entry<String, Object>> iterable = map.entrySet().iterator();
		while (iterable.hasNext()) {
			Entry<String, Object> entry = iterable.next();
			Object value = entry.getValue();
			String key = entry.getKey();
			if (key.startsWith("$") && value instanceof Map) {
				BasicBSONList basicBSONList = new BasicBSONList();
				Map<String, Object> conditionsMap = ((Map) value);
				Set<String> keys = conditionsMap.keySet();
				for (String k : keys) {
					Object conditionsValue = conditionsMap.get(k);
					if (conditionsValue instanceof Collection) {
						conditionsValue = convertArray(conditionsValue);
					}
					DBObject dbObject2 = new BasicDBObject(k, conditionsValue);
					basicBSONList.add(dbObject2);
				}
				value = basicBSONList;
			} else if (value instanceof Collection) {
				value = convertArray(value);
			} else if (!key.startsWith("$") && value instanceof Map) {
				value = getMapped(((Map) value));
			}
			dbObject.put(key, value);
		}

		return dbObject;
	}

	/**
	 * 判断集合是否存在
	 * 
	 * 
	 * @param collectionName
	 * @return
	 */
	public static boolean collectionExists(String collectionName) {
		return db.collectionExists(collectionName);
	}

	private static Object[] convertArray(Object value) {
		Object[] values = ((Collection) value).toArray();
		return values;
	}
	//
	// private static void print(DBObject object) {
	// Set<String> keySet = object.keySet();
	// for (String key : keySet) {
	// // print(object.get(key));
	// }
	// }

}

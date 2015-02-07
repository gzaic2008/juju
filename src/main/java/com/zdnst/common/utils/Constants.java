package com.zdnst.common.utils;

public class Constants {

	public final static String DS_VERSION = "1.1.1";//公司规定版本号
	
	public final static String APP_VERSION = "0.2";//后台网站、接口版本号
	
	public final static String HASH = "hash";// 传入必须参数码表值
	public final static String ID = "id";// 传入必须参数码表值
	public final static String SEND_MSG_SUCCESS = "000/";// 传入必须参数码表值

	public final static String OPERATE_TYPE_ADD = "0";
	public final static String OPERATE_TYPE_UPDATE = "1";
	public final static String OPERATE_TYPE_DELETE = "2";

	public final static String RESULT_FAILED = "0";
	public final static String RESULT_SUCCESS = "1";

	public final static String USER_INFO_SESSION = "userSessionInfo";

	/**
	 * 中国国家代码
	 */
	public final static String SYS_COUNTRY_CODE_ZG = "86";

	/**
	 * 默认地区代码(广东省)
	 */
	public final static String SYS_DEFAULT_AREA_NAME = "广东省";
	/**
	 * 默认城市代码(广东省)
	 */
	public final static String SYS_DEFAULT_AREA_CODE = "440000";

	/**
	 * 默认城市代码(广州)
	 */
	public final static String SYS_DEFAULT_CITY_NAME = "广州";

	/**
	 * 默认城市代码(广州)
	 */
	public final static String SYS_DEFAULT_CITY_CODE = "440100";

	/**
	 * 默认城市天气代码(广州)
	 */
	public final static String SYS_DEFAULT_CITY_WEATHER_CODE = "101280101";

	/**
	 * 默认区县代码(广州)
	 */
	public final static String SYS_DEFAULT_DISTRICT_NAME = "天河区";

	/**
	 * 默认区县代码(天河区)
	 */
	public final static String SYS_DEFAULT_DISTRICT_CODE = "440106";

	/**
	 * 热门城市
	 */
	public final static String[] SYS_HOTCITY_CODES = { "440100", "310000",
			"110000","440300", "330200" };// 广州、上海、北京、深圳、宁波

	public final static String DEAULT_TAGTYPE = "100100";// 默认创建活动的时候不传标签类型时设置值
	public final static String DEAULT_TAGNAME = "聚餐";// 默认创建活动的时候不传标签名称时设置值

	// 系统默认参数
	public final static String CONFIG_DEPLOY = "config_deploy";// 发布配置
	public final static String SYS_SETTING = "sys_setting";// 系统配置键值
	public final static String SYS_USR_SEQ = "USR_SEQ";// 用户随机键值
	public final static String SYS_DEFAULT_CARD_TASK = "default";// 获取默认卡片任务列表值
	public final static String PHP_SERVER = "PHP_SERVER";// PHP服务地址
	public final static String JAVA_SERVER = "JAVA_SERVER";// JAVA服务地址
	public final static String IM_SERVER = "IM_SERVER";// IM服务地址
	public final static String IM_PORT = "IM_PORT";// IM端口
	public final static String DEAULT_IPAD_TAG = "DEAULT_IPAD_TAG";// 默认ipad标签类型
	public final static String DEAULT_MOBILE_TAG = "DEAULT_MOBILE_TAG";// 默认手机标签类型
	public final static String DEAULT_FREE_WATCH_COUNT = "DEAULT_FREE_WATCH_COUNT";// 随便看看前面固定显示数量
	public final static String VOTE_AUTO_END_TIME = "VOTE_AUTO_END_TIME";// 投票自动结束时间，单位分钟，0为即时
	public final static String ACTION_AUTO_RUNNING_TIME = "ACTION_AUTO_RUNNING_TIME";// 活动自动从未开始进入进行中时间,单位分钟，0为即时
	public final static String ACTION_AUTO_END_TIME = "ACTION_AUTO_END_TIME";// 活动自动结束天数，单位天，0为即时
	public final static String DEAULT_START_PAGE_IMAGE = "DEAULT_START_PAGE_IMAGE";// 启动页默认图片名称
	public final static String CURRENT_IPAD_VERSION = "CURRENT_IPAD_VERSION";// IPAD当前版本号
	public final static String CURRENT_IPAD_VERSION_URL = "CURRENT_IPAD_VERSION_URL";// IPAD当前版本下载地址
	public final static String CURRENT_IPAD_VERSION_CONTENT = "CURRENT_IPAD_VERSION_CONTENT";// IPAD当前版本说明
	public final static String CURRENT_IPHONE_VERSION = "CURRENT_IPHONE_VERSION";// IPHONE当前版本号
	public final static String CURRENT_IPHONE_VERSION_URL = "CURRENT_IPHONE_VERSION_URL";// IPHONE当前版本下载地址
	public final static String CURRENT_IPHONE_VERSION_CONTENT = "CURRENT_IPHONE_VERSION_CONTENT";// IPHONE当前版本说明
	public final static String CURRENT_ANDROID_VERSION = "CURRENT_ANDROID_VERSION";// ANDROID当前版本号
	public final static String CURRENT_ANDROID_VERSION_URL = "CURRENT_ANDROID_VERSION_URL";// ANDROID当前版本下载地址
	public final static String CURRENT_ANDROID_VERSION_CONTENT = "CURRENT_ANDROID_VERSION_CONTENT";// ANDROID当前版本说明
	public final static String IM_WEB_SERVICE_URL = "IM_WEB_SERVICE_URL";// IM
																			// web服务
	public final static String UPLOAD_IMAGE_URL = "UPLOAD_IMAGE_URL";// 上传图片url
	public final static String SYS_TAG_TASK_DEPLOY = "SYS_TAG_TASK_DEPLOY";// 任务关系发布配置

	// 后台常量部分
	public final static String normalTagType = "1";// 普通标签类型
	public final static String superTagType = "2";// 高级标签类型
	public final static String verfyEmailUrl = "api/public/common/verifyEmail&hash=";// 邮箱验证地址常量
	public final static String emailActivation="emailActivation";//邮箱激活地址
	public final static String actionDetailUrl = "index.php?route=Index/actionPageIndex&aid=";// 活动详情外部访问链接
	public final static String downloadImageUrl = "files/";// 下载图片URL
	public final static String downloadAppWebUrl = "index.php?route=Index/downloadIndex";// 外网扫描二维码链接
	public final static String helpUrl = "index.php?route=Index/helpIndex";// 帮助外部访问链接
	
	//请求浏览器常量
	public final static int SYS_IOS_TYPE=4;//ios
	public final static int SYS_ANDROID_TYPE=2;//android
	public final static int SYS_BROWSER_TYPE=1;//浏览器
	
	public final static String ACTION_COVERER_IMG_KEY = "actionCoverImg";//活动封面key
	
	public final static String VOTE_IMG_KEY = "voteKey";//投票项图片key
	
	public final static String SYS_USER="10000";//后台系统操作用户
	
}

/**
 * Copyright(C) 2012-2014 GuangZhou zdnst Co., Ltd. All Rights Reserved.<br/>
 * 版权所有(C)2012-2014 <br/>
 * 公司名称：广州支点科技有限公司<br/>
 * 公司地址：广州市天河区天源路401号之三E2栋<br/>
 * 网址：http://www.100100system.com/<br/>
 * <p>标        题：</p>
 * <p>文  件  名：com.zdnst.common.base.SysMsgConstants.java</p>
 * <p>部        门：研发一部
 * <p>版        本： 1.0</p>
 * <p>Compiler: JDK1.6.0_21</p>
 * <p>创  建  者：yongqin.zhong</p>
 * <p>创建时间：Nov 2, 20145:18:44 PM</p>
 * <p>修  改  者：</p>
 * <p>修改时间：</p>
 */
package com.zdnst.common.base;

public class SysMsgConstants {
	
	//手机
	
	
	/**
	 * 邀请注册{0}:初始密码，{1}下载链接
	 */
	public final static String SYSMSG_INVITE_REGISTER_MSG="您已经成功成为聚聚+用户，初始密码为：{0},聚聚+下载地址：{1}";
	
	/**
	 * 注册验证码：1111 ，30分钟内有效{0}:验证码
	 */
	public final static String SYSMSG_REGISTER_MSG="注册验证码：{0}，30分钟内有效";
	
	/**
	 * 验证码：1111 ，30分钟内有效{0}:验证码
	 */
	public final static String SYSMSG_FORGET_PWD_MSG="验证码：{0}，30分钟内有效";
	
	/**
	 * 邀请通讯录圈子成员提示{0}:好友用户昵称，{1}:好友手机,{2}：圈子名称,{3}：链接地址+邀请码
	 */
	public final static String SYSMSG_INVITE_ADDRESSBOOK_GROUP_MSG="您的好友“{0}”（手机{1}）邀请你加入圈子：“{2}”，点击查看：{3}";

	/**
	 * 邀请通讯录活动成员提示{0}:好友用户昵称，{1}:好友手机,{2}：活动名称,{3}：链接地址+邀请码
	 */
	public final static String SYSMSG_INVITE_ADDRESSBOOK_ACTION_MSG="您的好友“{0}”（手机{1}）邀请你参加活动：“{2}”，点击查看：{3}";

	/**
	 * 邀请添加通讯录好友提示{0}:好友用户昵称，{1}:好友手机,{2}：链接地址+邀请码
	 */
	public final static String SYSMSG_INVITE_ADDRESSBOOK_ADD_FRIEND_MSG="您的好友“{0}”（手机{1}）邀请你一起聚聚，点击查看：{2}";
	
	/**
	 * 分享通讯录圈子提示{0}:圈子名称，{1}:链接地址+邀请码
	 */
	public final static String SYSMSG_SHARE_ADDRESSBOOK_GROUP_MSG="推荐有趣的圈子“{0}”，你也来看看吧：{1}";
	
	/**
	 * 分享通讯录活动提示{0}:活动名称，{1}：链接地址+邀请码
	 */
	public final static String SYSMSG_SHARE_ADDRESSBOOK_ACTION_MSG="推荐一个精彩活动“{0}”，你也来看看吧：{1}";
	
	
	//普通
	/**
	 * 邀请圈子成员提示{0}:我,{1}圈子名称，{2}:链接地址+邀请码
	 */
	public final static String SYSMSG_INVITE_GROUP_MSG="{0}正在聚聚圈子“{1}”，邀请你一起加入，点击查看：{2}";

	/**
	 * 邀请活动成员提示{0}:我,{1}:活动名称，{2}:链接地址+邀请码
	 */
	public final static String SYSMSG_INVITE_ACTION_MSG="{0}参加了活动“{1}”，你也一起来吧：{2}";

	/**
	 * 邀请添加好友提示{0}:我,{1}:链接地址+邀请码
	 */
	public final static String SYSMSG_INVITE_ADD_FRIEND_MSG="{0}在使用聚聚APP，你也一起来聚聚吧：{1}";
	
	/**
	 * 分享圈子提示{0}:圈子名称，{1}:链接地址+邀请码
	 */
	public final static String SYSMSG_SHARE_GROUP_MSG="推荐有趣的圈子“{0}”，你也来看看吧：{1}";
	
	/**
	 * 分享活动提示{0}:活动名称，{1}：链接地址+邀请码
	 */
	public final static String SYSMSG_SHARE_ACTION_MSG="推荐一个精彩活动“{0}”，你也来看看吧：{1}";
	
	
	/**
	 * 合作伙伴邀请{0}:企业名称，{1}：链接地址+邀请码
	 */
	public final static String SYSMSG_INVITE_PARTNER_MSG="“{0}”邀请您成为他/她的合作伙伴，点击查看：{1}";
	
	//邮箱
	
	/**
	 * 注册邮箱标题
	 */
	public final static String SYSMSG_REGISTER_EMAIL_TITLE="聚聚+账号验证";
	
	/**
	 * 注册邮箱验证{0}:验证链接
	 */
	public final static String SYSMSG_REGISTER_EMAIL_CONTENT="欢迎您使用聚聚，请点击以下链接验证您的邮箱：{0}【聚聚+】";
	
	/**
	 * 忘记密码邮箱标题
	 */
	public final static String SYSMSG_FORGET_PWD_EMAIL_TITLE="聚聚+账号密码重置";
	
	/**
	 * 忘记密码邮箱内容{0}:密码重置链接
	 */
	public final static String SYSMSG_FORGET_PWD_EMAIL_CONTENT="您好，请点击以下链接，重置您的密码：{0} 本链接有效期为24小时。如果您没有发送过重置密码需求，可能是其他用户在尝试重置时错误输入了您的电子邮件地址，请忽略本邮件。【聚聚+】";
	
	
	/**
	 * 邮箱标题
	 * 邀请圈子成员提示{0}:用户昵称，{1}:圈子名称
	 */
	public final static String SYSMSG_INVITE_GROUP_EMAIL_TITLE="{0}邀请您加入{1}";

	/**
	 * 邮箱内容
	 * 邀请圈子成员提示{0}:好友用户昵称，{1}:好友手机{2}:圈子名称,{3}：链接地址+邀请码
	 */
	public final static String SYSMSG_INVITE_GROUP_EMAIL_CONTENT="您的好友“{0}”（{1}）邀请你加入圈子：“{2}”，点击查看：{3}";

	/**
	 * 邮箱标题
	 * 邀请活动成员提示{0}:用户昵称，{1}:活动名称
	 */
	public final static String SYSMSG_INVITE_ACTION_EMAIL_TITLE="{0}邀请您加入{1}";

	
	/**
	 * 邮箱内容
	 * 邀请活动成员提示{0}:好友用户昵称，{1}:好友手机,{2}:活动名称,{3}：链接地址+邀请码
	 */
	public final static String SYSMSG_INVITE_ACTION_EMAIL_CONTENT="您的好友“{0}”（{1}）邀请你参加活动：“{2}”，点击查看：{3}";

	
	/**
	 * 邮箱标题
	 * 邀请添加好友提示{0}:用户昵称
	 */
	public final static String SYSMSG_INVITE_ADD_FRIEND_EMAIL_TITLE="{0}邀请您加入聚聚+";
	
	/**
	 * 邮箱内容
	 * 邀请添加好友提示{0}:好友用户昵称，{1}:好友手机,{2}:链接地址+邀请码
	 */
	public final static String SYSMSG_INVITE_ADD_FRIEND_EMAIL_CONTENT="您的好友“{0}”（{1}）邀请你一起聚聚，点击查看：{2}";
	
	/**
	 * 邮箱标题
	 * 分享圈子提示{0}:用户昵称，{1}:圈子名称
	 */
	public final static String SYSMSG_SHARE_GROUP_EMAIL_TITLE="{0}分享了聚聚+上的{1}圈子给您";
	
	/**
	 * 邮箱内容
	 * 分享圈子提示{0}:圈子名称，{1}:链接地址
	 */
	public final static String SYSMSG_SHARE_GROUP_EMAIL_CONTENT="推荐有趣的圈子“{0}”，你也来看看吧：{1}";
	
	/**
	 * 邮箱标题
	 * 分享活动提示{0}:用户昵称，{1}：活动名称
	 */
	public final static String SYSMSG_SHARE_ACTION_EMAIL_TITLE="{0}分享了聚聚+上的{1}活动给您";
	
	/**
	 * 邮箱内容
	 * 分享活动提示{0}:活动名称，{1}：链接地址
	 */
	public final static String SYSMSG_SHARE_ACTION_EMAIL_CONTENT="推荐一个精彩活动“{0}”，你也来看看吧：{1}";
	
	
	
	/**
	 * 邮箱标题
	 * 合作伙伴邀请提示{0}:公司名称
	 */
	public final static String SYSMSG_INVITE_PARTNER_EMAIL_TITLE="{0}聚聚+上的合作伙伴邀请";
	
	/**
	 * 邮箱内容
	 * 合作伙伴邀请{0}:公司名称，{1}:链接地址
	 */
	public final static String SYSMSG_INVITE_PARTNER_EMAIL_CONTENT="{0}邀请您成为他/她的合作伙伴，点击查看:{1}";
	
	
	
	
	
	
	
	/**
	 * web端用户，邮箱链接
	 */
	public final static String SYS_INVITE_WEB_USER_URL="/app/index.html?page=friendinfo&id=";
	
	
	/**
	 * web端活动，邮箱链接
	 */
	public final static String SYS_INVITE_WEB_ACTION_URL="/app/index.html?page=actionStarted&id=";
	
	/**
	 * web端圈子，邮箱链接
	 */
	public final static String SYS_INVITE_WEB_GROUP_URL="/app/index.html?page=groupinfo&id=";
	
	/**
	 * web端地址，邮箱地址
	 */
	public final static String SYS_INVITE_MOBILE_URL="/wi/";
	
	/**
	 * 手机短信下载链接地址
	 */
	public final static String SYS_DOWNLOAD_URL="http://apps.juju.im";
	//public final static String SYS_DOWNLOAD_URL="http://a.app.qq.com/o/simple.jsp?pkgname=com.zdnst.jujuplus";
	
	
}

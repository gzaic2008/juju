package com.zdnst.common.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 码表
 * 
 * @author yongqin.zhong
 * 
 */
public class BaseCode {

	// base code
	/**
	 * 成功返回码表值
	 */
	public final static String SUCCESS_CODE = "1";// 成功返回码表值
	
	//1开头为调用接口类异常
	/**
	 *  非法参数
	 */
	public final static String ERROR_CODE100 = "100";// 非法参数
	/**
	 *  非法操作
	 */
	public final static String ERROR_CODE101 = "101";// 非法操作
	/**
	 *  请求无效
	 */
	public final static String ERROR_CODE102 = "102";// 请求无效
	/**
	 *  禁止访问
	 */
	public final static String ERROR_CODE103 = "103";// 禁止访问
	/**
	 *  未实现
	 */
	public final static String ERROR_CODE104 = "104";// 未实现
	
	/**
	 *  无法找到资源
	 */
	public final static String ERROR_CODE105 = "105";// 无法找到资源
	/**
	 *  资源被禁止
	 */
	public final static String ERROR_CODE106 = "106";// 资源被禁止
	/**
	 *  网管错误
	 */
	public final static String ERROR_CODE107 = "107";// 网管错误
	/**
	 *  未登陆
	 */
	public final static String ERROR_CODE108 = "108";// 未登陆
	/**
	 *  无权限
	 */
	public final static String ERROR_CODE109 = "109";// 无权限
	
	/**
	 *  系统无法提供服务
	 */
	public final static String ERROR_CODE110 = "110";// 系统无法提供服务
	/**
	 *  参数太少
	 */
	public final static String ERROR_CODE111 = "111";// 参数太少
	/**
	 *  参数错误
	 */
	public final static String ERROR_CODE112 = "112";// 参数错误
	/**
	 *  参数为空
	 */
	public final static String ERROR_CODE113 = "113";// 参数为空
	/**
	 *  JSON解析异常
	 */
	public final static String ERROR_CODE114 = "114";// JSON解析异常
	/**
	 *  JSON匹配异常
	 */
	public final static String ERROR_CODE115 = "115";// JSON匹配异常
	/**
	 *  数据不存在
	 */
	public final static String ERROR_CODE116 = "116";// 数据不存在
	/**
	 *  手机号码格式不正确
	 */
	public final static String ERROR_CODE117 = "117";// 手机号码格式不正确
	/**
	 *  操作过频繁
	 */
	public final static String ERROR_CODE118 = "118";// 操作过频繁
	
	/**
	 *  服务器内部对象转换异常
	 */
	public final static String ERROR_CODE119 = "119";//服务器内部对象转换异常
	
	/**
	 * 与数据库交换异常
	 */
	public final static String ERROR_CODE120 = "120";// 与数据库交换异常
	
	/**
	 * 手机号码不正确
	 */
	public final static String ERROR_CODE121 = "121";// 手机号码不正确
	/****
	 * 不能邀请添加自己为好友
	 */
	public final static String ERROR_CODE122 = "122";// 不能邀请添加自己为好友
	/****
	 * 邮箱不正确
	 */
	public final static String ERROR_CODE123 = "123";// 邮箱不正确
	/****
	 * 您所分享的对象不存在
	 */
	public final static String ERROR_CODE124 = "124";//您所分享的对象不存在
	
	/**
	 * 类型转换失败
	 */
	public final static String ERROR_CODE125 = "125";
	
	
	
	public static String[] values1 = { "非法参数", "非法操作", "请求无效", "禁止访问", "未实现",
		"无法找到资源", "资源被禁止", "网管错误", "未登陆", "无权限",
		"系统无法提供服务", "参数太少", "参数错误","参数为空","JSON解析异常", "JSON匹配异常", "数据不存在", "手机号码格式不正确", "操作过频繁",
		"服务器内部对象转换异常", "与数据库交换异常", "手机号码不正确", "不能邀请添加自己为好友", "邮箱不正确", "您所分享的对象不存在","类型转换失败" };
	
	
	
	//2开头为公共类业务异常
	/**
	 *  业务逻辑错误(具体错误信息需另外设置)
	 */
	public final static String ERROR_CODE200 = "200";// 业务逻辑错误(具体错误信息需另外设置)
	/**
	 *  密码错误
	 */
	public final static String ERROR_CODE201 = "201";// 密码错误
	/**
	 *  验证码错误
	 */
	public final static String ERROR_CODE202 = "202";// 验证码错误
	/**
	 *  不能重复分享
	 */
	public final static String ERROR_CODE203 = "203";// 不能重复分享
	/**
	 *  手机或者邮箱已存在
	 */
	public final static String ERROR_CODE204 = "204";// 手机或者邮箱已存在
	/**
	 *  发送失败
	 */
	public final static String ERROR_CODE205 = "205";// 发送失败
	/**
	 *  上传目录异常
	 */
	public final static String ERROR_CODE206 = "206";// 上传目录异常
	/**
	 *  上传文件失败
	 */
	public final static String ERROR_CODE207 = "207";// 上传文件失败
	/**
	 *  文件类型不正确
	 */
	public final static String ERROR_CODE208 = "208";// 文件类型不正确
	/**
	 *  用户不存在
	 */
	public final static String ERROR_CODE209 = "209";// 用户不存在
	/**
	 *  旧密码输入错误
	 */
	public final static String ERROR_CODE210 = "210";// 旧密码输入错误
	/**
	 *  您已经申请加入该好友
	 */
	public final static String ERROR_CODE211 = "211";// 您已经申请加入该好友
	/**
	 *  不能加自己为好友
	 */
	public final static String ERROR_CODE212 = "212";// 不能加自己为好友
	/**
	 *  账号未激活
	 */
	public final static String ERROR_CODE213 = "213";// 账号未激活
	/**
	 *  验证码已过期
	 */
	public final static String ERROR_CODE214 = "214";// 验证码已过期
	/**
	 *  你们已经是好友
	 */
	public final static String ERROR_CODE215 = "215";// 你们已经是好友
	/**
	 *  你输入的账号/密码错误，请重新输入
	 */
	public final static String ERROR_CODE216 = "216";// 你输入的账号/密码错误，请重新输入
	/**
	 *  该用户已存在
	 */
	public final static String ERROR_CODE217 = "217";// 该用户已存在
	/**
	 *  激活链接已过期，请重新获取
	 */
	public final static String ERROR_CODE218 = "218";// 激活链接已过期，请重新获取
	
	/**
	 *  新旧密码不一致
	 */
	public final static String ERROR_CODE219 = "219";// 新旧密码不一致
	
	/**
	 *  输入的手机/邮箱已经被使用
	 */
	public final static String ERROR_CODE220 = "220";// 输入的手机/邮箱已经被使用
	
	/**
	 *  账户已经被激活,无需再次激活
	 */
	public final static String ERROR_CODE221 = "221";// 账户已经被激活,无需再次激活
	/***
	 * 账号已绑定
	 */
	public final static String ERROR_CODE222 = "222";// 账号已绑定,无需再次绑定
	
	/***
	 * 文件不存在
	 */
	public final static String ERROR_CODE223 = "223";
	
	public static String[] values2 = { "业务逻辑错误", "密码错误", "验证码错误", "不能重复分享", "手机或者邮箱已存在",
		"发送失败", "上传目录异常", "上传文件失败", "文件类型不正确", "用户不存在",
		"旧密码输入错误", "您已经申请加入该好友", "不能加自己为好友", "账号未激活", "验证码已过期",
		"你们已经是好友", "你输入的账号/密码错误，请重新输入", "该用户已存在", "激活链接已过期，请重新获取", "新旧密码不一致", "输入的手机/邮箱已经被使用","账户已经被激活,无需再次激活","账号已绑定","文件不存在" };
	
	//3开头为圈子用户类业务异常
	/**
	 *  下联验证错误
	 */
	public final static String ERROR_CODE300 = "300";// 下联验证错误
	/**
	 *  您已是该圈子成员
	 */
	public final static String ERROR_CODE301 = "301";// 您已是该圈子成员
	/**
	 *  您已关注过该圈子
	 */
	public final static String ERROR_CODE302 = "302";// 您已关注过该圈子
	/**
	 *  需要先更改圈主
	 */
	public final static String ERROR_CODE303 = "303";// 需要先更改圈主
	/**
	 *  该圈子不能升级
	 */
	public final static String ERROR_CODE304 = "304";// 无升级权限
	/**
	 *  您已经存在高级圈
	 */
	public final static String ERROR_CODE305 = "305";// 您已经存在高级圈
	/**
	 *  该圈子不能降级
	 */
	public final static String ERROR_CODE306 = "306";// 无降级权限
	/**
	 *  圈主才有权限
	 */
	public final static String ERROR_CODE307 = "307";// 圈主才有权限
	/**
	 *  超过修改权限
	 */
	public final static String ERROR_CODE308 = "308";// 超过修改权限
	/**
	 *  该成员已存在高级圈
	 */
	public final static String ERROR_CODE309 = "309";// 该成员已存在高级圈
	/**
	 *  管理圈成员不能退出非管理圈
	 */
	public final static String ERROR_CODE310 = "310";// 管理圈成员不能退出非管理圈
	
	/**
	 * 圈内还有其他成员，不能删除
	 */
	public final static String ERROR_CODE311 = "311";
	
	/**
	 * 邀请码错误
	 */
	public final static String ERROR_CODE312 = "312";
	
	/**
	 * 该用户已设职级
	 */
	public final static String ERROR_CODE313 = "313";
	
	/**
	 *  该企业已存在
	 */
	public final static String ERROR_CODE314 = "314";
	
	/**
	 *  你们已经是合作伙伴
	 */
	public final static String ERROR_CODE315 = "315";

	public static String[] values3 = { "下联验证错误", "您已是该圈子成员", "您已关注过该圈子", "需要先更改圈主", "该圈子不能升级",
			"您已经存在高级圈", "该圈子不能降级", "圈主才有权限", "超过修改权限", "该成员已存在高级圈", "管理圈成员不能退出非管理圈",
			 "圈内有成员，圈主不能退出圈子","邀请码错误","该用户已设职级","该企业已存在","您们已经是合作伙伴"};
	
	//4开头为活动类业务异常
	/**
	 *  您已进行过投票
	 */
	public final static String ERROR_CODE400 = "400";// 您已进行过投票
	/**
	 *  您已参加活动
	 */
	public final static String ERROR_CODE401 = "401";// 您已参加活动
	/**
	 *  已经邀请过他/她参加活动
	 */
	public final static String ERROR_CODE402 = "402";// 已经邀请过他/她参加活动
	/**
	 *  您已经点赞过
	 */
	public final static String ERROR_CODE403 = "403";// 您已经点赞过
	/**
	 *  未到签到时间
	 */
	public final static String ERROR_CODE404 = "404";// 未到签到时间
	/**
	 *  您已签到
	 */
	public final static String ERROR_CODE405 = "405";// 您已签到
	/**
	 *  活动已满员
	 */
	public final static String ERROR_CODE406 = "406";// 活动已满员
	
	/**
	 *  无创建活动权限
	 */
	public final static String ERROR_CODE407 = "407";// 无创建活动权限
	
	/**
	 * 不能删除自己
	 */
	public final static String ERROR_CODE408 = "408";// 不能删除自己
	
	/**
	 *投票已结束
	 */
	public final static String ERROR_CODE409 = "409";
	
	/**
	 *投票截止时间不能小于当前时间
	 */
	public final static String ERROR_CODE410 = "410";
	
	/**
	 *活动开始时间不能小于当前时间
	 */
	public final static String ERROR_CODE411 = "411";
	
	public static String[] values4 = { "您已进行过投票", "您已参加活动", "已经邀请过他/她参加活动", "您已经点赞过", "未到签到时间",
		"您已签到", "活动已满员", "无创建活动权限", "不能删除自己",
		"投票已结束", "投票截止时间不能小于当前时间", "活动开始时间不能小于当前时间", "", "", "", "", "", "", "", "" };
	
	
	public static Map<String, Object> resultMsg = new HashMap<String, Object>();

	static {
		// put error message in map
		for (int i = 0; i <= 40; i++) {
			String code = "0";
			if (i < 10) {
				code = "0" + i;
			} else {
				code = i + "";
			}
			if (i < values1.length) {
				resultMsg.put("1" + code, values1[i]);
			}
			if (i < values2.length) {
				resultMsg.put("2" + code, values2[i]);
			}
			if (i < values3.length) {
				resultMsg.put("3" + code, values3[i]);
			}
			if (i < values4.length) {
				resultMsg.put("4" + code, values4[i]);
			}
		}

	}

}

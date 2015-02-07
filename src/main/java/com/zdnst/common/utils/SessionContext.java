/**
 * Copyright(C) 2012-2014 GuangZhou zdnst Co., Ltd. All Rights Reserved.<br/>
 * 版权所有(C)2012-2014 <br/>
 * 公司名称：广州支点科技有限公司<br/>
 * 公司地址：广州市天河区天源路401号之三E2栋<br/>
 * 网址：http://www.100100system.com/<br/>
 * <p>标        题：</p>
 * <p>文  件  名：com.zdnst.common.utils.SessionContext.java</p>
 * <p>部        门：研发一部
 * <p>版        本： 1.0</p>
 * <p>Compiler: JDK1.6.0_21</p>
 * <p>创  建  者：kylin.woo</p>
 * <p>创建时间：2014年8月30日下午6:49:02</p>
 * <p>修  改  者：</p>
 * <p>修改时间：</p>
 */
package com.zdnst.common.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class SessionContext {
	
	private static SessionContext instance;
    private static Map<String, Object> map;
 
    private SessionContext() {
    	map = new HashMap<String, Object>();
    }
 
    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }
 
    public synchronized void AddSession(HttpSession session) {
        if (session != null) {
        	map.put(session.getId(), session);
        }
    }
 
    public synchronized void DelSession(HttpSession session) {
        if (session != null) {
        	map.remove(session.getId());
        }
    }
 
    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null) return null;
        return (HttpSession) map.get(session_id);
    }
}

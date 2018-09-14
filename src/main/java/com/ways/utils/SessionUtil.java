package com.ways.utils;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

/**
 * Session工具类
 * @author jiangsy
 * @version 2016-11-7
 */
public class SessionUtil {
	
	
	
	/**
	 * 获取Session值
	 * @param session
	 * @param sessionKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getAttr(HttpSession session, String sessionKey){
		return (T) session.getAttribute(sessionKey);
	}
	
	/**
	 * 获取Session值后清除
	 * @param session
	 * @param sessionKey
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T pollAttr(HttpSession session, String sessionKey){
		T t = (T) session.getAttribute(sessionKey);
		session.removeAttribute(sessionKey);
		return t;
	}
	
	/**
	 * 保存Session值
	 * @param session .
	 * @param sessionKey .
	 * @param sessionValue .
	 */
	public static void setAttr(HttpSession session, String sessionKey, Object sessionValue){
		session.setAttribute(sessionKey, sessionValue);
	}
	
	/**
	 * 根据session名称删除session值.
	 * @param session .
	 * @param sessionKey .
	 */
	public static void removeAttr(HttpSession session, String sessionKey){
		session.removeAttribute(sessionKey);
	}
	
	/**
	 * 移除所有session值.
	 * @param session .
	 */
	public static void removeAll(HttpSession session){
		Enumeration<String> em = session.getAttributeNames();
        while (em.hasMoreElements()) {
        	session.removeAttribute(em.nextElement().toString());
        }
	}
	
	/**
	 * 销毁session
	 * @param session
	 */
	public static void destory(HttpSession session){
        session.invalidate();
	}
	
}

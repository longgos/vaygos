package com.ways.utils.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 短信参数配置
 * add by qiuguanghui 2016-11-09
 * @author QiuGH
 */
public class MqUtil {
	
	private static Logger logger = LoggerFactory.getLogger(MqUtil.class);
	public static ResourceUtils GTL_SMS_RU = ResourceUtils.getResource("template/gtl_sms");
	public static ResourceUtils GTL_MSG_RU = ResourceUtils.getResource("template/gtl_msg");
	public static ResourceUtils GTL_EMAIL_RU = ResourceUtils.getResource("template/gtl_email");
	public static Map<String, String> GTL_SMS_MAP = ResourceUtils.getResource("template/gtl_sms").getMap();
	public static Map<String, String> GTL_EMAIL_MAP = ResourceUtils.getResource("template/gtl_email").getMap();
	public static Map<String, String> GTL_MSG_MAP = ResourceUtils.getResource("template/gtl_msg").getMap();
	
	//************************************【短信模板模块】*****************************************************
	public static String SMS_DYNAMIC_CODE_VM = "template/sms/DynamicCode.vm";//动态码
	public static String SMS_CONFIRM_ACCOUNT = "SMS_CONFIRM_ACCOUNT";//充值
	public static String SMS_OPEN_ACCOUNT = "SMS_OPEN_ACCOUNT";//开通账号
	public static String SMS_CHECK_CONTACT = "SMS_CHECK_CONTACT";//审核员审核
	
	//************************************【邮件模板模块】*****************************************************
	public static String MAIL_MEMBER_RES_MA_VM = GTL_EMAIL_MAP.get("MAIL_MEMBER_RES_MA_VM");
	public static String MAIL_MEMBER_RES_MA_URL = GTL_EMAIL_MAP.get("MAIL_MEMBER_RES_MA_URL");
	public static String MAIL_MEMBER_RES_MA_TIME = GTL_EMAIL_MAP.get("MAIL_MEMBER_RES_MA_TIME");
	public static String MAIL_MEMBER_RES_MA_SUB = GTL_EMAIL_MAP.get("MAIL_MEMBER_RES_MA_SUB");
	
	//************************************【系统消息模块】*****************************************************
	public static String REG_MSG = GTL_MSG_MAP.get("REG_MSG");
	public static String LOAN_MSG = GTL_MSG_MAP.get("LOAN_MSG");
	public static String EXPRESS_MSG = GTL_MSG_MAP.get("EXPRESS_MSG");
	
	//************************************【辅助工具类】*****************************************************
	/**
	 * 返回短信配置信息
	 * add by qiuguanghui 2016-07-22
	 * @param key
	 * @param args
	 * @return
	 */
	public static String getSmsText(String key, Object... args){
		String sms_text = "";
		try {
			sms_text = MqUtil.GTL_SMS_RU.getValue(key, args);
		} catch (Exception e) {
			logger.error("from [gtl_sms.properties] getSmsText by key [" + key + "] find error......" , e);
		}
		return sms_text;
	}
	
	/**
	 * 返回系统消息配置信息
	 * add by qiuguanghui 2016-07-22
	 * @param key
	 * @param args
	 * @return
	 */
	public static String getMsgText(String key, Object... args){
		String msg_text = "";
		try {
			msg_text = MqUtil.GTL_MSG_RU.getValue(key, args);
		} catch (Exception e) {
			logger.error("from [gtl_msg.properties] getMsgText by key [" + key + "] find error......" , e);
		}
		return msg_text;
	}
	
}

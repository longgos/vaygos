/*package com.ways.utils.utils.sms;

import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.hzr.gtl.common.config.PublicConfig;
import com.hzr.gtl.common.constants.GtlConst;

public class SmsUtils {
	
	private static Logger logger = LoggerFactory.getLogger(SmsUtils.class);
	private static String DEFAULT_URL = "http://www.mxtong.net.cn/GateWay/Services.asmx/DirectSend?";
	
	*//**
	 * 发送短信提醒内容
	 * add by qiuguanghui 2016-07-22
	 * @param phoneNum
	 * @param smsContent
	 * @return
	 *//*
	public static boolean sendRemindSms(String phoneNum, String smsContent){
		boolean isSuccess = false;
		try {
			SmsUtils.sendSmsMessage(new DirectSend(phoneNum, smsContent));
			isSuccess = true;
		} catch (Exception e) {
			logger.error("send sendRemindSms error:", e);
		}
		return isSuccess;
	}
	
	*//**
	 * 发送短信验证码【未使用短信模板，固定默认短信验证码失效时间30分钟】【HttpSession】
	 * add by qiuguanghui 2016-07-22
	 * @param phoneNum      接收短信号码
	 * @param effectiveTime 有效时间，单位分钟
	 * @param session
	 * @return
	 *//*
	public static boolean sendDynamicCode(String phoneNum, HttpSession session) {
		boolean isSuccess = false;
		String code = genSmsCode();
		String smsContent = "您本次申请的验证码是" + code + "，请在30分钟内，登录系统输入验证码，谢谢！" + PublicConfig.SMS_SUFFIX;//设置短信内容
		try {
			SmsUtils.sendSmsMessage(new DirectSend(phoneNum, smsContent));
			session.setAttribute(GtlConst.SMS_CODE, new SmsCode(SecurePolicy.genVerifyGroup(code, phoneNum)));
			isSuccess = true;
		} catch (Exception e) {
			logger.error("send sendDynamicCode error:", e);
		}
		return isSuccess;
	}
	
	*//**
	 * 发送短信验证码【未使用短信模板，但自定义短信验证码失效时间】【HttpSession】
	 * @param phoneNum
	 * @param effectiveTime
	 * @param session
	 * @return
	 *//*
	public static boolean sendDynamicCode(String phoneNum, int effectiveTime, HttpSession session) {
		boolean isSuccess = false;
		String code = genSmsCode();
		String smsContent = "您本次申请的验证码是" + code + "，请在" + effectiveTime + "分钟内，登录系统输入验证码，谢谢！" + PublicConfig.SMS_SUFFIX;//设置短信内容
		try {
			SmsUtils.sendSmsMessage(new DirectSend(phoneNum, smsContent));
			session.setAttribute(GtlConst.SMS_CODE, new SmsCode(SecurePolicy.genVerifyGroup(code, phoneNum), effectiveTime));
			isSuccess = true;
		} catch (Exception e) {
			logger.error("send sendDynamicCode error:", e);
		}
		return isSuccess;
	}
	
	*//**
	 * 发送短信验证码【使用短信模板自定义短信内容】【HttpSession】
	 * add by qiuguanghui 2016-07-22
	 * @param phoneNum
	 * @param dynamicCode
	 * @param effectiveTime
	 * @param smsContent
	 * @param session
	 * @return
	 *//*
	public static boolean sendDynamicCode(String phoneNum, String dynamicCode, int effectiveTime, String smsContent) {
		boolean isSuccess = false;
		try {
			SmsUtils.sendSmsMessage(new DirectSend(phoneNum, smsContent));
			
			isSuccess = true;
		} catch (Exception e) {
			logger.error("send sendDynamicCode error:", e);
		}
		return isSuccess;
	}
	
	*//**
	 * 生成随机验证码
	 * add by qiuguanghui 2016-07-22
	 * @return
	 *//*
	public static String genSmsCode(){
		return RandomUtils.nextInt(100000, 999999) + "";//获取随机码
	}
	
	*//**
	 * 短信发送具体类
	 * add by qiuguanghui 2016-07-22
	 * @param directSend
	 * @throws Exception
	 *//*
	private static void sendSmsMessage(DirectSend directSend) throws Exception {
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setBooleanParameter("http.protocol.expect-continue", false);
		String sms_server_url = PublicConfig.SMS_SERVER_URL;
		if(StringUtils.isEmpty(sms_server_url)){
			sms_server_url = DEFAULT_URL;
		}
		PostMethod getMethod = new PostMethod(sms_server_url);
		try {
			String Content = java.net.URLEncoder.encode(directSend.getContent(), "UTF-8");
			NameValuePair[] data = {
					new NameValuePair("UserID", directSend.getUserId()),
					new NameValuePair("Account", directSend.getAccount()),
					new NameValuePair("Password", directSend.getPasswrod()),
					new NameValuePair("Phones", directSend.getPhones()),
					new NameValuePair("SendType", String.valueOf(directSend.getSendType())),
					new NameValuePair("SendTime", directSend.getSendTime()),
					new NameValuePair("PostFixNumber", directSend.getPostFixNumber()),
					new NameValuePair("Content", Content) };
			if (logger.isDebugEnabled()) {
				for (NameValuePair nv  : data) {
					logger.debug("发送短信参数" + "&" + nv.getName()+"=" + nv.getValue());
				}
			}
			getMethod.setRequestBody(data);
			getMethod.addRequestHeader("Connection", "close");
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				logger.debug("发送短信Method failed: "+ getMethod.getStatusLine());
			} 
			String str = new String(getMethod.getResponseBody(), "GBK");
			logger.debug("发送短信返回，result:{}", str);
			if (!str.contains("<RetCode>Sucess</RetCode>")) {
				logger.error("发送短信失败");
				throw new RuntimeException("发送短信失败");
			} else {
			}
		} catch (Exception e) {
			logger.error("发送短信异常", e);
			throw new RuntimeException("发送短信异常");
		} finally {
			getMethod.releaseConnection();
		}
	}
	
	public static void main(String[] args) {
		//String sms_url = "http://www.mxtong.net.cn/GateWay/Services.asmx/DirectSend?";
		DirectSend sendPrm = new DirectSend();
		//上海资信
		sendPrm.setUserId("968995");
		sendPrm.setPasswrod("1JKY7K");
		//合众融
		//sendPrm.setUserId("969006");
		//sendPrm.setPasswrod("FWTNG9");
		sendPrm.setAccount("admin");
		sendPrm.setPhones("15989493842");
		sendPrm.setContent("你好，这个是测试的短信内容【深圳合众融】");
		sendPrm.setSendType(1);
		try {
			sendSmsMessage(sendPrm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
*/
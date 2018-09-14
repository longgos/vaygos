package com.ways.utils.utils.exceptions;

import java.io.PrintWriter;

import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常
 * add by qiuguanghui 2016-07-08
 * @author QiuGH
 * ******************************************************************************
 * 业务异常基类，所有业务异常都必须继承于此异常
 * 定义异常时，需要先确定异常所属模块。例如：添加商户报错 可以定义为 [10020001] 前四位数为系统模块编号，后4位为错误代码 ,唯一 
 * 订单服务异常:1001
 * 工作流异常：1002
 * 用户服务异常:2001
 * boss服务异常:3001
 * manager服务异常:4001
 * ******************************************************************************
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = -5875371379845226068L;
	//数据库操作,insert返回0
	public static final BizException DB_INSERT_RESULT_0 = new BizException(90040001, "数据库操作,insert返回0");
	//数据库操作,update返回0
	public static final BizException DB_UPDATE_RESULT_0 = new BizException(90040002, "数据库操作,update返回0");
    //数据库操作,selectOne返回null
	public static final BizException DB_SELECTONE_IS_NULL = new BizException(90040003, "数据库操作,selectOne返回null");
    //数据库操作,list返回null
	public static final BizException DB_LIST_IS_NULL = new BizException(90040004, "数据库操作,list返回null");
    //Token 验证不通过
	public static final BizException TOKEN_IS_ILLICIT = new BizException(90040005, "Token 验证非法");
	//会话超时　获取session时，如果是空，throws 下面这个异常 拦截器会拦截爆会话超时页面
	public static final BizException SESSION_IS_OUT_TIME = new BizException(90040006, "会话超时");
	//获取序列出错
	public static final BizException DB_GET_SEQ_NEXT_VALUE_ERROR = new BizException(90040007, "获取序列出错");
	//异常信息
	protected String msg;

	//具体异常码
	protected int code;

	public BizException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public BizException() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	/**
	 * 实例化异常
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public BizException newInstance(String msgFormat, Object... args) {
		return new BizException(this.code, msgFormat, args);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String message) {
		super(message);
	}
	
	
	
	
	
	
	
	
	
	
	
	/*********************************************************************************************/
	/*********************************** 异常工具类 【Begin】   ******************************************/
	/*********************************************************************************************/
	//将CheckedException转换为UncheckedException.
	public static RuntimeException unchecked(Exception e) {
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException(e);
		}
	}

	//将ErrorStack转化为String.
	public static String getStackTraceAsString(Throwable e) {
		if (e == null){
			return "";
		}
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

	//判断异常是否由某些底层的异常引起.
	public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
		Throwable cause = ex.getCause();
		while (cause != null) {
			for (Class<? extends Exception> causeClass : causeExceptionClasses) {
				if (causeClass.isInstance(cause)) {
					return true;
				}
			}
			cause = cause.getCause();
		}
		return false;
	}

	//在request中获取异常类
	public static Throwable getThrowable(HttpServletRequest request){
		Throwable ex = null;
		if (request.getAttribute("exception") != null) {
			ex = (Throwable) request.getAttribute("exception");
		} else if (request.getAttribute("javax.servlet.error.exception") != null) {
			ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
		}
		return ex;
	}
	/*********************************************************************************************/
	/*********************************** 异常工具类【End】    ********************************************/
	/*********************************************************************************************/
}

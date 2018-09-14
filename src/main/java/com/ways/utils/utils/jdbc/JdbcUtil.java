package com.ways.utils.utils.jdbc;

import com.alibaba.druid.filter.config.ConfigTools;

/** 
 * @描述: 数据库访问链接加解密工具
 * @作者: qiuguanghui
 * @创建时间：2016-7-22, 下午5:44:43 
 * @版本: 1.0 . 
 * @param <T>  
 */
public class JdbcUtil {

	public static void main(String[] args) {
		try {
			System.out.println("*******************************");
			//明文密码
			String pwd = "T4kv#86rt";
			System.out.println("明文密码为：" + pwd);
			//使用Druid加密工具加密
			String enpwd = ConfigTools.encrypt(pwd);
			System.out.println("对明文密码进行加密后为：" + enpwd);
			//使用Druid解密工具解密
			String deenpwd = ConfigTools.decrypt(enpwd);
			System.out.println("对加密密码进行解密后为：" + deenpwd);
			System.out.println("*******************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

package com.ways.utils.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class IpUtils {

	private static Logger logger = LoggerFactory.getLogger(IpUtils.class);
	
	/**
	 * 获取用户真实IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) { 
	     String ip = request.getHeader("x-forwarded-for"); 
	     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    	 ip = request.getHeader("X-Forwarded-For");
	     }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	         ip = request.getHeader("Proxy-Client-IP"); 
	     } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	         ip = request.getHeader("WL-Proxy-Client-IP"); 
	     } 
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	          ip = request.getRemoteAddr(); 
	      } 
	     return ip.split(",")[0]; 
	  }
	
	public static String getIpAddrProxy(HttpServletRequest request) {  
	    String strClientIp = request.getHeader("x-forwarded-for");  
	    logger.info("All the IP address string is: " + strClientIp);  
	    if(strClientIp == null || strClientIp.length() == 0 ||"unknown".equalsIgnoreCase(strClientIp))  
	    {  
	        strClientIp = request.getRemoteAddr();  
	    }else{  
	        String[] ipList = strClientIp.split(",");
	        String strIp = new String();  
	        for(int index = 0; index < ipList.length; index ++)  
	        {  
	            strIp = (String)ipList[index];  
	            if(!("unknown".equalsIgnoreCase(strIp)))  
	            {  
	                strClientIp = strIp;  
	                break;  
	            }  
	        }  
	    }  
	  
	    return strClientIp;  
	    }  
	
}

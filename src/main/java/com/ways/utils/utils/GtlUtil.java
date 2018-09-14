package com.ways.utils.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;


/**
 * @描述: 项目公共常用工具类
 * @作者: qiuguanghui
 * @创建时间：2016-7-12, 下午4:16:58
 * @版本: 1.0 .
 * @param <T>
 */
public class GtlUtil {

	public static final List<String> picExtList = Lists.newArrayList("bmp", "gif", "jpeg", "jpg", "png", "tif", "tiff", "ico");
	private static final int DEF_DIV_SCALE = 10; 
	/**
	 * 判断字符串是否为空
	 * add by qiuguanghui 2016-07-12
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		if (value == null || value == "" || value.length() <= 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断对象是否为空
	 * add by qiuguanghui 2016-07-12
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(Object value) {
		if (value != null && !"".equals(value)){
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * 生成随机字符
	 * @return 随机字符
	 */
	public static String genID(){
		StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            stringBuilder.append((int)(Math.random()*(10)));
        }
        long now = System.currentTimeMillis();//一个13位的时间戳
        return String.valueOf(now)+stringBuilder.toString();
	}
	
	/**
	 * 去除空格
	 * @param src
	 * @return
	 */
	public static String removeSpace(String src){
		if (!isEmpty(src)) {
			return src.replaceAll(" ", "").replaceAll(" ", "").replaceAll("　", "");
		}
		return src;
	}
	
	/**
	 * 获取文件类型
	 * @param file
	 * @return
	 */
	public static String getFileType(MultipartFile file){
		String fileType = "";
		String fileExt = FilenameUtils.getExtension(file.getOriginalFilename());
	    if (file.getContentType().contains("image")) {
	    	fileType = "image";
	    } else if (fileExt.equals("xls") || fileExt.equals("xlsx")) {
	    	fileType = "xls";
	    } else {
	    	fileType = fileExt;
	    }
	    return fileType;
	}

    /**
     * 判断后缀名是否为图片
     * @param fileExt
     * @return
     */
    public static boolean isPicture(String fileExt) { 
    	return picExtList.contains(fileExt.toLowerCase());
    }
    
    /**
     * 判断后缀名是否为excel
     * @param fileExt
     * @return
     */
    public static boolean isExcel(String fileExt) { 
    	return (fileExt.equals("xls") || fileExt.equals("xlsx"));
    }
	
	/**
     * 日期格式化
     * add by qiuguanghui 2016-08-23
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format){
    	if(date != null && format != null && !"".equals(format)){
    		SimpleDateFormat sdf = new SimpleDateFormat(format);
        	return sdf.format(date);
    	}
    	return "";
    }
    
    /**
     * 格式化企业信息自增主键编号
     * add by qiuguanghui 2016-11-10
     * @param companyId
     * @return
     */
    public static String fmtCompanyID(String companyId){
    	//String fmtValue = String.format("%0" + fmtLen + "d", Integer.parseInt(companyId));
    	int fmtLen = 12;//格式化位数
    	StringBuffer sb = new StringBuffer();
    	String fmtValue = "";
    	if(!GtlUtil.isEmpty(companyId) && companyId.length() <= fmtLen){
    		for(int i = 0; i < (fmtLen - companyId.length()); i++){
    			sb.append("0");
    		}
    		fmtValue = sb.toString() + companyId;
    	} else {
    		fmtValue = companyId;
    	}
    	return "C" + fmtValue;
    }
    
    /**
     * 格式化商户自增主键编号
     * add by qiuguanghui 2016-11-10
     * @param merchantId
     * @return
     */
    public static String fmtMerchantID(String merchantId){
    	//String fmtValue = String.format("%0" + fmtLen + "d", Integer.parseInt(merchantId));
    	int fmtLen = 12;//格式化位数
    	StringBuffer sb = new StringBuffer();
    	String fmtValue = "";
    	if(!GtlUtil.isEmpty(merchantId) && merchantId.length() <= fmtLen){
    		for(int i = 0; i < (fmtLen - merchantId.length()); i++){
    			sb.append("0");
    		}
    		fmtValue = sb.toString() + merchantId;
    	} else {
    		fmtValue = merchantId;
    	}
    	return "M" + fmtValue;
    }
    
    /**
     * 格式化员工信息表自增主键ID
     * add by qiuguanghui 2017-01-11
     * @param merchantId
     * @return
     */
    public static String fmtStaffId(String staffId){
    	int fmtLen = 12;//格式化位数
    	StringBuffer sb = new StringBuffer();
    	String fmtValue = "";
    	if(!GtlUtil.isEmpty(staffId) && staffId.length() <= fmtLen){
    		for(int i = 0; i < (fmtLen - staffId.length()); i++){
    			sb.append("0");
    		}
    		fmtValue = sb.toString() + staffId;
    	} else {
    		fmtValue = staffId;
    	}
    	return "S" + fmtValue;
    }
    
    /**
     * 格式化用户信息表自增主键ID
     * add by qiuguanghui 2017-01-11
     * @param merchantId
     * @return
     */
    public static String fmtUserId(String userId){
    	int fmtLen = 12;//格式化位数
    	StringBuffer sb = new StringBuffer();
    	String fmtValue = "";
    	if(!GtlUtil.isEmpty(userId) && userId.length() <= fmtLen){
    		for(int i = 0; i < (fmtLen - userId.length()); i++){
    			sb.append("0");
    		}
    		fmtValue = sb.toString() + userId;
    	} else {
    		fmtValue = userId;
    	}
    	return "U" + fmtValue;
    }
    
    /**
     * 设置entity里面的创建/修改者，创建/修改时间
     * 
     * @author 作者：zxw
     * @version 创建时间：2016年11月15日 下午5:41:24
     * @param target
     * @param userName
     */
    public static void setUserInfo(Object target, String userName) {
		if (target != null) {
			try {
				Field[] fields = target.getClass().getDeclaredFields();
				for (Field f : fields) {
					f.setAccessible(true);
					switch (f.getName()) {
					case "createdBy":
						if (f.get(target) == null) {
							f.set(target, userName);
						}
						break;
					case "updatedBy":
						f.set(target, userName);
						break;
					case "createdDate":
						if (f.get(target) == null) {
							f.set(target, new Date());
						}
						break;
					case "updatedDate":
						f.set(target, new Date());
						break;
					}
				}
			} catch (Exception e) {

			}
		}
	}
    
    /**
     * 两个Double数相加
     * add by qiuguanghui 2016-11-17
     * @param v1
     * @param v2
     * @return
     */
    public static Double addDouble(Double v1, Double v2) {
		BigDecimal b1 = new BigDecimal(v1.toString());  
		BigDecimal b2 = new BigDecimal(v2.toString());  
		return new Double(b1.add(b2).doubleValue()); 
	}
    
    /**
     * 两个Double数相减
     * add by qiuguanghui 2016-11-17
     * @param v1
     * @param v2
     * @return
     */
    public static Double subDouble(Double v1, Double v2) {
    	BigDecimal b1 = new BigDecimal(v1.toString());
    	BigDecimal b2 = new BigDecimal(v2.toString());
    	return new Double(b1.subtract(b2).doubleValue());
    }
    
    /**
     * 两个Double数相乘
     * add by qiuguanghui 2016-11-17
     * @param v1
     * @param v2
     * @return
     */
    public static Double mulDouble(Double v1, Double v2) { 
    	BigDecimal b1 = new BigDecimal(v1.toString());
    	BigDecimal b2 = new BigDecimal(v2.toString());
    	return new Double(b1.multiply(b2).doubleValue());
    }
    
    /**
     * 两个Double数相除
     * add by qiuguanghui 2016-11-17
     * @param v1
     * @param v2
     * @return
     */
    public static Double divDouble(Double v1, Double v2) {
    	BigDecimal b1 = new BigDecimal(v1.toString());  
    	BigDecimal b2 = new BigDecimal(v2.toString());  
    	return new Double(b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue());  
    }
    
    /**
     * 两个Double数相除,并保留scale位小数
     * add by qiuguanghui 2016-11-17
     * @param v1
     * @param v2
     * @return
     */
    public static Double divDouble(Double v1, Double v2, int scale) { 
    	if (scale < 0) {
    		throw new IllegalArgumentException("The scale must be a positive integer or zero");  
    	}  
    	BigDecimal b1 = new BigDecimal(v1.toString());  
    	BigDecimal b2 = new BigDecimal(v2.toString());  
    	return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());  
    }
    
    /**
     * 两个Double数相比较，返回 ：-1、0 或 1
     * 如：v1与v2比较，返回-1表示v1<v2，返回0表示：v1=v2，返回1表示：v1>v2
     * add by qiuguanghui 2017-01-09
     * @param v1
     * @param v2
     * @return
     */
    public static int compareDouble(Double v1, Double v2){
    	BigDecimal b1 = new BigDecimal(v1.toString());  
    	BigDecimal b2 = new BigDecimal(v2.toString());
    	return b1.compareTo(b2);
    }
    
    /**
     * 返回指定个数的月份
     * add by qiuguanghui 2016-11-17
     * @param num 返回指定个数的月份
     * @param fromCurrMonth 是否从当前月份开始
     * @return
     */
    public static String[] getLastMonths(int num, boolean fromCurrMonth){
    	String[] lastMonths = new String[num];
    	Calendar cal = Calendar.getInstance();
    	//cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);//要先+1,才能把本月的算进去
    	cal.set(Calendar.MONTH, fromCurrMonth ? (cal.get(Calendar.MONTH) + 1) : cal.get(Calendar.MONTH));
    	//************************************************************
    	for(int i = 0; i < num; i++){  
    		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1); //逐次往前推1个月  
    		lastMonths[(num - 1)-i] = cal.get(Calendar.YEAR) + "." + fillZero((cal.get(Calendar.MONTH) + 1), 2);  
    	}
    	return lastMonths;
    }
    
    /**
     * 对数字格式化，返回指定的位数，不够位数昨天补0
     * add by qiuguanghui 2016-11-17
     * @param month
     * @param digit
     * @return
     */
    public static String fillZero(int month, int digit){
    	StringBuffer sb = new StringBuffer();
    	String monthStr = String.valueOf(month);
    	int monthDigit = monthStr.length();
    	if(monthDigit < digit){
    		for(int i = 0; i < (digit - monthDigit); i++){
    			sb.append("0");
    		}
    	}
    	sb.append(monthStr);
    	return sb.toString();
    }
    
    /**
     * 格式化double数字显示
     * add by qiuguanghui 2016-11-17
     * @param v
     * @param fmt
     * @return
     */
    public static String fmtDouble(double v, String fmt){
    	if(GtlUtil.isEmpty(fmt)) {
    		return String.valueOf(v);
    	}
    	DecimalFormat dfmt = new DecimalFormat();
		//dfmt.applyPattern("##,###.00");
    	dfmt.applyPattern(fmt);
    	return dfmt.format(v);
    }
    
    /**
     * 格式化显示字符
     * add by qiuguanghui 2017-01-18
     * @param value
     * @return
     */
    public static String fmtOneStr(String val) {
    	String _value = "";
    	if(!isEmpty(val)){
    		/*StringBuffer sb = new StringBuffer();
    		for(int i = 0; i < val.substring(1, val.length()).length(); i++){
    			sb.append("*");
    		}
    		_value = val.substring(0, 1) + sb.toString();*/
    		_value = val.substring(0, 1);
    	}
    	return _value;
    }
    
    /** 测试  **/
    public static void main(String[] args) {
		//System.out.println("fmtValue=" + GtlUtil.fmtCompanyID("123"));
		//System.out.println("fmtValue=" + GtlUtil.fmtMerchantID("101"));
		
		/*String[] month = GtlUtil.getLastMonths(14, false);
		for(int i = 0; i < month.length; i++){
			System.out.println(month[i]);
		}*/
    	
    	String s = "邱光辉";
    	System.out.println("s--->" + GtlUtil.fmtOneStr(s));
	}
}

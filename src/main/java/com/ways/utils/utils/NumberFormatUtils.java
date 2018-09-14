package com.ways.utils.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 数值格式化 
 * @author hufeng
 *
 */
public class NumberFormatUtils {
    /**
     * 格式化金额.
     * @param money
     * @return
     */
    public static String formatMoney(Double money) {
        if (money == 0D) {
            money = 0D;
        }
        if (money > Math.floor(money)) {
            return new DecimalFormat("0.00").format(money);
        } else {
            return new DecimalFormat("0").format(money);
        }
    }
    
    /**
     * 格式化金额：保留两位小数.
     * @author cxc
     * @param money 金额
     * @return 保留两位小数后的金额数字
     */
	public static String formatMoney2Decimal(Double money) {
	    if (money == null) {
            return "";
        }
		return new DecimalFormat("0.00").format(money);
	}
	
	/**
	 * double转换百分比
	 * @param number
	 * @return
	 */
	public static String convertNumber2Percentage(Double number){
        NumberFormat fmt = NumberFormat.getPercentInstance();  
        fmt.setMaximumFractionDigits(2);
        String rate = fmt.format(number);
        return rate.replace("%", "");
	}
	
	/**
     * 格式化金额：保留两位小数.
     * @param money 金额
     * @return 保留两位小数后的金额数字
     */
	public static Double convertMoney2Decimal(Double money) {
	    if (money == null) {
            return null;
        }
	    return new BigDecimal(money).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 取消科学计数法
	 * @param num
	 * @return
	 */
	public static String convertDoubleGrouping(Double num) {
    	java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
    	nf.setGroupingUsed(false);  
	    return nf.format(num);
	}
}

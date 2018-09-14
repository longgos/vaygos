package com.ways.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ways.constants.ConstantFilal;

/**
 * 图片验证码.
 * 
 * @author 作者：cxc
 * @version 创建时间：2016年5月3日 上午9:02:32
 */
public class ImageCodeutil {
	
    private static Font mFont = new Font("Times New Roman", Font.BOLD, 17);

    public static void getImageCode(HttpSession session,HttpServletResponse response) throws IOException {
        int width=100, height=18;     
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);     
        Graphics g = image.getGraphics();     
        Random random = new Random();      
        g.fillRect(1, 1, width-1, height-1);       
        g.drawRect(0, 0, width-1, height-1);     
        g.setFont(mFont);     
        //生成随机数,并将随机数字转换为字母      
        String sRand="";     
        for (int i=0;i<4;i++) {     
            int itmp = random.nextInt(26) + 65;     
            char ctmp = (char)itmp;     
            sRand += String.valueOf(ctmp);     
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));     
            g.drawString(String.valueOf(ctmp),15*i+10,16);     
        }
        ImageCode imageCode = new ImageCodeutil().new ImageCode(sRand);
        session.setAttribute(ConstantFilal.IMGAE_CODE, imageCode);     
        g.dispose();     
        ImageIO.write(image, "JPEG", response.getOutputStream());     
    }
    
     public class ImageCode{

    	private String imageCode;
    	private Date expiredTime;

    	public String getImageCode() {
    		return imageCode;
    	}

    	public void setImageCode(String imageCode) {
    		this.imageCode = imageCode;
    	}

    	public Date getExpiredTime() {
    		return expiredTime;
    	}

    	public void setExpiredTime(Date expiredTime) {
    		this.expiredTime = expiredTime;
    	}

    	public ImageCode(String imageCode) {
    		this.imageCode = imageCode;
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime(new Date());
    		calendar.add(Calendar.MINUTE,10);
    		expiredTime = new Date(calendar.getTimeInMillis());
    	}
    }
}

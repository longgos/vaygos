package com.ways.utils.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @描述: 文件工具类
 * @作者: qiuguanghui
 * @创建时间：2016-7-13, 下午2:10:09
 * @版本: 1.0 .
 * @param <T>
 */
public class FileUtils extends org.apache.commons.io.FileUtils {
	/**
	 * 传入文件夹路径，该方法能够实现创建整个路径
	 * @param path 文件夹路径，不包含文件名称及后缀名
	 */
	public static void isDir(String path) {
		String[] paths = path.split("/");
		String filePath = "";
		for (int i = 0; i < paths.length; i++) {
			if (i == 0) {
				filePath = paths[0];
			} else {
				filePath += "/" + paths[i];
			}
			creatDir(filePath);
		}
	}

	/**
	 * 该方法用来判断文件夹是否存在，如果不存在则创建，存在则什么都不做
	 * @param filePath
	 */
	public static void creatDir(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
	
	
    /**
     * 将File转换成Byte[].
     * @param file 文件
     * @return byte[]
     */
    public static byte[] getBytesFromFile(File file) {  
        if (file == null) {  
            return null;  
        }  
        try {
            FileInputStream stream = new FileInputStream(file);  
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);  
            byte[] byt = new byte[1000];  
            int ni;  
            while ((ni = stream.read(byt)) != -1) { 
                out.write(byt, 0, ni);  
            }
            stream.close();  
            out.close();  
            return out.toByteArray();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return null;  
    }
	
}

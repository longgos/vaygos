package com.ways.constants;

import java.util.ArrayList;
import java.util.List;

public class VayConst {

	/**系统操作员*/
	public static final String SYSTEM = "system";
	
	public static List<String> filepath = new ArrayList<String>();
	
	/**博客状态；1:短文本;2:文章;3：图片+文字;4:音频+文字;5:视频+文字；*/
	public static final int BLOG_TYPE_TEXT = 1;
	public static final int BLOG_TYPE_ESSAY = 2;
	public static final int BLOG_TYPE_IMAGES = 3;
	public static final int BLOG_TYPE_AUDIOS = 4;
	public static final int BLOG_TYPE_VIDEOS = 5;
	
	/**系统默认头像*/
	public static final String DEFAULT_AVATAR = "images/user_avatar/v2-609767db9265bf311a8ab5ae8813bf11_b.jpg";
}

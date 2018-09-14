/**
 * Copyright &copy; 2012-2016 <a href="https://www.hzrinfo.com">hzrinfo</a> All rights reserved.
 */
package com.ways.param;
import com.tool.base.Pager;
import com.ways.entity.UserBlogEntity;
/**
 * 用户博客param
 * @author ljk
 * @version 2017-07-28
 */
public class UserBlogParam extends UserBlogEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Pager pager = new Pager();
	/**博客类型;1:短文本;2:文章;3：图片+文字;4:音频+文字;5:视频+文字；*/
	private int type;
	/**标题*/
	private String title;
	/**正文内容*/
	private String msg;
	/**图片链接*/
	private String[] imgLink;
	/**音频连接*/
	private String[] audioLink;
	/**视频连接*/
	private String[] videolink;
	
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	public Pager getPager() {
		return this.pager;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String[] getImgLink() {
		return imgLink;
	}

	public void setImgLink(String[] imgLink) {
		this.imgLink = imgLink;
	}

	public String[] getAudioLink() {
		return audioLink;
	}

	public void setAudioLink(String[] audioLink) {
		this.audioLink = audioLink;
	}

	public String[] getVideolink() {
		return videolink;
	}

	public void setVideolink(String[] videolink) {
		this.videolink = videolink;
	}
	
	
}

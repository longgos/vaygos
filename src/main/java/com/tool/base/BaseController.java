package com.tool.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ways.entity.UserInfoEntity;
import com.ways.utils.utils.Global;


/**
 * 控制器支持类
 * @author jiangsy
 * @version 2016-11-30
 */
public abstract class BaseController {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	
	
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		model.addAttribute("message", sb.toString());
	}
	
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	
	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException() {  
        return "error/400";
    }
	
	
	/**
	 * 获取登录信息
	 * @param sessionID
	 * @param request
	 * @param response
	 * @return
	 */
	public UserInfoEntity getUserInfo(String sessionID,HttpServletRequest request,HttpServletResponse response){
//		boolean status = false;
		SessionKey key = new WebSessionKey(sessionID,request, response);
		try {
			Session se = SecurityUtils.getSecurityManager().getSession(key);
			Object obj = se.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
			SimplePrincipalCollection coll = (SimplePrincipalCollection) obj;
			return (UserInfoEntity) coll.getPrimaryPrincipal();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 自定义错误页面
	 * @param model
	 * @param title
	 * @param activeTab
	 * @param returnUrl
	 * @return
	 */
	protected String errorCustom(Model model, String title, String activeTab, String returnUrl){
		model.addAttribute("title", title);
		model.addAttribute("activeTab", activeTab);
		model.addAttribute("returnUrl", returnUrl);
		return "/error/custom";
	}
	
	/**
	 * 错误页面
	 * @param model
	 * @return
	 */
	protected String error404(Model model){
		return "/error/404";
	}
	
	/**
	 * 错误页面
	 * @param model
	 * @return
	 */
	protected String error400(Model model){
		return "/error/400";
	}
	
	
    protected String getView(String view){
    	return "templates/edition/show/"+view;
    }
    protected String getRedirect(String view){
    	return "redirect:/"+view;
    }
	
	/**
	 * 创建分页对象
	 * @param pageModel
	 * @param pager
	 * @param model
	 *//*
	public <T> void buildPageBean(DataPageModel<T> pageModel, Pager pager, Model model) {
		PageBean<T> pageBean = new PageBean<T>(pager.getPageNo(), pager.getPageSize(), pageModel.getTotal(), pageModel.getDatas());
        model.addAttribute("pageBean", pageBean);
	}*/
	
}

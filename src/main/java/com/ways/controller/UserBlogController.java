/**
 * Copyright &copy; 2012-2016 <a href="https://www.hzrinfo.com">hzrinfo</a> All rights reserved.
 */
package com.ways.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tool.base.BaseController;
import com.ways.entity.UserBlogEntity;
import com.ways.facade.UserBlogFacade;
import com.ways.param.UserBlogParam;
import com.ways.utils.utils.Global;
import com.ways.utils.utils.model.DataPageModel;

/**
 * 用户博客Controller.
 * @author ljk
 * @version 2017-07-28
 */
@Controller
@RequestMapping(value = "${adminPath}/用户博客/vayBlog")
public class UserBlogController extends BaseController {

	@Autowired
	private UserBlogFacade userBlogService;
	
	/**
     * 列表页面.
     * @param vayBlogParam 查询参数
     * @param model model
     * @return 页面路径
     */
//	@RequiresPermissions("用户博客:vayBlog:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserBlogParam vayBlogParam, Model model) {
		DataPageModel<UserBlogEntity> pageModel = userBlogService.findPage(vayBlogParam);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("vayBlogParam", vayBlogParam);
		model.addAttribute("pager", vayBlogParam.getPager());
		return "modules/用户博客/vayBlogList";
	}

    /**
     * 表单页面.
     * @param Long blogId 主键
     * @param model model
     * @return 页面路径
     */
//	@RequiresPermissions("用户博客:vayBlog:view")
	@RequestMapping(value = "form")
	public String form(Long blogId, Model model) {
		UserBlogEntity vayBlog;
		if (null == blogId) {
			vayBlog = new UserBlogEntity();
		} else {
			vayBlog = userBlogService.getById(blogId);
			if (null == vayBlog) {
//				throw new XxxException(RetCode.SYSTEM_ERROR, "数据不存在");
			}
		}
		model.addAttribute("vayBlog", vayBlog);
		return "modules/用户博客/vayBlogForm";
	}
	
	/**
     * 参看页面.
     * @param Long blogId 主键
     * @param model model
     * @param redirectAttributes 重定向参数
     * @return 页面路径
     */
//	@RequiresPermissions("用户博客:vayBlog:view")
	@RequestMapping(value = "view")
	public String view(Long blogId, Model model, RedirectAttributes redirectAttributes) {
		UserBlogEntity vayBlog = userBlogService.getById(blogId);
		if (vayBlog == null){
			addMessage(redirectAttributes, "查看的用户博客不存在");
			return "redirect:"+Global.getAdminPath()+"/用户博客/vayBlog/?repage";
		}
		model.addAttribute("vayBlog", vayBlog);
		return "modules/用户博客/vayBlogView";
	}

    /**
     * 保持数据.
     * @param vayBlog 实体
     * @param model model
     * @param redirectAttributes 重定向参数
     * @return 重定向到列表页面的路径
     */
//	@RequiresPermissions("用户博客:vayBlog:edit")
	@RequestMapping(value = "save")
	public String save(UserBlogEntity vayBlog, Model model, RedirectAttributes redirectAttributes) {
//		if (!beanValidator(model, vayBlog)){
//			return form(vayBlog.getBlogId(), model);
//		}
		userBlogService.save(vayBlog);
		addMessage(redirectAttributes, "保存用户博客成功");
		return "redirect:"+Global.getAdminPath()+"/用户博客/vayBlog/?repage";
	}
	
	/**
     * 删除数据.
     * @param Long blogId 主键
     * @param redirectAttributes 重定向参数
     * @return 重定向到列表页面的路径
     */
//	@RequiresPermissions("用户博客:vayBlog:edit")
	@RequestMapping(value = "delete")
	public String delete(Long blogId, RedirectAttributes redirectAttributes) {
		userBlogService.deleteById(blogId);
		addMessage(redirectAttributes, "删除用户博客成功");
		return "redirect:"+Global.getAdminPath()+"/用户博客/vayBlog/?repage";
	}

}
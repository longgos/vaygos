package com.ways.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tool.base.BaseController;
import com.ways.bo.UserLoginBo;
import com.ways.constants.ConstantFilal;
import com.ways.constants.VayConst;
import com.ways.constants.pageConst;
import com.ways.entity.UserInfoEntity;
import com.ways.facade.UserInfoFacade;
import com.ways.param.UserInfoParam;
import com.ways.utils.BeanUtils;
import com.ways.utils.ImageCodeutil;
import com.ways.utils.ImageCodeutil.ImageCode;
import com.ways.utils.SessionUtil;
import com.ways.utils.VerifyCodeUtils;



/**
 * 用户登录Controller
 * @author ljk
 * 2017年6月14日
 */
@Controller
//@RequestMapping(value="login")
public class UserLoginController extends BaseController {

	@Autowired
	private UserInfoFacade userInfoFacade;

    /**
     * 产品说明
     * @param model
     * @return
     */
    @RequestMapping("/explain")
	public String explain(Model model) {
    	return "modules/product/product_explain";
    }
    
    /**
     * 首页显示
     * @return
     */
    @RequestMapping(value = {"","index"})
    public String index(Model model) {
    	UserLoginBo bo =new UserLoginBo();
    	model.addAttribute("userLoginBo",bo);
        return getView(pageConst.LOGIN_PAGE);
    }

    /**
     * 注册页面显示
     * @param model
     * @return
     */
    @RequestMapping(value="/register")
    public String register(Model model){
    	UserInfoParam param = new UserInfoParam();
    	model.addAttribute("userInfoParam",param);
    	return getView(pageConst.REGISTER_PAGE);
    }
    
    /**
     * 主页
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value ="/home")
    public String home(Model model){
     	Subject subject = SecurityUtils.getSubject();
     	Object b = subject.getPrincipals();
    	UserInfoEntity userInfo = userInfoFacade.findByUsername(b.toString());
    	UserInfoParam param =  new UserInfoParam();
    	BeanUtils.copyProperties(userInfo,param);
    	model.addAttribute("userInfo",param);
//    	UserInfoEntity e = getUserInfo((String)session.getId(), request, response);
    	return getView(pageConst.HOME_PAGE);
    }
    
    
    @RequestMapping(value="/othHome")
    public String othHome(Model model){
    	UserInfoParam param =  new UserInfoParam();
    	model.addAttribute("userInfo",param);
    	return getView(pageConst.HOME_PAGE);
    }
    /**
     * 处理登录请求
     * @param model
     * @return
     */
	@SuppressWarnings("unused")
    @RequestMapping(value = "/doLogin",method=RequestMethod.POST)
    public String doLogin( ModelMap model, RedirectAttributes redirectAttributes,
    		@Valid UserLoginBo userLoginBo) {
    	String userName = userLoginBo.getLoginName();
    	String passWord = new Sha384Hash(userLoginBo.getLoginPassword()).toBase64();
    	if(StringUtils.isBlank(userName) || StringUtils.isBlank(userName)){
    		return getView(pageConst.LOGIN_PAGE);
    	}
		Subject currentUser = SecurityUtils.getSubject();

		boolean b = currentUser.isAuthenticated();
		 //判断用户状态是否已经被认证
		if (!currentUser.isAuthenticated()) {// 使用shiro来验证
			// 第一步：用户登录，根据用户登录名密码生产Token
			UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
			token.setRememberMe(true);
			try {
				currentUser.login(token);// 验证角色和权限
				return getRedirect(pageConst.HOME_PAGE);// 成功
			} catch (UnknownAccountException e) {
				model.put("message",ConstantFilal.ERROR_PASSWORD);
			} catch (IncorrectCredentialsException e) {
				model.put("message",ConstantFilal.ERROR_PASSWORD);
			} catch (LockedAccountException e) {
				model.put("message",ConstantFilal.ERROR_DISABLED);
			} catch (Exception e) {
				model.put("message",ConstantFilal.ERROR_AUTHENTICATION);
			}
			return getView(pageConst.LOGIN_PAGE);
		}
		redirectAttributes.addFlashAttribute("loginName", userLoginBo.getLoginName());
	    return getRedirect(pageConst.HOME_PAGE);
//        HttpSession session = request.getSession();
        /*********************暂时没有手机号注册，图片验证码验证暂且挂起**********************/
       /* ImageCode imageCode = SessionUtil.getAttr(session, ConstantFilal.IMGAE_CODE);
        //session中的图片验证码超时，已被销毁
        if (imageCode == null) {
            redirectAttributes.addFlashAttribute("loginName", userLoginBo.getLoginName());
            addMessage(redirectAttributes,ConstantFilal.EXPIR_CODE);
            return "redirect:/login";
        }*/
        //session中的图片验证码过期
     /*   if (System.currentTimeMillis() >= imageCode.getExpiredTime().getTime()) {
            redirectAttributes.addFlashAttribute("loginName", userLoginBo.getLoginName());
            addMessage(redirectAttributes, ConstantFilal.EXPIR_CODE);
            return "redirect:/login";
        }*/
//        if (imageCode.getImageCode().equalsIgnoreCase(userLoginBo.getVerifyCode())) {
       
    }
    

    
    /**
     * 处理注册请求
     * @param model
     * @return
     */
    @Valid 
    @ResponseBody
    @RequestMapping(value = "/doRegist", method = RequestMethod.POST)
    public String doRegist(HttpServletRequest request, ModelMap model, @Valid UserInfoParam userInfoParam) {
    	/**密码进行散列/哈希加密**/
    	String passWord = userInfoParam.getLoginPassword();
    	passWord = new Sha384Hash(passWord).toBase64();
    	userInfoParam.setLoginPassword(passWord);
        HttpSession session = request.getSession();
        //用户名，手机号不能重复
//        UserInfoEntity info = new UserInfoEntity();
        if(userInfoParam.getNickName()!=""){
        	UserInfoEntity info = new UserInfoEntity();
        	info.setNickName(userInfoParam.getNickName());
        	info = userInfoFacade.getByParam(info);
        	if(null!= info){
        		model.put("message", "用户名已存在");
        		return getView(pageConst.REGISTER_PAGE);
        	}
        }
        if(userInfoParam.getEmail()!=""){
        	UserInfoEntity info = new UserInfoEntity();
        	info.setEmail(userInfoParam.getEmail());
        	info = userInfoFacade.getByParam(info);
        	if(null != info){
        		model.put("message","Email地址已存在");
        		return getView(pageConst.REGISTER_PAGE);
        	}
        }
        UserInfoEntity info = new UserInfoEntity();
        BeanUtils.copyProperties(userInfoParam, info);
        info.setRankId(1);
        info.setLoginName(userInfoParam.getNickName());
        info.setUserImg(VayConst.DEFAULT_AVATAR);
        UserInfoEntity userInfo = userInfoFacade.save(info);
        if(userInfo!=null){
        	model.put("message",ConstantFilal.REG_SUCCESS_MESSAGE);
        }else{
        	model.put("message",ConstantFilal.REG_FAILURE_MESSAGE);
        }
        //校验成功删除手机动态码
//        UserInfoEntity entity = new UserInfoEntity();
//        GtlBeanUtils.copyProperties(userRegistBo, info);
//        entity.setLoginName(userRegistBo.getLoginName());
//        entity.setLoginPassword(PasswordHash.entryptPassword(userRegistBo.getLoginPassword()));
//        entity.setCreateBy(ConstantFilal.SYSTEM);
//        entity.setCreateDate(new Date());
//        UserInfoEntity ent = userInfoFacade.save(entity);
//        SessionUtil.setAttr(session, ConstantFilal.BASIC_MEMBER, ent);
//        return DataModel.success();
        return getRedirect(pageConst.HOME_PAGE);
    }
 

    
   
    
    /**
     * 获取验证码.
     * 
     * @return String
     * @throws IOException .
     */
    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpSession session, HttpServletResponse response) throws IOException {
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        ImageCode imageCode = new ImageCodeutil().new ImageCode(verifyCode);
        SessionUtil.setAttr(session, ConstantFilal.IMGAE_CODE, imageCode);
        //生成图片  
        int w = 200, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }
    
    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value="/logout")
    public String logout(){
    	Map<String,Object> result = new HashMap<String,Object>();
    	result.put("success",true);
    	Subject currentUser = SecurityUtils.getSubject();
    	currentUser.logout();
    	return getRedirect(pageConst.HOME_PAGE);
    }
    /**
     * 忘记密码
     * @param model
     * @return
     */
    @RequestMapping("/forget")
    public String forget(HttpSession session, Model model) {
        return "modules/user/forget";
    }
    
    /**
     * 处理提交忘记密码
     * @param session
     * @param model
     * @return
     */
    /*@Valid
    @RequestMapping(value = "/doForget", method = RequestMethod.POST)
    public String doForget(HttpSession session, Model model, @Valid ForgetBo forgetBo, RedirectAttributes redirectAttributes) {
        SmsCode smsCode = SessionUtil.getAttr(session, ConstantFilal.SMS_PWD_CODE);
        if (smsCode == null || StringUtils.isBlank(smsCode.getSmsCode())) {
            addMessage(redirectAttributes, RetCode.GET_SMSCODE.getValue());
            return "redirect:/forget";
        }
        if (smsCode.isInvalid()) {
            addMessage(redirectAttributes, RetCode.SMSCODE_OVERDUE.getValue());
            return "redirect:/forget";
        }
        if (!SecurePolicy.verifyGroup(smsCode.getSmsCode(), forgetBo.getSmsCode(), forgetBo.getContactsMobile())) {
            addMessage(redirectAttributes, RetCode.SMSCODE_INVALID.getValue());
            return "redirect:/forget";
        }
        //校验成功删除手机动态码
        SessionUtil.removeAttr(session, ConstantFilal.SMS_PWD_CODE);
        CompanyInfoEntity companyInfo = companyInfoFacade.getByLoginName(forgetBo.getContactsMobile());
        if (companyInfo == null) {
            addMessage(redirectAttributes, RetCode.USER_NOT_EXIST.getValue());
            return "redirect:/forget";
        } else {
            SessionUtil.setAttr(session, ConstantFilal.GET_BACK_PWD, companyInfo.getLoginName());
            TokenUtil.createToken(session, model);
            return "modules/user/forget_setpwd";
        }
    }*/
    
   public static void main(String[] args) {
	String data = "941029";
	String sha = new Sha384Hash(data).toBase64();
	System.out.println("加密后的密码："+sha);
}
  
    /**
     * 产品说明
     * @param model
     * @return
     */
    @RequestMapping(value = "produce")
    public String produce(Model model) {
        return "modules/user/produce";
    }
    
    /**
     * 操作指引
     * @param model
     * @return
     */
    @RequestMapping(value = "guide")
    public String guide(Model model) {
        return "modules/user/guide";
    }
    
}

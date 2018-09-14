package com.shiro.realm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ways.entity.Permission;
import com.ways.entity.Role;
import com.ways.entity.UserInfoEntity;
import com.ways.facade.UserInfoFacade;

public class AccountRealm extends AuthorizingRealm {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
    private UserInfoFacade userInfoFadade;

    public AccountRealm() {
        super(new AllowAllCredentialsMatcher());
        setAuthenticationTokenClass(UsernamePasswordToken.class);

        //FIXME: 暂时禁用Cache
        setCachingEnabled(false);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	logger.info("----------doGetAuthorizationInfo方法被调用----------");
        String nickName = (String) principals.fromRealm(getName()).iterator().next();
    	Set<Role> roleSet = userInfoFadade.findByUsername(nickName).getRoleSet();
    	//角色名集合
    	Set<String> roles = new HashSet<String>();
    	//权限名集合
    	Set<String> permissions = new HashSet<String>();
    	//迭代
    	Iterator<Role> it = roleSet.iterator();
    	while (it.hasNext()) {
    		roles.add(it.next().getName());
    		for (Permission per : it.next().getPermissionSet()) {
				permissions.add(per.getName());
			}
		}
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
        
    }

    /**
     * 提供账户信息返回认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken at) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)at;
    	String username = String.valueOf(token.getPrincipal());
    	String pasw = new String((char[])token.getCredentials());
    	logger.info("-----------用户名称："+username);
    	UserInfoEntity user = userInfoFadade.findByUsername(username);
        if (username == null || username.equals("")) {
            // 用户名不存在抛出异常
            throw new UnknownAccountException("账号为空");
        }
        if(!pasw.equals(user.getLoginPassword())) {
            throw new IncorrectCredentialsException("用户凭证错误"); // 如果密码错误
        }
        if(user==null || user.equals("")){
        	 // 用户不存在抛出异常
            return null;
    	}else {
    	 	//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现   
        	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getLoginName(),user.getLoginPassword(),getName()); 
        	return info;
        }
    }
    
    /**
     * 清除所有用户授权信息缓存
     * @param principal
     */
    protected void clearCachedAuthorizationInfo(String principal){
    	SimplePrincipalCollection principals = new SimplePrincipalCollection(principal,getName());
    	clearCachedAuthorizationInfo(principals);
    }
    /**
     * 清除所有用户授权信息缓存
     */
    protected void clearAllCachedAuthorizationInfo(){
    	Cache<Object,AuthorizationInfo> cache = getAuthorizationCache();
    	if(cache !=null){
    		for (Object key : cache.keys()) {
				cache.remove(key);
			}
    	}
    }
    
}

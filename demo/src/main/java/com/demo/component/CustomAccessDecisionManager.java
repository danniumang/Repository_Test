package com.demo.component;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


/**
 * @Title CustomAccessDecisionManager.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年10月21日 下午9:34:18 
 * 路径对应角色与当前角色进行对比，相同则有权限，否则返回权限不足
 */
@Component
public class CustomAccessDecisionManager implements AccessDecisionManager{

	/**
	 *判断当前用户是否具备当前请求路径对应的角色信息。
	 *auth 当前登录用户信息。   object FilterInvocation对象，获取当前请求对象。  ca：当前请求路径对应的角色信息。
	 */
	@Override
	public void decide(Authentication auth, Object object, Collection<ConfigAttribute> ca)
			throws AccessDeniedException, InsufficientAuthenticationException {
		// TODO Auto-generated method stub
		Collection<? extends GrantedAuthority>auths=auth.getAuthorities()	;
		for(ConfigAttribute cfab :ca) {
			//用户登录即可访问。
			if("ROLE_LOGIN".equals(cfab.getAttribute())&&auth instanceof UsernamePasswordAuthenticationToken) {return;}
			//需要权限验证情况
		for(GrantedAuthority authority:auths) {
			if(cfab.getAttribute().equals(authority.getAuthority())) {return;}
		}
			
		}
	   //不具备则抛出异常，具备则什么也不做。
		throw new AccessDeniedException("权限不足");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}

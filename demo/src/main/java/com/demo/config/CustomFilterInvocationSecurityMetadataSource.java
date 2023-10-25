package com.demo.config;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.demo.dao.MenuMapper;
import com.demo.entity.Menu;
import com.demo.entity.Role;

/**
 * @Title CustomFilterInvocationSercurityMetadataSource.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年10月21日 下午9:28:35 
 * 实现动态配置权限
 */
@Component
public class CustomFilterInvocationSecurityMetadataSource  implements FilterInvocationSecurityMetadataSource{
	AntPathMatcher antPathMatcher=new AntPathMatcher();
	@Autowired
	MenuMapper menuMapper;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		String requesetUrl=((FilterInvocation) object).getRequestUrl();//返回当前请求的URL
		List<Menu>allMenus=menuMapper.getAllMenus();
		for(Menu menu:allMenus) {
			if(antPathMatcher.match(menu.getPattern(), requesetUrl)) {//ant 风格的URL匹配
				List<Role>roles=menu.getRoles();//匹配后，获得路径对应的的角色信息。
				String []roleArr=new String[roles.size()];
				for(int i=0;i<roleArr.length;i++) {
					roleArr[i]=roles.get(i).getRoleName();
				}
				//有匹配的返回对应的权限角色信息
				return SecurityConfig.createList(roleArr);
				}
		}
		//返回默认的权限信息
		return SecurityConfig.createList("ROLE_LOGIN");
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 *类对象是否支持校验
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}

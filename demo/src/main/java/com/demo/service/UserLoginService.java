package com.demo.service;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.dao.UserLoginMapper;
import com.demo.entity.UserLogin;

@Service("userLoginService")
public class UserLoginService implements UserDetailsService{
	@Resource
	UserLoginMapper userLoginMapper;
	
	/**
	 *有些是将User 与权限分开的，在组合成一个整体的。这里直接UserLogin继承UserDetails，包含了Auth这部分的内容，
	 *有的是auser 单独一个类，专门储存用户名密码等信息，再在UserLogin 加载auser,继承UserDetails
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("before loadByUserName");
		UserLogin user=userLoginMapper.loadByUsername(username);//数据库根据用户名查询用户的信息，这个用户名需要唯一性。
		//System.out.println("after loadByUserName"+user.getUsername());
		if(user==null) {throw new UsernameNotFoundException("账户名不存在");}
		System.out.println(user.toString());
		//user.setRoles(userLoginMapper.getUserRolesByUid(user.getUserId()));
		System.out.println("after    set roless");
		//UserLogin uslong=new UserLogin();
	    //uslong.setUsername(user.getUsername());
		//uslong.setPassword(user.getPassword());
		//return	new User(user.getUsername(),user.getPassword(),user.getAuthorities());
		return user;//这里需要返回一个User 或者子类，这个user为org.springframework.security.core.userdetails.User;
	}

}

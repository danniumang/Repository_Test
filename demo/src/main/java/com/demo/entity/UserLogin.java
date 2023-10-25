package com.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class UserLogin implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String userId;
	private String username;
	private String password;
	private boolean enabled;
	private boolean locked;

	//@JsonBackReference
	private List<Role>roles;
//上面的内容可以单独成立一个类，这里直接引用即可。下面直接是UserDetails中的一些需要实现的方法。
	/**
	 *返回授予用户的权限,继承UserDetails中的方法，这里通过user访问数据user对应的角色，返回这里并保存在Author当中。
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<SimpleGrantedAuthority>authorities=new ArrayList<>();
		
		  for(Role role:roles) { authorities.add(new
		  SimpleGrantedAuthority(role.getRoleName())); }
		  
		 
		// authorities.add(new
		//		 SimpleGrantedAuthority("admin"));
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
    //账户是否过期，本次数据库、以及字段没有设定，则使用了默认的永不过期。可以添加过期的控制
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
    //是否锁定
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !locked;
	}
    //密码是否过期，notExpired 不过期
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
     //用户是否禁用，设定了字段
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	public String getUserId() {
		return userId;
	}

	/*
	 * @Override public String toString() { return "username: "+username; }
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	//@JsonManagedReference
	
	  public List<Role> getRoles() { return roles; }
	  
	  public void setRoles(List<Role> roles) { this.roles = roles; }
	 

	public boolean isLocked() {
		return locked;
	}
}

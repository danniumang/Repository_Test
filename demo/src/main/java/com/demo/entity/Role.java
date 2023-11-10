package com.demo.entity;

import java.io.Serializable;
import java.util.List;


public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String roleId;
	private String roleName;
	private String describe;
	
	private List<UserLogin> userLogins;
	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String dESCRIBE) {
		describe = dESCRIBE;
	}
	public List<UserLogin> getUserLogins() {
		return userLogins;
	}
	public void setUserLogins(List<UserLogin> userLogins) {
		this.userLogins = userLogins;
	}
}

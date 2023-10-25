package com.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)//注释，可用于抑制属性的序列化（在序列化期间）或忽略读取的JSON属性的处理（在反序列化期间）。
public class Menu {
	private int mid;
	private String pattern;
	private List<Role>roles;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public List<Role> getRoles() {
		// TODO Auto-generated method stub
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "{ mid="+mid+" pattrern="+pattern+"}";
	}

}

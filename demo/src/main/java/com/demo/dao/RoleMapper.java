package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.entity.Role;
@Mapper
public interface RoleMapper {
	//public int insertRole(Role role);
	public List<Role> selectAllRoles();
	//public  Role  selectOneRoleById(String id);
	
	//public int deleteRoleById(String id);
	//public int updateRoleById(String id);
}

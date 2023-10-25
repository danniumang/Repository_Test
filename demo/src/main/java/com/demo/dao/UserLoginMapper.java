package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.demo.entity.Role;
import com.demo.entity.UserLogin;
@Repository("userLoginMapper")
@Mapper
public interface UserLoginMapper {
	UserLogin loadByUsername(String username);
	List<Role>getUserRolesByUid(String id);

}

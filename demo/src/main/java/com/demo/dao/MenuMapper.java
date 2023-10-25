package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.entity.Menu;
@Mapper
public interface MenuMapper {
	List<Menu>getAllMenus();

}

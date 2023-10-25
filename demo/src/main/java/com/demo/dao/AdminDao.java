package com.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.demo.entity.Auser;
@Repository("adminDao")
@Mapper
public interface AdminDao {
     public Auser login(Auser auset);
}

package com.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.demo.entity.Auser;

@Mapper
public interface AdminMapper {
     public Auser login(Auser auset);
}

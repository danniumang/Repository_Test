package com.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
public interface AdminOrderDao {

	List<Map<String, Object>> orderInfo();

	void deleteOrderDetail(String orderDetailid);

	void deleteOrderBase(String orderid);

}

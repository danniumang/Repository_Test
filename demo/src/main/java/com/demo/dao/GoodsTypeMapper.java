package com.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.demo.entity.GoodsType;

/*@Repository("goodsTypeDao")*/
@Mapper
public interface GoodsTypeMapper {
    public List<GoodsType> selectGoodsType();
    public int addType(String goosTypename);
    public int deleteType(String goosTypename);
	public List<GoodsType> selectGoodsByType(String goosTypename);
}


/** 
* @author daliu E-mail: 1515783415@qq.com
* @version 创建时间：2021年5月26日 下午9:52:19 
* 说明 
*/ 
 
package com.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.demo.entity.Goods;
//import com.demo.entity.GoodsType;

/**
 * @author liu
 * @version 创建时间：2021年5月26日 下午9:52:19 
 */
/*
 * @Repository("goodsDao")
 * 
 * @Mapper
 */
public interface GoodsDao {
     public int addGoods(Goods goods);
     public List<Goods>selectGoods();
     public int updateGoodsById(Goods goods);
     public Goods selectGoodsById(String goodid);
     public List<Goods>selectGoodsByType(String gstypeid);
     public List<Goods>selectAllGoodsByPage(Map map);
    // public List<GoodsType>goodsType();
	public List<Map<String, Object>> selectCartGoods(String goodid);
	public List<Map<String, Object>> selectOrderdetailGoods(String str);
	public int deleteGoods(List<String> list);
	public List<Map<String, Object>> selectFocusGoods(String goodid);
	public void deleteAGoods(String goodid);
}

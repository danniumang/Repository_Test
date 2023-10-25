package com.demo.service.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.demo.entity.Goods;

public interface GoodsService {
    public String addorUpdateGoods(Model model);

	public String addorUpdateGoods(Goods goods, HttpServletRequest request,String updateAct);
	
	public String selectGoods(Model model,Integer pageCur,String act);
	
	public String selectAGoods(Model model,String goodid,String act);

	public String deleteGoods(String[] goodids, Model model);

	public String deleteAGoods(String goodid, Model model);
}

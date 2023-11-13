package com.demo.service.imp;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.demo.dao.GoodsTypeMapper;
import com.demo.entity.GoodsType;
import com.demo.service.GoodsTypeService;

@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {
	private static final Log logger = LogFactory.getLog(GoodsTypeServiceImpl.class);
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;

	@Override
	public String toAddType(Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("allTypes", goodsTypeMapper.selectGoodsType());
		logger.info(goodsTypeMapper.selectGoodsType().toString());
		return "addType";
	}

	@Override
	public String addType(String typename, Model model, HttpSession session) {
		// TODO Auto-generated method stub
		//String error=null;
		
		goodsTypeMapper.addType(typename);
		session.setAttribute("goodsType", goodsTypeMapper.selectGoodsType());
		//error.split("/");
		return "forward:/adminType/toAddType";// 跳转至controller中的action
	}

	@Override
	public String deleteType(String gstype, Model model) {
		// TODO Auto-generated method stub
		if (goodsTypeMapper.selectGoodsByType(gstype).size() > 0) {
			model.addAttribute("msg", "类型被使用。不允许删除");
			return "forward:/adminType/toDeleteType";
		}
		if (goodsTypeMapper.deleteType(gstype) > 0) {
			model.addAttribute("msg", "类型删除成功");
		}
		return "forward:/adminType/toDeleteType";
	}

	@Override
	public String toDeleteType(Model model) {
		// TODO Auto-generated method stub
		model.addAttribute("allTypes", goodsTypeMapper.selectGoodsType());
		return "deleteType";
	}

	@Override
	public List<GoodsType> selectSessionGoodsType() {
		// TODO Auto-generated method stub
		return goodsTypeMapper.selectGoodsType();
	}

}

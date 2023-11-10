package com.demo.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.GoodsTypeService;

@RestController
@RequestMapping("/adminType")
public class AdminGoodsTypeController  {
	private static final Log logger =LogFactory.getLog(AdminGoodsTypeController.class);
	@Autowired
     private GoodsTypeService goodsTypeService;
	@RequestMapping("/toAddType")
	public String toAddType(Model model) {
		return goodsTypeService.toAddType(model);
	}
	@RequestMapping("/addType")
	public String addType(String typeName,Model model,HttpSession session) {
		logger.info("****************"+typeName);
		return goodsTypeService.addType(typeName, model, session);
		
	}
	@RequestMapping("/toDeleteType")
	public String toDeleteType(Model model) {
		return goodsTypeService.toDeleteType(model);
	}
	@RequestMapping("/deleteType")
	public String deleteType(String gstypeid,Model model) {
		logger.info("$$$$$$$$$$$$$$"+gstypeid);
		
		return goodsTypeService.deleteType(gstypeid, model);
	}
}

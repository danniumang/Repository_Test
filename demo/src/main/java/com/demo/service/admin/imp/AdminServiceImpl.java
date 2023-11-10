package com.demo.service.admin.imp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;


import com.demo.dao.AdminMapper;
import com.demo.entity.Auser;
import com.demo.service.admin.AdminService;
@Service
//@Transactional
public class AdminServiceImpl implements AdminService {
	
	@Autowired
    private AdminMapper adminDao;
	@Override
	public String login(Auser auser, Model model, HttpSession session) {
		// TODO Auto-generated method stub
		if(adminDao.login(auser)!=null) {
			session.setAttribute("auser", auser);
			//session.setAttribute("goodsType", adminTypeDao.selectGoodsType());
			return "admin/main";
			
		}
		model.addAttribute("msg","用户或密码错误");
		
		return "admin/login";
	}

}

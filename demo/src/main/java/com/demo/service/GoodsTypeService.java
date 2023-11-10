package com.demo.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.demo.entity.GoodsType;

public interface GoodsTypeService {
    public String toAddType(Model model);
    public String addType(String typename,Model model,HttpSession session);
    public String deleteType(String gstype,Model model);
    public String toDeleteType(Model model);
    public List<GoodsType>selectSessionGoodsType();
}

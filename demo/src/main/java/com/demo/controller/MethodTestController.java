package com.demo.controller;
/***
 *@author :liu
 *@date :2022/7/13
 **
 */

import com.demo.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/****
 *@Description: 测试Service 中的注解
 *@author liu
 *@date 2022/7/13 12:24
 *****/
@RestController
@RequestMapping("/good")

public class MethodTestController {
    @Autowired
    MethodService methodService;
    @GetMapping("/ad")
    public String admin(){
        return methodService.admin();
    }
    @GetMapping("/daa")
    public String dba(){
        return methodService.dba();
    }
    @GetMapping("/uee")
    public String user(){
        return methodService.user();
    }
}

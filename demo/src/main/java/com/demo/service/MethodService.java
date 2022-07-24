package com.demo.service;
/***
 *@author :liu
 *@date :2022/7/13
 **
 */

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/****
 *@Description: service中测试方法注解
 *@author liu
 *@date 2022/7/13 12:18
 *****/
@Service
public class MethodService {
    @Secured("ROLE_admin")//这里的admin需要加上前缀
    public String admin(){
        return  "hello admin";
    }
    @PreAuthorize("hasRole('admin') and hasRole('dba')")//不需要前缀
    public String dba(){
        return "hello dba";
    }
    @PreAuthorize("hasAnyRole('admin','dba','user')")
    public String user(){
        return "hello user";
    }
}

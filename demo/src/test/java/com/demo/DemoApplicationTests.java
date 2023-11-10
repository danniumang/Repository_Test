package com.demo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.service.UserService;



@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {
	/*
	 * @Autowired UserService userService;
	 * 
	 * @Test void contextLoads() { BCryptPasswordEncoder ec = new
	 * BCryptPasswordEncoder(10); String ecp = "123"; String ecpw = ec.encode(ecp);
	 * System.out.println(ecpw); Assert.assertTrue(ecp, false); }
	 * 
	 * @Test void show2() { userService.deleteUserById(1);
	 * 
	 * }
	 */

}

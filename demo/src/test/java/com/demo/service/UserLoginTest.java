package com.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.entity.UserLogin;
//@RunWith(SpringRunner.class)
@SpringBootTest(/* classes=UserLoginTest.class */)
class UserLoginTest {
	 @Autowired
	 UserLoginService  userLoginService;
	@Test
	void test() {
		UserLogin user=(UserLogin) userLoginService.loadUserByUsername("liuyujie");
	System.out.println(user);
	
	}

}

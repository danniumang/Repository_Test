package com.demo;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.demo.service.UserService;

//@RunWith(SpringRunner.class)
/**
 * @Title UserServiceTest.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年10月15日 下午6:21:57 在测试的使用使用@SpringBootTest
 *          会自动加载appxxx.xml文件，这里测试service autowired自动装载数据
 */
@SpringBootTest
class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	public void good() {
		String s = userService.getUserById(2);
		Assert.assertNotNull(s);
	}

	@Test
	public void contextLoads() {
		BCryptPasswordEncoder ec = new BCryptPasswordEncoder(10);
		String ecp = "123";
		String ecpw = ec.encode(ecp);
		System.out.println(ecpw);
		Assert.assertTrue(ecp, false);
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}

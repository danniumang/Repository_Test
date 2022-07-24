package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		BCryptPasswordEncoder ec=new BCryptPasswordEncoder(10);
		String  ecp="123";
		String ecpw=ec.encode(ecp);
		System.out.println(ecpw);
	}

}

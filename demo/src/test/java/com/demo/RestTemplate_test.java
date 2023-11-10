package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)//DEFINED_PORT(内嵌，+定义端口)   MOCK(applicationContext)-》这个是默认的设置  RANDOM_PORT(random_port 内嵌 接口随机)  NODE(不适用web测试)
class RestTemplate_test {
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void test() {
		ResponseEntity<String>hello=restTemplate.getForEntity("/hello?name", String.class,"liu");
		System.out.println(hello.getBody());
	}

}

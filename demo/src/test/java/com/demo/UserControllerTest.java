package com.demo;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.demo.entity.Auser;
import com.demo.service.admin.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @Title UserControllerTest.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月7日 下午7:43:26 
 * MockMvc 提供http请求模拟
 */
@SpringBootTest
class UserControllerTest {

	@Autowired
	AdminService adminService;

	
	@Autowired
	WebApplicationContext wac;
	MockMvc mockMvc;
	Auser auser = new Auser();

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		auser.setAname("liu");
		auser.setApwd("123");
	}

	@Test
	public void test1() throws Exception { ///
		ObjectMapper om = new ObjectMapper(); //
		String s = om.writeValueAsString(auser);
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/adminlogin").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("name", "liu"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
	}

	/**
	 *@throws Exception
	 *MockMvcRequestBuilders.get()\post()
	 *
	 *MediaType.APPLICATION_JSON
	 */
	@Test
	public void test2() throws Exception {
		ObjectMapper om = new ObjectMapper();// com.fasterxml.jackson.databind.ObjectMapper
		String s = om.writeValueAsString(auser);//Method that can be used to serialize any Java value as a String
		System.out.println(s+" and "+auser.toString());
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/adminlogin").contentType(MediaType.APPLICATION_JSON).content(s))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
	}

	

}

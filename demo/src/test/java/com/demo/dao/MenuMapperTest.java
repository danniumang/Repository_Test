package com.demo.dao;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.component.JmsComponent;
import com.demo.entity.Menu;
import com.demo.entity.Message;
@SpringBootTest
class MenuMapperTest {
	@Autowired
	MenuMapper  menuMapper;
	@Autowired
	JmsComponent jmsComponent;
	@Test
	void testGetAllMenus() {
		//fail("Not yet implemented");
		List<Menu> menus=menuMapper.getAllMenus();
		System.out.println(menus.get(0));
		Assert.assertNotNull(menus);
		//menus.stream().forEach(x->x.toString());
	}
	@Test
	public void contextLoads() {
		Message msg=new Message();
		msg.setName("jmsTest");
		msg.setContent("this is jms content");
		msg.setDate(new Date());
		jmsComponent.send(msg);
	}

}

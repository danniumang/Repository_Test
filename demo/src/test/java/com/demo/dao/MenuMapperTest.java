package com.demo.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.entity.Menu;
@SpringBootTest
class MenuMapperTest {
	@Autowired
	MenuMapper  menuMapper;
	@Test
	void testGetAllMenus() {
		//fail("Not yet implemented");
		List<Menu> menus=menuMapper.getAllMenus();
		System.out.println(menus.get(0));
		Assert.assertNotNull(menus);
		//menus.stream().forEach(x->x.toString());
	}

}

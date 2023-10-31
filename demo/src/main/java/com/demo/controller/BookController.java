package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Book;

/**
 * @Title BookController.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年10月31日 上午1:33:00 
 */
@RestController
public class BookController {
	@Autowired
	RedisTemplate<?, ?> redisTemplate;
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@GetMapping("/test1")
	public void test1() {
		ValueOperations ops=redisTemplate.opsForValue();
		Book book=new Book();
		book.setBookName("java shijian");
		book.setAuthor("liu");
		book.setBookId(1);
		ops.set("b1", book);
		System.out.println(ops.get("b1"));
		ValueOperations<String,String>ops2=stringRedisTemplate.opsForValue();
		ops2.set("k1", "k1");
		System.out.println(ops2.get("k1"));
		
		
	}

}

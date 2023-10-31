package com.demo.service;



import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.entity.UserLogin;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
//@RunWith(SpringRunner.class)
@SpringBootTest(/* classes=UserLoginTest.class */)
class UserLoginTest {
	 @Autowired
	 UserLoginService  userLoginService;
	@Test
	void test() throws FileNotFoundException {
		

        Jedis jd=new Jedis("192.168.242.130",8001);
        jd.auth("123@456");
        jd.set("user","123"); InputStream in=new BufferedInputStream(new
		  FileInputStream(new
		  File("C:\\Users\\Administrator\\Pictures\\MSI Wallpaper\\MSI.png")));
		  jd.set("liuu", in.toString()); System.out.println(jd.get("user")); 
		 // JedisPool pool=new JedisPool("192.168.242.130",6379,"default","liuyujie");//String user-> default 
		//  Jedis jds=pool.getResource(); jds.set("user","456");
		  System.out.println(jd.get("user"));
		 
     //  JedisPooled  jpool=new JedisPooled ("192.168.242.130",6379,"default","liuyujie");
		
		UserLogin user=(UserLogin) userLoginService.loadUserByUsername("liuyujie");
	System.out.println(user);
	
	
	}

}

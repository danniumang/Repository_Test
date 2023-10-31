package com.demo;


import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.demo"})
@EnableCaching
@MapperScan("com.demo.dao")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		//SpringApplicationBuilder builder=new SpringApplicationBuilder(DemoApplication.class);
		//builder.run(args);
		
	}
	//这个Bean居然是放在 这里的，不是放在Config 修饰的类中
@Bean
Queue queue() {
	return new ActiveMQQueue("amq");
}
}

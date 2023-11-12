package com.demo;


import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Title DemoApplication.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月8日 下午11:54:40 
 * rabbitmq activemq 同一台服务器上5672端口默认冲突，一般使用其一即可
 * springboot 默认对jpa,jdbc,mybatis开启了事务。有些接口没有默认开启事务，需要手动开启事务。声明式事务的支持
 */
@SpringBootApplication(scanBasePackages = {"com.demo"})//this setting is an alias for @ComponentScan only
@EnableCaching
@EnableScheduling //开启定时任务，这个是spring提供的定时任务
@EnableBatchProcessing//batch 批量处理
@MapperScan("com.demo.dao")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		//SpringApplicationBuilder builder=new SpringApplicationBuilder(DemoApplication.class);
		//builder.run(args);
		
	}
	//这个Bean居然是放在 这里的，不是放在Config 修饰的类中
@Bean
Queue queue() {//activeMQqueue ---rabbitMq 两者选其一。 这里也是queue.这里返回的amq,应该也可以添加其他的bmq cmq 等Bean。根据业务要求将不同的数据添加到不同的queue中。功能相关性。
	return new ActiveMQQueue("amq");
}
}

package com.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title RabbitHeaderConfig.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月5日 下午11:03:13 
 * 这里匹配的可以添加到具体的queue中，没有匹配的没有添加到queue中，是否默认的queue保存或者直接丢掉？其他的类型queue是否同样如此。
 * 根据消息的Header消息路由到具体的Queue
 */
@Configuration
public class RabbitHeaderConfig {
	public final static String HEADERNAME="liu-hanner";
	@Bean
	HeadersExchange headersExchange() {
		return new HeadersExchange(HEADERNAME,true,false);
	}
	@Bean
	Queue queueName() {
		return new Queue("name-queue");
	}
	@Bean
	Queue queueAge() {
		return new Queue("age-queue");
	}
	@Bean
	Binding bingName() {
		Map<String,Object>map=new HashMap<>();
		map.put("name", "liu");
		return BindingBuilder.bind(queueName()).to(headersExchange()).whereAny(map).match();
	}
	@Bean
	Binding bingAge() {
		return BindingBuilder.bind(queueAge()).to(headersExchange()).where("age").exists();
	}
}

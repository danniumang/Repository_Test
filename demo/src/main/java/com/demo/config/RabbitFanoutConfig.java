package com.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title RabbitFanoutConfig.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月5日 下午10:13:31 
 * FanoutExchange,将接受的消息绑定到所有queue上（无视routingkey），
 * 所有消息消费者可以获得发送到FanoutExchange上的所有数据，
 * 如果后续过滤数据，可以获得想要的数据
 */
@Configuration
public class RabbitFanoutConfig {
	public final static String FANOUTNAME="liu-fanout";
	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUTNAME,true,false);
	}
	@Bean
	Queue queueOne() {
		return new Queue("queue-one");
	}
	@Bean
	Queue queueTwo() {
		return new Queue("queue-two");
	}
	@Bean
	Binding bindingOne() {
		return BindingBuilder.bind(queueOne()).to(fanoutExchange());
	}
	@Bean
	Binding bindingTwo() {
		return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
	}
	
}

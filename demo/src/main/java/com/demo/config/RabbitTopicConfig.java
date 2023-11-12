package com.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title RabbitTopicConfig.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月5日 下午10:33:53 
 * 根据routingkey将消息绑定到一个或者多个Queue上，根据with的匹配规则进行，规则设定好后，自动的匹配即可。
 */
@Configuration
public class RabbitTopicConfig {
	public final static String TOPICNAME="liu-topic";
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(TOPICNAME,true,false);
	}
	@Bean
	Queue xiaomi(){
		return new  Queue("xiaomi");
	}
	@Bean
	Queue huawei() {
		return new Queue("huawei");
	}
	@Bean
	Queue phone() {
		// TODO Auto-generated method stub
		return new Queue("phone");
	}
	@Bean
	Queue meizu() {
		return new Queue("meizu");
	}
	@Bean
	Binding meizuBinding() {
		return BindingBuilder.bind(meizu()).to(topicExchange()).with("meizu.#");
	}
	@Bean
	Binding xiaomiBingding() {
		return BindingBuilder.bind(xiaomi()).to(topicExchange()).with("xiaomi.#");//以xiaomi开头的routingkey 则绑定到xiaomiQueue上
	}
	@Bean
	Binding huaweiBinding() {
		return BindingBuilder.bind(huawei()).to(topicExchange()).with("huawei.#");
	}
    @Bean
    Binding phoneBinding() {
    	return BindingBuilder.bind(phone()).to(topicExchange()).with("#.phone.#");//routingkey中包含phone则绑定到phonequeue 上，及时已经绑定到了上面的其中一个。
    }
	
}

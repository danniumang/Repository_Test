package com.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title RabbitDirectConfigu.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月3日 下午8:19:17
 * directExchange  
 */
/**
 * @Title RabbitDirectConfigu.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月5日 下午9:48:28 
 * 一个directExchange可以将多个queue 绑定到同一个routingKey上，或者绑定到分别绑定到不同的可以上。一般根据功能的不同分别放在不同的queue&routingKey形成对应关系，名称最好可以直接区分。
 * directExchange 是个中间关系，连接器的功能。
 * 中间键 储存转发消息。  线路层协议规范  rabbit  amqp
 */
@Configuration
public class RabbitDirectConfigu {
	public final static String DIRECTNAME="liu-direct";// name of DirectExchange
	@Bean
	Queue queue1() {//如果在使用 rabbit 而不使用其他的activeMq,这里改成queue,使用其他时，这里的queu需要改名
		return new Queue("hello-queue");
	}
	@Bean
	Queue queue2() {//如果在使用 rabbit 而不使用其他的activeMq,这里改成queue,使用其他时，这里的queu需要改名
		return new Queue("word-queue");
	}
    /**
     *@return
     *这里使用了directExchange
     */
    @Bean
    DirectExchange directExchange() {
    	return new DirectExchange(DIRECTNAME,true,false);// name of DirectExchange ，重启有效，久未用删除？
    }
    @Bean
    Binding binding() {
    	return BindingBuilder
    			.bind(queue1())//queue
    			.to(directExchange())//change
    			.with("direct");//String routingKey
    }
    /**
     *@return
     *queue2   direct1 一对一？
     */
    @Bean
    Binding binding1() {
    	return BindingBuilder
    			.bind(queue2())//queue
    			.to(directExchange())//change
    			.with("direct1");//String routingKey
    }
}

package com.demo.config;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.demo.entity.Message;

/**
 * @Title JmsComponent.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年10月28日 下午3:52:37 
 * 消息体需要根据具体的点情况抽象成一种公共的类，可以覆盖所有的内容的一种框架，通过其中的字段判断类型等，发布订阅，主题，一类，某个具体的id等，具体的某一个对象接受。
 */
@Component
public class JmsComponent {
	@Autowired
	JmsMessagingTemplate messagingTemplate;//jms 消息发送模版
	@Autowired
	Queue queue;
	
	/**
	 *@param message
	 *发送消息
	 */
	public void send(Message message) {
		messagingTemplate.convertAndSend(this.queue, message);
	}
	/**
	 *@param msg
	 *消息消费者
	 */
	@JmsListener(destination = "amq")
	public void receive(Message msg) {
		System.out.println("Receive: "+msg);
	}

}

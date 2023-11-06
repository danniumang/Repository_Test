package com.demo.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {
	@RabbitListener(queues="phone")
	public void handler1(String msg) {
		System.out.println("phone queue get : "+msg);
	}
	@RabbitListener(queues="xiaomi")
	public void handler2(String msg) {
		System.out.println("xiaomi queue get : "+msg);
	}
	@RabbitListener(queues="huawei")
	public void handler3(String msg) {
		System.out.println("huawei queue get : "+msg);
	}

}

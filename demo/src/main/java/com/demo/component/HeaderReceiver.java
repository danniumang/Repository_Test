package com.demo.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeaderReceiver {
	@RabbitListener(queues="name-queue")
	public void hander1(byte[]msg) {
		System.out.println("name queue get byte msg HeaderReceiver:name:"+new String (msg,0,msg.length));
	}
	@RabbitListener(queues="age-queue")
	public void hander2(byte[]msg) {
		System.out.println("age queue get byte msg HeaderReceiver:age:"+new String (msg,0,msg.length));
	}

}

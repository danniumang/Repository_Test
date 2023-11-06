package com.demo.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {
	/**
	 *@param msg
	 *在这里对hello-queue中的消息进行处理，不同的业务需要编写不同的代码，
	 *一般同一个queue的数据来自同一个功能，
	 *如果时统一的处理接口，具体业可能需要进一步的规划，识别消息的处理的功能了。
	 */
	@RabbitListener(queues="hello-queue")//消息消费方法  hello-queue，
	public void handler1(String msg) {
		System.out.println("DirectReceiver handler1: "+msg);
	}
	@RabbitListener(queues="word-queue")//消息消费方法  word-queue
	public void handler2(String msg) {
		System.out.println("DirectReceiver handler2: "+msg);
	}
}

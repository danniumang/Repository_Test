package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.config.RabbitFanoutConfig;
import com.demo.config.RabbitHeaderConfig;
import com.demo.config.RabbitTopicConfig;
@SpringBootTest
class RabbitmqApplicationTests {
	@Autowired
	RabbitTemplate rabbitTemplate;

	@Test
	void Directtest() {
		rabbitTemplate.convertAndSend("hello-queue","hello direct!");
		//String routingKey, Object object  这里的消息能容时Object，说明这里的消息体可以是任何的数据类型了。这里就是具体要将需要发送到消息队列中的最后发送的一步功能，
		rabbitTemplate.convertAndSend("word-queue","word-queue!,this is for second to test");
	}
	@Test
	void FanoutTest() {
		//RabbitFanoutConfig.FANOUTNAME  自己定义的Fanoutname,这里的routingkey ==null
		rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUTNAME, null, "hello this is fanout to test");
	}
	@Test
	void TopicTest() {
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "xiaomi.news", "小米的新闻..");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "xiaomi.phone", "小米手机");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "xiaomi.发布", "小米的发布");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "huawei.news", "华为的新闻");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "huawei.message", "华为的新闻..");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "huawei.phone", "华为手机");
		rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME, "phone.news", "手机新闻");
	}
	@Test
	void HeaderTest() {
		Message nameMsg=MessageBuilder.withBody("hello header! name-queue".getBytes()).setHeader("name", "liu").build();
		Message ageMsg=MessageBuilder.withBody("hello header! age-queue".getBytes()).setHeader("age", 45).build();
		Message pwdMsg=MessageBuilder.withBody("hello header! pwd-queue".getBytes()).setHeader("pwwd", "ps").build();
		rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME,null,ageMsg);
		rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME,null,nameMsg);
		rabbitTemplate.send(RabbitHeaderConfig.HEADERNAME,null,pwdMsg);
	}

}

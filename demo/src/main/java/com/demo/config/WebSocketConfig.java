package com.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
/**
 * @Title WebSocketConfig.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年10月24日 下午9:29:00 
 * stomp  协议
 */
@Configuration
@EnableWebSocketMessageBroker  //开启webSocket的代理
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{
	
	/*
	 * public void configureWebSocketTransport(WebSocketTransportRegistration
	 * registry) {
	 * 
	 * }
	 */
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/char").withSockJS();//Sockjs 解决浏览器对WebSocket 的兼容性问题
	}
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");//简单代理，增加一个前缀，方便识别具体的目的地。消息代理前缀
		registry.setApplicationDestinationPrefixes("/app");//app 前缀的destination 可以被@MessageMapping注解的方法处理
	}
}

package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;*/
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
/**
 * @Title WebSocketConfig.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年10月24日 下午9:29:00 
 * stomp  协议  WebSocket
 */
@Configuration
//@EnableWebSocketMessageBroker  //开启webSocket的代理  1  可能是前端技术不过关，数据传递不到后端，全局变量，局部变量
public class WebSocketConfig //implements WebSocketMessageBrokerConfigurer  2
{
	
	/*
	 * public void configureWebSocketTransport(WebSocketTransportRegistration
	 * registry) {
	 * 
	 * }
	 */
	//基于  WebSocketMessageBrokerConfigurer  3
	/*
	 * @Override public void registerStompEndpoints(StompEndpointRegistry registry)
	 * { registry.addEndpoint("/char").withSockJS();//Sockjs 解决浏览器对WebSocket 的兼容性问题
	 * }
	 * 
	 * @Override public void configureMessageBroker(MessageBrokerRegistry registry)
	 * { registry.enableSimpleBroker("/topic");//简单代理，增加一个前缀，方便识别具体的目的地。消息代理前缀
	 * registry.setApplicationDestinationPrefixes("/app");//app 前缀的destination
	 * 可以被@MessageMapping注解的方法处理 }
	 */
	//方法2 weksocket 的实现,测试的时候居然报错，这个只有在使用默认自带的的server可以使用，其他外部的server时是不需要使用的，JUNit 应该是第三方的运行环境。，测试时关闭，其他情况开启，正式环境有待校验
	
	
	
	  @Bean public ServerEndpointExporter serverEndpointExporter() { return new
	  ServerEndpointExporter(); }
	 
}

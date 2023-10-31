package com.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @Title RedisConfig.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月1日 上午1:46:25
 * reids 集群配置 
 */
@Configuration
//@ConfigurationProperties("spring.redis.cluster")
public class RedisConfig {
	 @Value("${spring.redis.cluster.nodes}")
	List<String>nodes12;
	 @Value("${spring.redis.password}")
	 String pd;
	 @Value("${spring.redis.jedis.pool.max-active}")
	 int maxtotal;
	 @Value("${spring.redis.jedis.pool.max-idle}")
	 int maxidle;
	 @Value("${spring.redis.jedis.pool.max-wait}")
	 String maxwaitmillis;
	 @Value("${spring.redis.jedis.pool.min-idle}")
	 int minidle;
	//JedisPoolConfig poolConfig;
	@Bean
	RedisClusterConfiguration redisClusterConfiguration() {
		RedisClusterConfiguration configuration=new RedisClusterConfiguration();
		
		configuration.setPassword(RedisPassword.of(pd));
		List<RedisNode>nodes=new ArrayList<>();
		for(String s:nodes12) {
			int index=s.indexOf(":");
			System.out.println("node12 "+index);
			nodes.add(new RedisNode(s.substring(0, index),Integer.parseInt(s.substring(index+1))));
		}
		
		configuration.setClusterNodes(nodes);
		return configuration;
		
	}
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory=new JedisConnectionFactory(redisClusterConfiguration(),new JedisPoolConfig());
		return factory	;
	}
	@Bean
	RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate =new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		return redisTemplate;
		
	}
	@Bean
	StringRedisTemplate stringRedisTemplate() {
		StringRedisTemplate stringRedisTemplate=new StringRedisTemplate(jedisConnectionFactory());
		stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
		stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
		return stringRedisTemplate;
	}
	
	public List<String> getNodes12() {
		return nodes12;
	}
	public void setNodes12(List<String> nodes12) {
		this.nodes12 = nodes12;
	}
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	public int getMaxtotal() {
		return maxtotal;
	}
	public void setMaxtotal(int maxtotal) {
		this.maxtotal = maxtotal;
	}
	public int getMaxidle() {
		return maxidle;
	}
	public void setMaxidle(int maxidle) {
		this.maxidle = maxidle;
	}
	public String getMaxwaitmillis() {
		return maxwaitmillis;
	}
	public void setMaxwaitmillis(String maxwaitmillis) {
		this.maxwaitmillis = maxwaitmillis;
	}
	public int getMinidle() {
		return minidle;
	}
	public void setMinidle(int minidle) {
		this.minidle = minidle;
	}

}

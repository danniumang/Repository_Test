package com.demo.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @Title RedisCacheConfig.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月1日 上午1:39:52 
 * 集群缓存配置，优先配置redis 集群在配置集群缓存
 * 这里建立redisCacheManager 
 */
@Configuration
public class RedisCacheConfig {
	@Autowired
	RedisConnectionFactory conFactory;//集群配置中已经处理，这里直接使用即可
	@Bean
	RedisCacheManager redisCacheManager() {
		Map<String,RedisCacheConfiguration>configMap=new HashMap<>();
		RedisCacheConfiguration redisCacheConfig=RedisCacheConfiguration
				.defaultCacheConfig()
				. prefixCacheNameWith("liu:")//缓存前缀， liu:c1::1  liu:c1::3
				.disableCachingNullValues()//空值不缓存
				.entryTtl(Duration.ofMinutes(20));
		configMap.put("c1", redisCacheConfig);
		RedisCacheWriter cacheWriter=RedisCacheWriter.nonLockingRedisCacheWriter(conFactory);
		RedisCacheManager redisChacheManager=new RedisCacheManager(cacheWriter,RedisCacheConfiguration.defaultCacheConfig(),configMap);
		
		return redisChacheManager;
		
	}

}

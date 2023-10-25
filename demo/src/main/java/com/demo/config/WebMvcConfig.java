package com.demo.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.demo.interceptor.MyInterceptor;

//import com.demo.interceptor.MyInterceptor;

/**
 * @Title WebMvcConfig.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年10月12日 上午8:45:53 
 * 自定义配置，boot 基本都有默认值，覆盖大部分的情况
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	/**
	 *拦截器，拦截路径以及处理类
	 */
	
	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
	  registry.addInterceptor(new MyInterceptor()) .addPathPatterns("/**")
	  .excludePathPatterns("/hello");
	  //or other path 
	  }
	 
	/**
	 *跨域请求设置
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/usr/**")
		.allowedHeaders("*")
		.allowedMethods("*")
		.maxAge(1800)
		.allowedOrigins("http://localhost:8081");
	}
	/**
	 *过滤静态资源
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**")
		.addResourceLocations("classpath:/static/");
	}
	/**
	 *格式转化，具体配置  这里一般适用于 fastJson,其他两项不用配置
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
	}
	/**
	 *设置可以直接访问的路径，不通过控制器
	 *security 控制，等等相关功能
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/index").setViewName("index");
		//registry.addStatusController(null, null);
		//registry.addRedirectViewController(null, null);
	}
	
	
}

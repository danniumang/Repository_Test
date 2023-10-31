package com.demo.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.demo.service.UserLoginService;
@Configuration
/**
 * prePostEnabled = true解锁@PreAuthorize()、@PostAuthorize()
 * PreAuthorize()：在方法执行前进行验证
 * PostAuthorize()：在方法执行后进行验证
 * securedEnabled = true：解锁@Secured
 * 权限控制
 */
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
	private Logger LOG = LoggerFactory.getLogger(MyWebSecurityConfig.class);
	@Autowired
	UserLoginService userLoginService;
	//角色的继承，权限的继承，本身不具有特别的灵活性,后期动态配置权限
	/*
	 * @Bean RoleHierarchy roleHierarchy() { RoleHierarchyImpl roleHierarchy=new
	 * RoleHierarchyImpl(); String hierarchy="ROLE_dba > ROLE_admin >ROLE_user";
	 * roleHierarchy.setHierarchy(hierarchy); return roleHierarchy;
	 * 
	 * }
	 */
	//有关动态的规划权限操作。1
	@Bean
	CustomFilterInvocationSecurityMetadataSource cfisms() {
		return new CustomFilterInvocationSecurityMetadataSource();
	}
	//动态权限验证，是否有相应的权限。2
	@Bean
	CustomAccessDecisionManager cadm() {
		return new CustomAccessDecisionManager();
	}
	//密码加密
	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder(10);

		//NoOpPasswordEncoder.getInstance();//未加密
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		 LOG.info(userLoginService.toString());
		auth.userDetailsService(userLoginService);//数据库登录
		
		//内存用户密码登录
		/*
		 * auth.inMemoryAuthentication() .withUser("root").password(
		 * "$2a$10$sAmdadhc46uUoA9XHD0Diu8sZooyHHdc0q/8beiL0hNjKfcfrt0PO").roles("admin"
		 * ,"dba") //这里的roles 不需要加入前缀 ROLE_,会自动加入ROLE_ 的 .and()
		 * .withUser("admin").password(
		 * "$2a$10$sAmdadhc46uUoA9XHD0Diu8sZooyHHdc0q/8beiL0hNjKfcfrt0PO").roles("admin"
		 * ,"user") .and() .withUser("liu").password(
		 * "$2a$10$sAmdadhc46uUoA9XHD0Diu8sZooyHHdc0q/8beiL0hNjKfcfrt0PO").roles("user")
		 * ;
		 */
		 
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		//方法一，数据库动态配置路径角色权限  3
		.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

			@Override
			public <O extends FilterSecurityInterceptor> O postProcess(O object) {
				// TODO Auto-generated method stub
				object.setSecurityMetadataSource(cfisms());
				object.setAccessDecisionManager(cadm());
				return object;
			}
			
		})//方法2程序中指定路径对应的角色权限。
				/*
				 * .antMatchers("/admin/**").hasRole("admin")
				 * .antMatchers("/user/**").access("hasAnyRole('admin','user')")
				 * .antMatchers("/db/**").access("hasRole('dba')")/
				 * /"hasRole('admin') and hasRole('dba')"
				 * .antMatchers("/**").access("hasAnyRole('admin','dba','user')") .anyRequest()
				 * .authenticated()
				 */
		.and()
		.formLogin()
		//.loginPage("/login_page")
		.loginProcessingUrl("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.successHandler(new AuthenticationSuccessHandler() {

			@Override
			public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
					Authentication auth) throws IOException, ServletException {
				// TODO Auto-generated method stub
				System.out.println("******************************");
				 LOG.info("successHandler");
				 //springboot 默认的fastJson 在使用下面的内容会报错误，statckOver的问题。后期直接跳转到默认Controller即可，Controller 判断默认角色，显示对应的主页面
						/*
						 * Object principal=auth.getPrincipal();
						 * resp.setContentType("application/json;charset=utf-8"); PrintWriter
						 * out=resp.getWriter(); resp.setStatus(200);
						 * 
						 * Map<String,Object>map=new HashMap<>(); map.put("statue", 200); map.put("msg",
						 * principal); ObjectMapper om=new ObjectMapper();
						 * out.write(om.writeValueAsString(map)); out.flush(); out.close();
						 */
				
				
			}})//.successForwardUrl("/admin/userhtml")//这个居然不行，有问题
		//这个居然成功不了
		.defaultSuccessUrl("/admin/userhtml")//进入一个Controller，中判断使用类型。在进一步跳转到具体的用户主界面，默认的角色的主界面。，用户角色的切换。
		.failureHandler(new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
					AuthenticationException e) throws IOException, ServletException {
				// TODO Auto-generated method stub
				LOG.info("**********************failureHandler****************");
				 //springboot 默认的fastJson 在使用下面的内容会报错误，statckOver的问题。
				//尝试控制fastJson的错误
						/*
						 * 
						 * resp.setContentType("application/json;charset=utf-8"); PrintWriter
						 * out=resp.getWriter(); resp.setStatus(401); Map<String,Object>map=new
						 * HashMap<>(); map.put("statue", 401); if(e instanceof LockedException) {
						 * map.put("msg", "账户被锁定，登陆失败！"); }else if(e instanceof BadCredentialsException)
						 * { map.put("msg", "账户或密码错误，登陆失败！"); }else if(e instanceof DisabledException) {
						 * map.put("msg", "账户被禁用，登陆失败！"); }else if(e instanceof AccountExpiredException)
						 * { map.put("msg", "账户已过期，登陆失败！"); }else if(e instanceof
						 * CredentialsExpiredException) { map.put("msg", "密码过期，登陆失败"); }else {
						 * map.put("msg", "登陆失败！"); } ObjectMapper om=new ObjectMapper();
						 * out.write(om.writeValueAsString(om)); out.flush(); out.close();
						 */
				
				
			}})
		.permitAll()
		.and()
		.csrf()//跨域请求，
		.disable()
		.logout()
		.logoutUrl("/logout")
		.clearAuthentication(true)//清除身份认证信息
		.invalidateHttpSession(true)//使Session失效
		//开发者可以在addLogoutHandler配置数据清除工作,如Cookoe
		.addLogoutHandler(new LogoutHandler() {
		  
		  @Override public void logout(HttpServletRequest req , HttpServletResponse
		  resp , Authentication auth ) {
			  // TODO Auto-generated method stub
		  
		  }
		  
		  })
		 .logoutSuccessHandler(new LogoutSuccessHandler() {
		  
		  @Override public void onLogoutSuccess(HttpServletRequest request,
		  HttpServletResponse response, Authentication authentication) throws
		  IOException, ServletException { // TODO Auto-generated method stub
		  response.sendRedirect("/login"); }
		  
		  })
		 ;
		
	}
}

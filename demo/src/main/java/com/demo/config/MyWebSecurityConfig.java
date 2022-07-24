package com.demo.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder(10);

		//NoOpPasswordEncoder.getInstance();
	}
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("root").password("$2a$10$sAmdadhc46uUoA9XHD0Diu8sZooyHHdc0q/8beiL0hNjKfcfrt0PO").roles("admin","dba")   //这里的roles 不需要加入前缀 ROLE_,会自动加入ROLE_ 的
		.and()
		.withUser("admin").password("$2a$10$sAmdadhc46uUoA9XHD0Diu8sZooyHHdc0q/8beiL0hNjKfcfrt0PO").roles("admin","user")
		.and()
		.withUser("liu").password("$2a$10$sAmdadhc46uUoA9XHD0Diu8sZooyHHdc0q/8beiL0hNjKfcfrt0PO").roles("user");
	}
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("admin")
		.antMatchers("/user/**").access("hasAnyRole('admin','user')")
		.antMatchers("/db/**").access("hasRole('admin') and hasRole('dba')")
		/*		.antMatchers("/**").access("hasAnyRole('admin','dba','user')")*/
		.anyRequest()
		.authenticated()
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
				Object principal=auth.getPrincipal();
				resp.setContentType("application/json;charset=utf-8");
				PrintWriter out=resp.getWriter();
				resp.setStatus(200);
				
				Map<String,Object>map=new HashMap<>();
				map.put("statue", 200);
				map.put("msg", principal);
				ObjectMapper om=new ObjectMapper();
				out.write(om.writeValueAsString(map));
				out.flush();
				out.close();
				
				
			}})
		.failureHandler(new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp,
					AuthenticationException e) throws IOException, ServletException {
				// TODO Auto-generated method stub
				resp.setContentType("application/json;charset=utf-8");
				PrintWriter out=resp.getWriter();
				resp.setStatus(401);
				Map<String,Object>map=new HashMap<>();
				map.put("statue", 401);
				if(e instanceof LockedException) {
					map.put("msg", "账户被锁定，登陆失败！");
				}else if(e instanceof BadCredentialsException) {
					map.put("msg", "账户或密码错误，登陆失败！");
				}else if(e instanceof DisabledException) {
					map.put("msg", "账户被禁用，登陆失败！");
				}else if(e instanceof AccountExpiredException) {
					map.put("msg", "账户已过期，登陆失败！");
				}else if(e instanceof CredentialsExpiredException) {
					map.put("msg", "密码过期，登陆失败");
				}else {
					map.put("msg", "登陆失败！");
				}
				ObjectMapper om=new ObjectMapper();
				out.write(om.writeValueAsString(om));
				out.flush();
				out.close();
			}})
		.permitAll()
		.and()
		.csrf()
		.disable()
		.logout()
		.logoutUrl("/logout")
		.clearAuthentication(true)
		.invalidateHttpSession(true)
		.addLogoutHandler(new LogoutHandler() {

			@Override
			public void logout(HttpServletRequest req , HttpServletResponse resp ,
					Authentication auth ) {
				// TODO Auto-generated method stub
				
			}
			
		})
		.logoutSuccessHandler(new LogoutSuccessHandler() {

			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				// TODO Auto-generated method stub
				response.sendRedirect("/login");
			}
			
		});
		
	}
}

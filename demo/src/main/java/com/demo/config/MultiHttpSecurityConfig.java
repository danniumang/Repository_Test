/*
package com.demo.config;
*/
/***
 *@author :liu
 *@date :2022/7/13
 **
 *//*


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.naming.NoPermissionException;

*/
/****
 *@Description: 多个httpSecurity
 *@author liu
 *@date 2022/7/13 12:02
 *****//*

@Configuration
public class MultiHttpSecurityConfig {
    @Bean
        PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
auth.inMemoryAuthentication().withUser("admin").roles("ADMIN","USER").and().withUser("liu").roles("USER");
    }
    @Configuration
    @Order(1)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
        protected void config(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasRole("ADMIN");
        }
    }
    @Configuration
    public static class OtherSecurityConfi extends WebSecurityConfigurerAdapter{
        protected void config(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login").permitAll().and().csrf().disable();
        }
    }
}
*/

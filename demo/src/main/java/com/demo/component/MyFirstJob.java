package com.demo.component;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class MyFirstJob {
	public void sayHello() {
		System.out.println("MyFirstJob:sayHello:"+new Date());
	}

}

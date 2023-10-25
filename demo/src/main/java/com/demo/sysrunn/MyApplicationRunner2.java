package com.demo.sysrunn;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Order(1)
public class MyApplicationRunner2 implements ApplicationRunner{

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		List<String>nonOptionArgs=args.getNonOptionArgs();//main(String args)
		System.out.println("2-nonoOPtionArgs"+nonOptionArgs);
	}

}


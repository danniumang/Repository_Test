package com.demo.sysrunn;

import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Order(2)
public class MyApplicationRunner implements ApplicationRunner{

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		List<String>nonOptionArgs=args.getNonOptionArgs();//main(String args)
		System.out.println("1-nonoOPtionArgs"+nonOptionArgs);
	}

}

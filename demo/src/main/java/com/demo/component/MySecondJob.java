package com.demo.component;

import java.util.Date;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MySecondJob extends QuartzJobBean{
	private String name;
	

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("hello:"+name+new Date());
	}

    //QuartzConfig.class   JobDetailFactoryBean   中 jobDetail2 使用这个set
	public void setName(String name) {
		this.name = name;
	}
	
}

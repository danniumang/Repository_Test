package com.demo.component;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MySchedule {
	@Scheduled(fixedDelay=300000)//当前任务结束后一秒后在开启其他的任务。
	public void fixedDelay() {
		System.out.println("fixedDelay: "+new Date());
		
	}
	@Scheduled(fixedRate=200000)//当前任务开启2秒后开启另外一个任务。当前任务或许可能还没有未完成，就已经开启其他的任务了。
	public void fixedRate() {
		System.out.println("fixedRate: "+new Date());
	}
	@Scheduled(initialDelay=100000,fixedRate=200000)//首次执行延迟的时间
	public void initialDelay() {
		System.out.println("initalDelay:"+new Date());
	}
	@Scheduled(cron="0 */59 * * * ?")//设置在具体间隔或者具体时刻执行该任务。linux 的定时任务，window 定时任务
	public void cron() {
		System.out.println("cron: "+new Date());
	}

}

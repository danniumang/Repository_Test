package com.demo.config;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.demo.component.MySecondJob;

/**
 * @Title QuartzConfig.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月10日 下午10:07:40 
 * 定时任务，另外外一个定时
 * quartz ,这里配置了定时任务的相关方法，与Schedule 对照进行。
 */
@Configuration
public class QuartzConfig {
	/**
	 *@return
	 *jobDetail
	 */
	@Bean
	MethodInvokingJobDetailFactoryBean jobDetail1() {
		MethodInvokingJobDetailFactoryBean bean=new MethodInvokingJobDetailFactoryBean();
		bean.setTargetBeanName("myFirstJob");//参考  MyFirstJob.class,这里的class 时定时任务，定时的工作
		bean.setTargetMethod("sayHello");//Bean中方法
		return bean;
		
	}
	/**
	 *@return
	 *jobDetail
	 */
	@Bean
	JobDetailFactoryBean jobDetail2() {
		JobDetailFactoryBean bean=new JobDetailFactoryBean();
		bean.setJobClass(MySecondJob.class);//JobClass
		JobDataMap jobDataMap=new JobDataMap();
		jobDataMap.put("name", "daliu");//传入参数
		bean.setJobDataMap(jobDataMap);
		bean.setDurability(true);//指定作业的持久性，即即使不再有触发器指向它，它是否仍应存储在作业存储中。
		return bean;
		
	}
	/**
	 *@return
	 *触发器
	 */
	@Bean
	SimpleTriggerFactoryBean simpleTrigger() {
		SimpleTriggerFactoryBean bean=new SimpleTriggerFactoryBean();
		bean.setJobDetail(jobDetail1().getObject());
		bean.setRepeatCount(3);//任务的循环次数
		bean.setStartDelay(1000);//启动延迟时间
		bean.setRepeatInterval(2000);//   job------job   指定此触发器的执行时间间隔
		return bean;
		
	}
	/**
	 *@return
	 *  cron linux 的定时任务
	 *  "* * * * * ?"
	 *  {秒数} {分钟} {小时} {日期} {月份} {星期} {年份(可为空)}
	 *  详细参看配置说明
	 *  触发器
	 */
	@Bean
	CronTriggerFactoryBean cronTrigger() {
		CronTriggerFactoryBean bean=new CronTriggerFactoryBean();
		bean.setJobDetail(jobDetail2().getObject());
		bean.setCronExpression("0 */59 * * * ?");
		return bean;
		
	}
	@Bean
	SchedulerFactoryBean schedulerFactory() {
		SchedulerFactoryBean bean=new SchedulerFactoryBean();
		SimpleTrigger simpleTrigger=simpleTrigger().getObject();
		CronTrigger cronTrigger=cronTrigger().getObject();
		bean.setTriggers(simpleTrigger,cronTrigger);//这里可以添加很多的上面的trigger,将这些trigger 添加到scheduler中统一管理
		return bean;
	}

}

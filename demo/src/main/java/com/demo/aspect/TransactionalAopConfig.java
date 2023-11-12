package com.demo.aspect;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * @Title TransaactionalAopConfig.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月10日 下午9:20:52 
 * 手动事务管理，自定义配置事务得管理，虽然springboot已经默认开启相关事务。
 * 相当于 spring 中xml配置的txmanager。无脑的覆盖，有些件没有默认开启事务。
 */
@Aspect
@Configuration
public class TransactionalAopConfig {
	private  final static int METHOD_TIME_OUT=5000;
	private   final static String POINTCUT_EXPRESSION="execution(* com.demo.service.*.*(..))";
	@Resource
	private TransactionManager transactionManager;
	@Bean
	public TransactionInterceptor txAdvice() {
		NameMatchTransactionAttributeSource source=new NameMatchTransactionAttributeSource();
				
		RuleBasedTransactionAttribute required=		getRuleBasedTransactionAttribute();
		Map<String,TransactionAttribute>attributeMap=new HashMap<>(30);
		//更删改
		attributeMap.put("save*", required);
		attributeMap.put("remove*", required);
		attributeMap.put("update*", required);
		attributeMap.put("batch*", required);
		attributeMap.put("clear*", required);
		attributeMap.put("add*", required);
		attributeMap.put("append*", required);
		attributeMap.put("modify*", required);
		attributeMap.put("edit*", required);
		attributeMap.put("insert*", required);
		attributeMap.put("delete*", required);
		attributeMap.put("do*", required);
		attributeMap.put("create*", required);
		attributeMap.put("import*", required);
		//只读
		RuleBasedTransactionAttribute readOnly=new RuleBasedTransactionAttribute();
		readOnly.setReadOnly(true);//只读设置
		readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);//不以事务方式执行。
		attributeMap.put("select*", readOnly);
		attributeMap.put("get*", readOnly);
		attributeMap.put("valid*", readOnly);
		attributeMap.put("list*", readOnly);
		attributeMap.put("count*", readOnly);
		attributeMap.put("find*", readOnly);
		attributeMap.put("load*", readOnly);
		attributeMap.put("search*", readOnly);
		source.setNameMap(attributeMap);
		return new TransactionInterceptor(transactionManager,source);
		
	
		
	}
	/**
	 *@return
	 *RuleBasedTransactionAttribute
	 *TransactionDefinition.PROPAGATION_REQUIRED->无-》新增，有则加入
	 */
	@Bean
	public  RuleBasedTransactionAttribute getRuleBasedTransactionAttribute() {
		// TODO Auto-generated method stub
		RuleBasedTransactionAttribute required=new RuleBasedTransactionAttribute();
		required.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));//异常回滚
		required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);//事务处理类型，无-》新增，有则加入
		required.setTimeout(METHOD_TIME_OUT);//超时设置
		return required;
	}
	@Bean
	public Advisor txAdviceAdvisor() {
		//切面定义
		AspectJExpressionPointcut pointcut=new AspectJExpressionPointcut();
		pointcut.setExpression(POINTCUT_EXPRESSION);
		//切面和通知相关联。
		return new DefaultPointcutAdvisor(pointcut,txAdvice());
		
	}

}

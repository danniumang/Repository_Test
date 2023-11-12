package com.demo.config;

import java.net.MalformedURLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.UrlResource;

import com.demo.entity.User;

/**
 * @Title CsvBatchJobConfig.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月12日 下午5:31:11 
 * 一般在导入数据前，需要使用者下载一个导入模板，模版中各个列名确定，字段要求需要附加说明？根据模板格式确定
 * 
 */
@Configuration
public class CsvBatchJobConfig {
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	@Autowired
	DataSource dataSource;
	/**
	 *@return
	 *FlatFileItemReader  普通文件
	 *StaxEventItemReader  xml数据
	 *jdbcPaginItemReader 数据局中的数据
	 *读取文件
	 *ClassPathResource
	 *MultipartFileResource
	 *UrlResource
	 *PathResource 
	 *PathResource 
	 * @throws MalformedURLException 
	 */
	@Bean
	@StepScope
	FlatFileItemReader<User>itemReader() throws MalformedURLException{
		FlatFileItemReader<User>reader=new FlatFileItemReader<>();
		reader.setLinesToSkip(1);//第一行是标题，需要跳过。
		reader.setResource(new PathResource ("D:\\data2.csv"));//数据源，这个根据web段导入，选择具体路径  MultipartFileResource。。。。等等其他的方式
		reader.setLineMapper(new DefaultLineMapper<User>() {//两个阶段的LineMapper实现，包括将行标记到FieldSet，然后映射到项。如果需要对异常进行更细粒度的控制，则应直接实现LineMapper接口
			{
				setLineTokenizer(new DelimitedLineTokenizer() {{
					setNames("id","username","address","gender","phone","mail");//设置列名，这个可选.跟导入模板中列名以及数据库字段相匹配，取的统一地方的数据，或者统一方法得到的列名。
					setDelimiter(",");//列与列之间的间隔符号\t
				}});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
					setTargetType(User.class);//唯一性
				}});
			}});
		
		return reader;
		
	}
	/**
	 *@return
	 *写入数据库
	 *JdbcBatchItemWriter 
	 *FlatFileItemWriter
	 *StaxEventItemWritr
	 *MongoItemWriter
	 *JapItemWriter
	 *Neo4jITemWriter
	 *HibernateItemWriiter
	 *
	 */
	@Bean
	JdbcBatchItemWriter<Object> jdbcBatchItemWriter() {
		System.out.println("int the jdbcBatchItemWriter");
		JdbcBatchItemWriter<Object> writer=new JdbcBatchItemWriter<Object>();
		writer.setDataSource(dataSource);
		writer.setSql("insert into USERINFO(id,username,address,gender,phone，mail)"+"values(:id,:username,:address,:gender,:phone,:mail)");
		writer.setItemSqlParameterSourceProvider(
				new BeanPropertyItemSqlParameterSourceProvider<>());//bean字段与sql占位符一一映射。
		return writer;
	}
	/**
	 * @return
	 * @throws MalformedURLException
	 * Step这个可以唯一，也可每次名称不同
	 */
	@Bean
	Step csvStep() throws MalformedURLException {
		System.out.println("int the csvStep");
		//String.valueOf(new Date().getTime())+
		return stepBuilderFactory.get("csvStep1")
				.<User,User>chunk(2)  //提交间隔
				.reader(itemReader())
				.writer(jdbcBatchItemWriter())
				.build();
		
	}
	/**
	 * @return
	 * job 每次导入的时候，这个get(xxx)都是唯一性的，重复的话，相当于是同一个任务了。
	 * 当有一次已经成功批量处理了数据，则后续的批量处理不会成功，所以需要特殊的处理
	 * @throws MalformedURLException 
	 */
	@Bean
	Job csvJob() throws MalformedURLException {
		//String date=new Date().toString();//String.valueOf(new Date().getTime())+
		System.out.println("int the csvJob");
		return jobBuilderFactory.get(String.valueOf(new Date().getTime())+"csvJob")//创建作业生成器并初始化其作业存储库。请注意，如果构建器用于创建@Beandefinition，那么作业的名称和bean名称可能不同。
				.start(csvStep())
				.build();
	}

}

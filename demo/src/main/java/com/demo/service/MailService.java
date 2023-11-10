package com.demo.service;

import java.io.File;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 * @Title MailService.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月7日 下午9:09:51 
 * MailSenderPropertiesConfiguration->JavaMailSender
 * MailSenderAutoConfiguration
 */
@Component
public class MailService {
	@Autowired
	JavaMailSender javaMailSender;
	//普通文本类型邮件
	public void sendSimpleMail(String from,String to,String cc,String subject,String content) {
		SimpleMailMessage simMsg=new SimpleMailMessage();
		simMsg.setFrom(from);
		simMsg.setTo(to);
		simMsg.setCc(cc);
		simMsg.setSubject(subject);
		simMsg.setText(content);
		javaMailSender.send(simMsg);
		
	}
	//带附件性的邮件
	/**
	 *@param from
	 *@param to
	 *@param cc
	 *@param subject
	 *@param content
	 *@param file
	 *
	 *MimeMessage-》message的固定类型
	 *MimeMessageHelper  ->用于附件的发送
	 *
	 */
	public void sendAttachFileMail(String from,String to,String cc,String subject,String content,File file) {//可传入多个附件一次性
		
		try {
			MimeMessage message=javaMailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message,true);// multipart message  附件性邮件。
			helper.setFrom(from);
             helper.setTo(to);
             helper.setCc(cc);
             helper.setSubject(subject);
             helper.setText(content);
             helper.addAttachment(file.getName(), file);//添加附件，多次调用添加附件
             javaMailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
public void sendAttachFilesMail(String from,String to,String cc,String subject,String content,List<File> files) {//可传入多个附件一次性
		
		try {
			MimeMessage message=javaMailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message,true);// multipart message  附件性邮件。
			helper.setFrom(from);
             helper.setTo(to);
             helper.setCc(cc);
             helper.setSubject(subject);
             helper.setText(content);
             if(files.size()>0) {
            	 for(File fi:files) {
             helper.addAttachment(fi.getName(), fi);//添加附件，多次调用添加附件
            	 }
             }
             javaMailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//正文带图片
    /**
     *@param from
     *@param to
     *@param cc
     *@param subject
     *@param content
     *@param srcPath  文件的具体路径
     *@param resIds   文件的id唯一性
     *在邮件当中还是以附件的形式显示出来的
     *
     */
    public void sendMailWithImage(String from,String to,String cc,String subject,String content,String []srcPath,String []resIds) {
    	if(srcPath.length!=resIds.length) {
    		System.out.println("fa song shibai ");
    		return;}
    	
    	try {
			MimeMessage message=javaMailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message,true);// multipart message  附件性邮件。
			helper.setFrom(from);
             helper.setTo(to);
             helper.setCc(cc);
             helper.setSubject(subject);
             helper.setText(content);
            for(int i=0;i<srcPath.length;i++) {
            	FileSystemResource res=new FileSystemResource(new File(srcPath[i]));
            	helper.addInline(resIds[i], res);
            }
             javaMailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    

}

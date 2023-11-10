package com.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.service.MailService;
@SpringBootTest
class MailSendTest {
    @Autowired
    MailService  mailService;
	@Test
	void test() {
		mailService.sendSimpleMail("2025884887@qq.com", "1515783415@qq.com", "15700085971@163.com", "springboot测试", "this is content for springboot mail");
		
	}
	@Test
	void testSendFile() {
		mailService.sendAttachFileMail("2025884887@qq.com", "1515783415@qq.com", "15700085971@163.com", "springboot测试", "this is content for springboot mail",new File("D:\\BaiduNetdiskDownload\\oracle.txt"));
		
	}
	@Test
	void testSendFiles() {
	     List<File>files=new ArrayList<>();
	     files.add(new File("D:\\BaiduNetdiskDownload\\oracle.txt"));
	     files.add(new File("D:\\lean\\java.txt"));
		mailService.sendAttachFilesMail("2025884887@qq.com", "1515783415@qq.com", "15700085971@163.com", "springboot测试", "this is content for springboot mail",files);
		
	}
	
	@Test
	void testSendMail_Image() {
		mailService.sendMailWithImage("2025884887@qq.com", "1515783415@qq.com", "15700085971@163.com", "springboot测试,正文带有图片", "<div>hello this is mail with pictures:"+
	"this is first :<div><img src='cid:p01'/></div>"+
	"this is second:<div><img src='cid:p02'/></div>"+
	"</div>",new String[] {"C:\\Users\\Administrator\\Pictures\\Camera Roll\\p01.jpg","C:\\Users\\Administrator\\Pictures\\Camera Roll\\p02.jpeg"},new String[] {"p01","p02"});
	}

}

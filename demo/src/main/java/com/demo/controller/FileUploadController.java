package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController

public class FileUploadController {
	SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
	@PostMapping("/upload")
	public String upload(MultipartFile uploadFile,HttpServletRequest req) {
		String realPath=req.getServletContext().getRealPath("/");
		//String realPath=req.getSession().getServletContext().getRealPath("/");
		System.out.println(realPath);
		String form=format.format(new Date());
		File folder=new File(realPath+form);
		if(!folder.isDirectory()) {
			folder.mkdirs();
		}
		String oldName=uploadFile.getOriginalFilename();
		String newName=UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."),oldName.length());
		
		try {
			uploadFile.transferTo(new File(folder,newName));
			String filePath=req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+"/uploadFile/"+form+"/"+newName;
			return filePath;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "上传失败";
		
	}

}

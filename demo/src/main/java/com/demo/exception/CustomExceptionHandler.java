package com.demo.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title CustomExceptionHandler.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年10月12日 上午9:05:25 
 * 异常处理多种方式  取其一使用
 * void  string  model 。。。。
 */
@ControllerAdvice
public class CustomExceptionHandler {
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public void uploadException(MaxUploadSizeExceededException ex,HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		out.write("to big for limiter");
		out.flush();
		out.close();
		
	}
	/*
	 * @ExceptionHandler(MaxUploadSizeExceededException.class) public ModelAndView
	 * uploadException1(MaxUploadSizeExceededException ex) throws IOException {
	 * ModelAndView model =new ModelAndView(); model.addObject("msg",
	 * "to big for limiter"); model.setViewName("error"); return model;
	 * 
	 * }
	 */

}

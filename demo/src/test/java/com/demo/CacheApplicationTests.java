package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.dao.BookDao;
import com.demo.entity.Book;

@SpringBootTest
class CacheApplicationTests {

	@Autowired
	BookDao bookDao;

	@Test
	void test() {
		bookDao.getBookById(1);
		bookDao.getBookById(2);
		bookDao.getBookById(3);
		bookDao.getBookById(4);
		bookDao.getBookById(5);
		bookDao.deleteById(1);
		Book b3 = bookDao.getBookById(1);
		System.out.println("B3" + b3);
		Book b = new Book();
		b.setAuthor("daliu");
		b.setBookId(1);
		b.setBookName("java 开发测试");
		bookDao.updateBookById(b);
		Book b4 = bookDao.getBookById(1);
		System.out.println("b4 " + b4); // 已经开启缓存
		bookDao.deleteById(1);
		/*
		 * delete book by id get book by id B3com.demo.entity.Book@2fafa9bf update book
		 * by id b4 com.demo.entity.Book@96448ee
		 */

	}

}

package com.demo.dao;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.demo.entity.Book;

/**
 * @Title BookDao.java
 * @Des TODO
 * @author daliu
 * @version 创建时间：2023年11月1日 上午1:51:35 
 * 用于缓存控制。查看各种缓存的注解，缓存只用于某些高频率访问的路径。
 */
@Repository
@CacheConfig(cacheNames="book_cache")
public class BookDao {
	@Cacheable(value="c1")//默认的key 为 id 的数据//查询后缓存
	public Book getBookById(Integer bookId) {
		System.out.println("get book by id");
		Book book=new Book();
		book.setAuthor("LIU");
		book.setBookId(12);
		book.setBookName("java");
		return book;
	}
    @CachePut(value="c2",key="#book.bookId")//取的应该是下面的参数book的ID  该注解多用更新，缓存+数据库都更新
    public Book updateBookById(Book book) {
    	System.out.println("update book by id");
    	book.setBookName("java 2");
    	return book;
    }
    @CacheEvict(key="#Id")//直接删除缓存以及数据库数据 这里的  #  不能少了 
    public void deleteById(Integer Id) {
    	System.out.println("delete book by id");
    }
}

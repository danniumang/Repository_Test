package com.demo;


import java.io.IOException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.entity.Book;
@RunWith(SpringRunner.class)
@JsonTest
class JSONTest {
	@Autowired
	JacksonTester<Book>jacksonTester;
	

	@Test
	void test() throws IOException {
		Book book=new Book();
		book.setBookId(12);
		book.setBookName("java jackson");
		book.setAuthor("daliu");
		Assertions.assertThat(jacksonTester.write(book)).isEqualToJson("book.json");
		Assertions.assertThat(jacksonTester.write(book)).hasJsonPathStringValue("@.bookName");
		Assertions.assertThat(jacksonTester.write(book)).extractingJsonPathStringValue("@.bookName").isEqualTo("java jackson");
	}
	@Test
	public  void  testDeseriablize() throws IOException {
		String content="{\"bookId\":1,\"bookName\":\"java jackson\",\"author\":\"daliu\"}";
		Assertions.assertThat(jacksonTester.parseObject(content).getBookName()).isEqualTo("java jackson");
		
	}

}

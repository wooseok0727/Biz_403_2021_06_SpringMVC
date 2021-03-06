package com.callor.book.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQualifier;
import com.callor.book.dao.extend.BookDao;
import com.callor.book.model.BookDTO;
import com.callor.book.service.BookService;
import com.callor.book.service.NaverAbstractService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("bookServiceV1")
public class BookServiceImplV1 implements BookService {
	
	@Qualifier(NaverQualifier.NAVER_BOOK_SERVICE_V2)
	protected final NaverAbstractService<BookDTO> nBookService;
	protected final BookDao bookDao;
	
	@Override
	public List<BookDTO> selectAll() {
		return bookDao.selectAll();
	}
	
	@Override
	public int insert(String isbnUTF) throws Exception {
		
		String isbn = URLDecoder.decode(isbnUTF,"UTF-8");
		String[] isbns = isbn.split(" ");
		
		isbn = isbns[1];
		String queryURL = nBookService.queryURL(isbn);
		String jsonString = nBookService.getJsonString(queryURL);
		List<BookDTO> books = nBookService.getNaverList(jsonString);
		BookDTO book = books.get(0);
		book.setIsbn(isbn);
		bookDao.insert(book);
		
		return 0;
	}

}

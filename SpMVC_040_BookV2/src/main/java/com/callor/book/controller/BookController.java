package com.callor.book.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.book.service.BookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value="/book")
public class BookController {
	
	protected final BookService bookService;
	
	@RequestMapping(value="/insert/{isbn}",method=RequestMethod.GET)
	public String insert(@PathVariable("isbn")String isbn, Model model) 
			throws MalformedURLException, IOException, ParseException {
		
		log.debug("ISBN : {}",isbn);
		
		int ret = bookService.insert(isbn);
		return "redirect:/naver/book";
	}
}

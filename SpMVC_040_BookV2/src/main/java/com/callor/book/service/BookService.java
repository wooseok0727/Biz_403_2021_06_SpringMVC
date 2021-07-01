package com.callor.book.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.json.simple.parser.ParseException;

public interface BookService {

	public int insert(String isbn) 
			throws UnsupportedEncodingException, MalformedURLException, IOException, ParseException;

}

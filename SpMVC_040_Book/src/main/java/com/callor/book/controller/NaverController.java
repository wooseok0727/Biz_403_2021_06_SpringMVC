package com.callor.book.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Optional;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.book.service.impl.NaverMainServiceImplV1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(value="/naver")
@Controller
public class NaverController {
	
	protected final NaverMainServiceImplV1 nService;
	
	/*
	 * web client에서 서버로 Request를 할 때
	 * 어떤 데이터를 보내는 방법
	 * 
	 * 1. queryString
	 * ?변수=값 : GET method 방법으로 queryString 으로 데이터 보내기
	 * ?username=callor&pw=12345
	 * 
	 * 2. request Body 에 담아 보내는 방법
	 * <form method=POST><input username></form>
	 * 
	 * 3. url Path(Path Variable) 방식
	 * http://localhost:8080/book/naver/korea
	 * http://localhost:8080/book/naver/callor/12345
	 * Mapping(value="/naver/{username}/{password})
	 */
	
	@RequestMapping(value={"/{CATEGORY}",""},method=RequestMethod.GET)
	public String home(
			@PathVariable(name = "CATEGORY",required = false) String cat,
			@RequestParam(name = "search",required = false,defaultValue = "") String search, Model model) throws MalformedURLException, IOException, ParseException {
		
		if(cat == null) cat = "book";
		log.debug("컨트롤러 URL {}",cat);
		model.addAttribute("CATEGORY",cat);
		nService.naverGetData(cat, search, model);
		
		return "home";
	}
}

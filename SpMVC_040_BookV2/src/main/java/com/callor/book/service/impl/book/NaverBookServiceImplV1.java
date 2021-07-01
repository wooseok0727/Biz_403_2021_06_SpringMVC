package com.callor.book.service.impl.book;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQualifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.BookDTO;
import com.callor.book.service.NaverAbstractService;

import lombok.extern.slf4j.Slf4j;

/*
 * NaverAbstractService 추상클래스를 상속받아 구현된 클래스
 * 추상클래스에 사전 정의된 getJsonString() method 코드는
 * 직접 작성하지 않고, 사용할 수 있다.
 * 		getJsonString()
 * 
 * 추상메서드는 반드시 구현해야 한다.
 *  	queryURL(), getNaverList()
 *  
 *  # 다음과 같은 형식으로 사용가능하다
 *  NaverAbstarctService nService = new NaverServiceImplV1();
 *  nService.queryURL()
 *  nService.getJsonString()
 *  nService.getNaverList()
 */

@Slf4j
@Service(NaverQualifier.NAVER_BOOK_SERVICE_V1)
public class NaverBookServiceImplV1 extends NaverAbstractService<BookDTO> {
	
	/*
	 * naver에 요청하기
	 * BookURL + "?query=" + 검색문자열
	 */
	
	@Override
	public String queryURL(String search_text) throws UnsupportedEncodingException {
		
		// 검색하고자 하는 문자열을 UTF-8로 인코딩
		String searchUTF8 =  URLEncoder.encode(search_text,"UTF-8");
		
		StringBuilder queryURL = new StringBuilder();
		queryURL.append(NaverSecret.NURL.BOOK); // queryString += BookURL
		
		String queryString = String.format("?query=%s",searchUTF8);
		queryURL.append(queryString);
		queryString = String.format("&display=%d", 20);
		queryURL.append(queryString);
		log.debug("book queryURL {}",queryURL.toString());
		return queryURL.toString();
	}
	
	/*
	 * 네이버에서 받은 jsonString을 parsing하여
	 * List<BookDTO>에 담아서 return 하기
	 * 
	 * json-simple을 사용하여 parsing 하기
	 */
	@Override
	public List<BookDTO> getNaverList(String jsonString) throws ParseException {
		
		log.debug("나는 ServiceV1");
		
		// 1. json Parsing 도구 선언
		JSONParser jParser = new JSONParser();
		
		// jsonString을 JSON 객체로 변환
		JSONObject jObject = (JSONObject) jParser.parse(jsonString);
		// parsing 된 JSON 객체에서 items 항목을 추출하여
		// JSON 배열타입으로 변환하기(내부적으로는 List type)
		JSONArray items = (JSONArray) jObject.get("items");
		
		List<BookDTO> bookList = new ArrayList<>();
		
		int nSize = items.size();
		for(int i = 0; i < nSize; i++) {
			
			// 한권의 도서정보가 담긴 객체 추출
			JSONObject item = (JSONObject) items.get(i);
			
			// 도서정보 학목을 문자열 변수에 저장
			String title = item.get("title").toString();
			String link = item.get("link").toString();
			String image = (String) item.get("image");
			String author = (String) item.get("author");		
			String price = (String) item.get("price");		
			String discount = (String) item.get("discount");	
			String publisher = (String) item.get("publisher");	
			String isbn = (String) item.get("isbn");		
			String description = (String) item.get("description");	
			String pubdate = (String) item.get("pubdate");		
			
			// BookDTO에 담기
			BookDTO bookDTO = BookDTO.builder()
					.title(title)
					.link(link)
					.image(image)
					.author(author)		
					.price(price)		
					.discount(discount)	
					.publisher(publisher)	
					.isbn(isbn)		
					.description(description)	
					.pubdate(pubdate)
					.build();
			// List에 담기
			bookList.add(bookDTO);
		}
		return bookList;
	}
}
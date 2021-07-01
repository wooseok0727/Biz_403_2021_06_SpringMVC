package com.callor.book.service.impl.movie;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQualifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.MovieDTO;
import com.callor.book.service.NaverAbstractService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(NaverQualifier.NAVER_MOVIE_SERVICE_V1)
public class NaverMovieServiceImplV1 extends NaverAbstractService<MovieDTO> {

	@Override
	public String queryURL(String search_text) throws UnsupportedEncodingException {
		
		String searchUTF8 = URLEncoder.encode(search_text,"UTF-8");

//		StringBuilder queryURL = new StringBuilder();
//		queryURL.append(NaverSecret.NURL.MOVIE);
//		
//		String queryString = String.format("?query=%s", searchUTF8);
//		queryURL.append(queryString);
//		queryString = String.format("&display=%d", 10);
//		queryURL.append(queryString);
//		
//		log.debug("movie queryURL {}",queryURL.toString());
//		return queryURL.toString();
		
		String queryURL = NaverSecret.NURL.MOVIE;
		queryURL += "?query=%s&display=10";
		queryURL = String.format(queryURL, searchUTF8);
		log.debug("queryURL : {}",queryURL);
		return queryURL;
	}

	/*
	 * gson을 사용하여 jsonString을 List<MovieDTO>로 변환하기
	 */
	@Override
	public List<MovieDTO> getNaverList(String jsonString) throws ParseException {
		
		JsonElement jSonElement = JsonParser.parseString(jsonString);
		JsonElement oItems = jSonElement.getAsJsonObject().get("items");
		Gson gson = new Gson();
		List<MovieDTO> movieList = gson.fromJson(oItems, new TypeToken<List<MovieDTO>>() {}.getType());
		
		return movieList;
	}
}

package com.callor.book.service.impl.news;

import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.book.config.NaverQualifier;
import com.callor.book.config.NaverSecret;
import com.callor.book.model.NewsDTO;
import com.callor.book.service.NaverAbstractService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service(NaverQualifier.NAVER_NEWS_SERVICE_V1)
public class NaverNewsServiceImplV1 extends NaverAbstractService<NewsDTO> {

	@Override
	public String queryURL(String search_text) throws Exception {
		
		String searchUTF8 = URLEncoder.encode(search_text, "UTF-8");
		String queryURL = NaverSecret.NURL.NEWS;
		queryURL += "?query=%s&display=10";
		queryURL = String.format(queryURL, searchUTF8);
		
		return queryURL;
	}

	@Override
	public List<NewsDTO> getNaverList(String jsonString) throws Exception {
		
		JsonElement jSonElement = JsonParser.parseString(jsonString);
		JsonElement oItems = jSonElement.getAsJsonObject().get("items");
		Gson gson = new Gson();
		List<NewsDTO> newsList = gson.fromJson(oItems, new TypeToken<List<NewsDTO>>() {}.getType());
		
		return newsList;
	}

}

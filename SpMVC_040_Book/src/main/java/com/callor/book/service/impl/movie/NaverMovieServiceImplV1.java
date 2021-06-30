package com.callor.book.service.impl.movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.callor.book.config.NaverSecret;
import com.callor.book.model.MovieDTO;
import com.callor.book.service.NaverMovieService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("naverMovieServiceV1")
public class NaverMovieServiceImplV1 implements NaverMovieService {

	@Override
	public String queryURL(String search_text) {
		
		String searchUTF8 = null;
		try {
			searchUTF8 = URLEncoder.encode(search_text,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		StringBuilder queryURL = new StringBuilder();
		queryURL.append(NaverSecret.NURL.MOVIE);
		
		String queryString = String.format("?query=%s", searchUTF8);
		queryURL.append(queryString);
		queryString = String.format("&display=%d", 20);
		queryURL.append(queryString);
		
		log.debug("movie queryURL {}",queryURL.toString());
		return queryURL.toString();
	}

	@Override
	public String getJsonString(String queryURL) throws MalformedURLException, IOException {
		
		URL url = new URL(queryURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");
		httpConn.setRequestProperty("X-Naver-Client-Id", NaverSecret.NAVER_CLIENT_ID);
		httpConn.setRequestProperty("X-Naver-Client-Secret", NaverSecret.NAVER_CLIENT_SECRET);
		
		int httpStatusCode = httpConn.getResponseCode();
		
		InputStreamReader is = null;
		if(httpStatusCode == 200) {
			is = new InputStreamReader(httpConn.getInputStream());
		} else {
			is = new InputStreamReader(httpConn.getErrorStream());
		}
		
		BufferedReader buffer = new BufferedReader(is);
		StringBuffer sBuffer = new StringBuffer();
		
		String reader = new String();
		while((reader = buffer.readLine()) != null) {
			sBuffer.append(reader);
		}
		buffer.close();
		is.close();
		httpConn.disconnect();
		return sBuffer.toString();
	}

	@Override
	public List<MovieDTO> getNaverList(String jsonString) throws ParseException {
		
		JsonElement jSonElement = JsonParser.parseString(jsonString);
		JsonElement oItems = jSonElement.getAsJsonObject().get("items");
		Gson gson = new Gson();
		List<MovieDTO> movieList = gson.fromJson(oItems, new TypeToken<List<MovieDTO>>() {}.getType());
		
		return movieList;
	}

}

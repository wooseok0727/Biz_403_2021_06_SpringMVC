package com.callor.gallery.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.persistence.extend.GalleryDao;
import com.callor.gallery.service.FileService;
import com.callor.gallery.service.GalleryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("galleryServiceV1")
public class GalleryServiceImplV1 implements GalleryService {

	protected final GalleryDao gDao;
	@Qualifier("fileServiceV2")
	protected final FileService fService;
	
	/*
	 * Spring framework는
	 * 	@Autowired 가 설정된 변수, method, 객체 등을 만나면
	 *  변수를 초기화, method를 실행하여 또 변수초기화
	 * 	이미 생성되어 준비된 객체에 주입등을 수행한다
	 */
	@Autowired
	public void create_table(GalleryDao gDao) {
		Map<String,String> maps = new HashMap<>();
		gDao.create_table(maps);
	}
	
	@Override
	public int insert(GalleryDTO galleryDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

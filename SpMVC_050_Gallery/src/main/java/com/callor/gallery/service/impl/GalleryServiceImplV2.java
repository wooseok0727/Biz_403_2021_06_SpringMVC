package com.callor.gallery.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.callor.gallery.model.FilesDTO;
import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.persistence.extend.FilesDao;
import com.callor.gallery.persistence.extend.GalleryDao;
import com.callor.gallery.service.FileService;
import com.callor.gallery.service.PageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("galleryServiceV2")
public class GalleryServiceImplV2 extends GalleryServiceImplV1 {



	/*
	 * @RequiredArgsConstructor 를 사용한 클래스를 상속받을 떄는
	 * 상속받은 클래스에서 강제로 생성자를 만들어야 한다
	 * 
	 * eClipse의 자동완성 기능을 사용하여 생성자를 만든다
	 * 만약 매개변수로 설정된 요소중에 interface를 상속받은 클래스가
	 * 2개 이상일 경우 @Qualifier()를 설정해야 하는데
	 * 
	 * 이때 각 매개변수의 요소 type 앞에 작성해 주면 된다
	 */
	public GalleryServiceImplV2(GalleryDao gDao, FilesDao fDao, @Qualifier("fileServiceV2") FileService fService, PageService pService) {
		super(gDao, fDao, fService, pService);
		// TODO Auto-generated constructor stub
	}

	@Override
	public GalleryDTO findByIdGallery(Long g_seq) {
		
		GalleryDTO gallery = gDao.findByIdGalleryFilesResultMap(g_seq);
		if(gallery != null) {
			log.debug("갤러리 데이터 : {}",gallery.toString());
		}
		return gallery;
	}

	/*
	 * 첨부파일이 있는 게시물 삭제하기
	 * 1. 첨부파일을 제거한 후
	 * 	가. 첨부파일을 삭제하기 위하여 데이터를 다시 select 하여
	 * 		첨부파일 이름을 가져오기
	 * 	나. 가져온 이름으로 파일을 삭제하고
	 * 2. 게시물 데티어를 삭제
	 * 
	 * 첨부파일 뿐만 아니라 JOIN된 데이터가 또 있다.
	 * JOIN된 데이터에 첨부파일 정보가 또 있다
	 * 
	 * 1. JOIN된 데이터의 첨부파일을 삭제한 후
	 * 2. JOIN된 데이터를 삭제하고
	 * 3. 본문(Gallery)의 첨부파일을 삭제하고
	 * 4. 본문을 삭제
	 */
	@Override
	public int delete(Long g_seq) {
		
		// Gallery 데이터와 fileList 데이터가 같이 포함된 데이터
		GalleryDTO gDTO = gDao.findByIdGalleryFilesResultMap(g_seq);
		if(gDTO == null) {
			return 0;
		}
		List<FilesDTO> fileList = gDTO.getFileList();
		for(FilesDTO file: fileList) {
			
			// 첨부파일 삭제
			String attFileName = file.getFile_upname();
			log.debug("attFileName {}",file.getFile_upname());
			int result = fService.delete(attFileName);
			if(result > 0) {
				fDao.delete(file.getFile_gseq());
			}
			
			
			// 데이터 한개씩 삭제
		}
		
		// 본문 첨부파일 삭제
		String imgFileName = gDTO.getG_image();
		int result = fService.delete(imgFileName);
		if(result > 0) {
			// 본문 데이터 삭제
			gDao.delete(g_seq);
		} else {
			log.debug("파일 삭제 실패로 데이터 삭제 하지 않음");
		}
		return 0;
	}
	
}

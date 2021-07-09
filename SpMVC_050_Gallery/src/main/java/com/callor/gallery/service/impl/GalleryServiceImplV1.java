package com.callor.gallery.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.FilesDTO;
import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.persistence.extend.FilesDao;
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
	protected final FilesDao fDao;
	
	@Qualifier("fileServiceV2")
	protected final FileService fService;
	
	/*
	 * Spring framework는
	 * 	@Autowired 가 설정된 변수, method, 객체 등을 만나면
	 *  변수를 초기화, method를 실행하여 또 변수초기화
	 * 	이미 생성되어 준비된 객체에 주입등을 수행한다
	 */
	
	@Autowired
	public void create_table(GalleryDao gaDao) {
		Map<String,String> maps = new HashMap<>();
		fDao.create_table(maps);
		gDao.create_table(maps);
	}
	
	@Override
	public int insert(GalleryDTO galleryDTO) throws Exception {
		return 0;
	}

	@Override
	public void input(GalleryDTO gDTO, MultipartFile one_file, MultipartHttpServletRequest m_file) throws Exception {
		// 대표이미지가 업로드 되면...
		// 이미지를 서버에 저장하고
		// 저장된 파일의 이름을 return 받기
		String strUUID = fService.fileUp(one_file);
		
		// DTO에 이미지 이름을 저장하기
		gDTO.setG_image(strUUID);
		
		log.debug(" INSERT 전 seq {}", gDTO.getG_seq());
		
		// GalleryDTO에 담긴 데이터를 tbl_gallery table에 insert하기
		gDao.insert(gDTO);
		log.debug(" INSERT 후 seq {}", gDTO.getG_seq());
		
		// mapper에서 insert를 수행한 후 새로 생성된 g_seq 값을 
		// 		selectKey 하여 gDTO의 g_seq 변수에 담아놓은 상태이다
		Long g_seq = gDTO.getG_seq();
		
		// 갤러리 게시판 seq 값과 파일들을 묶음으로 insert 하기 위한 준비하기
		List<FilesDTO> files = new ArrayList<FilesDTO>();
		
		// 업로드된 멀티파트파일을 서버에 업로드 하고
		// 원래 파일이름과 UUID 가 첨가된 파일이름을 추출하여 
		// FileDTO에 담고
		// 다시 List에 담아 놓는다
		List<MultipartFile> fileList = m_file.getFiles("m_file");
		for(MultipartFile file : fileList) {
			
			String fileOriginName = file.getOriginalFilename();
			String fileUUIDName = fService.fileUp(file);
			FilesDTO fDTO = FilesDTO.builder()
							.file_gseq(g_seq) // 갤러리 데이터의 PK값
							.file_originname(fileOriginName)
							.file_upname(fileUUIDName)
							.build();
			files.add(fDTO);
		}
		
		log.debug("이미지 들 {}",files.toString());
		
		fDao.insertOrUpdateWithList(files);
	}

	@Override
	public List<GalleryDTO> selectAll() {
		List<GalleryDTO> gList = gDao.selectAll();
		log.debug("갤러리 리스트 : {}",gList.toString());
		return gList;
	}

	@Override
	public List<GalleryFilesDTO> findByIdGalleryFiles(Long g_seq) {
		List<GalleryFilesDTO> gfList = gDao.findByIdGalleryFiles(g_seq);
		/*
		 * dao로부터 select를 한 후에 데이터 검증 하기 위해 사용하는 코드
		 * gfList 데이터가 조회되지 않아 null이 발생할 수 있다 
		 */
		if(gfList != null || gfList.size() > 0) {
			log.debug("gfList {}",gfList.toString());
		} else {
			log.debug("조회된 데이터가 없음");
		}
		return gfList;
	}

	@Override
	public GalleryDTO findByIdGallery(Long g_seq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Long g_seq) {
		// TODO Auto-generated method stub
		return 0;
	}
}

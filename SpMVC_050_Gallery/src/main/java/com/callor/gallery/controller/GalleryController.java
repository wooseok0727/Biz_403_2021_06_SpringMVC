package com.callor.gallery.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.model.MemberVO;
import com.callor.gallery.service.GalleryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	@Qualifier("galleryServiceV2")
	protected final GalleryService gService;
	
	/*
	 * 주소창에 직접 입력 후 Enter 로 요청할 때 Request를 처리
	 * 		localhost:8080/rootPath/gallery/dummy
	 * a tag를 클릭했을 때 
	 * 		<a href="${rootPath}/gallery/dummy">열기</a>
	 * JS
	 * 		location.href="${rootPath}/gallery/dummy" 
	 */
	@RequestMapping(value="/dummy",method=RequestMethod.GET)
	public String dummy() {
		
		return "home";
	}
	
	/*
	 * <form action="${rootPath}/dummy" method="POST">
	 * 		<input name="str">
	 * 		<button type="submit">전송</button>
	 * </form>
	 */
	@RequestMapping(value="/dummy",method=RequestMethod.POST)
	public String dummy(String str) {
		
		return "home";
	}
	
	// localhost:8080/rootPath/gallery/ 또는
	// localhost:8080/rootPath/gallery 로 요청했을 때
	@RequestMapping(value= {"/",""},method=RequestMethod.GET)
	public String list(@RequestParam(value="pageNum",required = false, defaultValue = "1") String pageNum,
					   @RequestParam(value="search_column", required=false, defaultValue = "NONE") String search_column,
					   @RequestParam(value="search_text", required=false, defaultValue = "NONE") String search_text,
					   Model model) throws Exception {
		
		
		int intPageNum = Integer.valueOf(pageNum);
		if(intPageNum > 0) {
			model.addAttribute("PAGE_NUM",intPageNum);
			
		}
		// tbl_gallery table 전체 List를 가져와서
		// 전체 리스트를 표시하기 위해서 몇페이지의 nav가 필요한지
		
		// List<GalleryDTO> gList = gService.selectAllPage(intPageNum, model);
		// model.addAttribute("GALLERYS",gList);
		
		// search_column, search_text를 사용하여 조건검색
		gService.findBySearchPage(search_column, search_text, intPageNum, model);
		
		
		model.addAttribute("BODY","G_LIST");
		
		return "home";
	}
	
	@RequestMapping(value= "/input", method=RequestMethod.GET)
	public String input(Model model, HttpSession hSession) {
		
		MemberVO memberVO = (MemberVO) hSession.getAttribute("MEMBER");
		if(memberVO == null) {
			return "redirect:/member/login";
		}
		
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("hh:mm:ss");
		
		String curDate = sd.format(date);
		String curTime = st.format(date); 
		
		GalleryDTO gDTO = GalleryDTO.builder().g_date(curDate).g_time(curTime).g_writer("ws").build();
		
		model.addAttribute("CMD",gDTO);
		model.addAttribute("BODY","G_INPUT");
		return "home";
	}
	
	@RequestMapping(value="/input", method=RequestMethod.POST)
	public String input(
			GalleryDTO gDTO,MultipartFile one_file,
			MultipartHttpServletRequest m_file,Model model) throws Exception {
		
		log.debug("갤러리 정보 : {}", gDTO.toString());
		log.debug("싱글 파일 : {}", one_file.getOriginalFilename());
		log.debug("멀티 파일 : {}", m_file.getFileMap().toString());
		
		gService.input(gDTO, one_file, m_file);
		
		
		return "redirect:/gallery";
	}
	
	@RequestMapping(value="/detail/{seq}",method=RequestMethod.GET)
	public String detail(@PathVariable("seq") String seq, Model model) {
		
		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			return "redirect:/gallery";
		}
		
		List<GalleryFilesDTO> gfList = gService.findByIdGalleryFiles(g_seq);
		model.addAttribute("GFLIST",gfList);
		model.addAttribute("BODY","G_DETAIL");
		
		return "home";
	}
	
	@RequestMapping(value="/detail2/{seq}",method=RequestMethod.GET)
	public String detail(@PathVariable("seq") String seq, Model model, HttpSession hSession) {
		
		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("갤러리 ID 값 오류");
			return "redirect:/gallery";
		}
		GalleryDTO galleryDTO = gService.findByIdGallery(g_seq);
		model.addAttribute("GALLERY",galleryDTO);
		model.addAttribute("BODY","G_DETAIL2");
		return "home";
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("g_seq") String seq, HttpSession hSession) {
		
		// 삭제를 요구하면
		// 1. 로그인이 되었나 확인
		// MemberVO memberVO = (MemberVO) hSession.getAttribute("MEMBER");
		// if(memberVO == null ) {
		//	return "redirect:/member/login";
		// }
		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			log.debug("갤러리 SEQ 오류");
			return "redirect:/";
		}
		int result = gService.delete(g_seq);
		return "redirect:/gallery";
	}
	
	@ResponseBody
	@RequestMapping(value="/file/delete/{seq}",method=RequestMethod.GET)
	public String file_delete(@PathVariable("seq") String seq){
		
		Long g_seq = 0L;
		try {
			g_seq = Long.valueOf(seq);
		} catch (Exception e) {
			// TODO: handle exception
			return "FAIL_SEQ";
		}
		int result = gService.file_delete(g_seq);
		if(result > 0) return "OK";
		else return "FAIL";
	}
}

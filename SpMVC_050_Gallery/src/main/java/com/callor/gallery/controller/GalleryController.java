package com.callor.gallery.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.callor.gallery.model.GalleryDTO;
import com.callor.gallery.model.GalleryFilesDTO;
import com.callor.gallery.service.GalleryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
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
	public String list(Model model) {
		
		List<GalleryDTO> gList = gService.selectAll();
		model.addAttribute("GALLERYS",gList);
		model.addAttribute("BODY","G_LIST");
		
		return "home";
	}
	
	@RequestMapping(value= "/input", method=RequestMethod.GET)
	public String input(Model model) {
		
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
}

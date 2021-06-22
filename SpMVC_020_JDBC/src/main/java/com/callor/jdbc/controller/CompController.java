package com.callor.jdbc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.jdbc.model.CompanyVO;
import com.callor.jdbc.persistence.CompDao;
import com.callor.jdbc.service.CompService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/comp")
public class CompController {
	
	protected final CompDao compDao;
	protected final CompService compService;
	
	public CompController(CompDao compDao, CompService compService) {
		this.compDao = compDao;
		this.compService = compService;
	}
	
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public String list(HttpSession hSession, Model model) {
		
		if(hSession.getAttribute("USERVO") == null ) {
			model.addAttribute("MSG","LOGIN");
			return "redirect:/member/login";
		}
		
		List<CompanyVO> compList = compService.selectAll();
		log.debug("출판사 정보 가져오기: {} ",compList);
		model.addAttribute("COMPS",compList);
		return "comp/list";
	}
	
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String getList(Model model) {
		List<CompanyVO> compList = compService.selectAll();
		model.addAttribute("COMPS",compList);
		return "comp/search";
	}
	
	// localhost:8080/jdbc/comp/insert로 호출되는 함수
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert() {
		
		// WEB-INF/views/comp/input.jsp 열어라
		return "comp/input";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(CompanyVO companyVO) {
		
		log.debug("Company VO {}",companyVO.toString());
		compService.insert(companyVO);
		return "redirect:/";
	}
	
	
	
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public String update() {
		
		return "comp/input";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	// 매개변수 이름은 web에서 보내는 이름과 같아야 한다. (String cpcode)
	// 굳이 다른 명칭으로 사용하고싶다면 @RequestParam("cp_code") String **** ) 이런식으로 사용해야함
	public String delete(@RequestParam("cp_code") String cpCode) {
		compDao.delete(cpCode);
		return "redirect:/";
	}
}

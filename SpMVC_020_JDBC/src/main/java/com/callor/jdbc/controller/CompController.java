package com.callor.jdbc.controller;

import org.springframework.stereotype.Controller;
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

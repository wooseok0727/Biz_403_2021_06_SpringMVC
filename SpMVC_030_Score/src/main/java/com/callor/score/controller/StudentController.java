package com.callor.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.callor.score.model.ScoreInputVO;
import com.callor.score.model.StudentVO;
import com.callor.score.model.SubjectAndScoreDTO;
import com.callor.score.service.ScoreService;
import com.callor.score.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/student")
public class StudentController {

	protected final StudentService stService;
	// protected final ScoreService scService;
	
	@RequestMapping(value = {"/",""},method=RequestMethod.GET)
	public String list(Model model) {
		
		List<StudentVO> stList = stService.selectAll();
		model.addAttribute("STLIST",stList);
		
		// String BODY = "STUDENT_LIST";
		// view.rendering(BODY)
		model.addAttribute("BODY","STUDENT_LIST");
		return "home";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.GET)
	public String insert(Model model) {
		
		StudentVO stVO = new StudentVO();
		stVO.setSt_num(stService.makeStNum()); 
		
		model.addAttribute("STD",stVO);
		model.addAttribute("BODY","STUDENT_INPUT");
		return "home";
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	public String insert(@ModelAttribute StudentVO studentVO, Model model) {
		
		log.debug("Req 학생정보 : {}", studentVO.toString());
		int ret = stService.insert(studentVO);
		log.debug("insert ret 결과 : {}",ret);
		model.addAttribute("BODY","STUDENT_INPUT");
		return "redirect:/student";
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String detail(String st_num, Model model) {
		
		// List<SubjectAndScoreDTO> ssList = scService.selectScore(st_num);
		
		String ret = stService.detail(model,st_num);
		log.debug("detail ret 결과 : {}",ret);
		// StudentVO stVO = stService.find
		// model.addAttribute("SSLIST",ssList);
		
		model.addAttribute("BODY","STUDENT_DETAIL");
		return "home";
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.POST)
//		public String detail(@RequestParam(name="subject") List<String> subject, 
//							 @RequestParam(name="score") List<String> score) {
	public String detail(ScoreInputVO scInputVO) {
	
//		log.debug("Subject: {} ", subject.toString());
//		log.debug("Score: {} ", score.toString());
		
		log.debug("Score Input {}", scInputVO.toString());
		
		String ret = stService.scoreInput(scInputVO);
		
		return "home";
	}
}

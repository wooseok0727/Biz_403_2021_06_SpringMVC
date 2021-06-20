package com.callor.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.callor.score.model.ScoreVO;
import com.callor.score.model.StudentVO;
import com.callor.score.service.ScoreService;
import com.callor.score.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/editlist")
public class EditController {

	protected final ScoreService scoreService;
	protected final StudentService studentService;
	
	public EditController(ScoreService scoreService, StudentService studentService	) {
		this.scoreService = scoreService;
		this.studentService = studentService;
	}
	
	@RequestMapping(value= {"/",""}, method=RequestMethod.GET)
	public String list(Model model, String st_num, @RequestParam(value="stnum", defaultValue="") String stnum) {
		
		if(st_num == null) {
			st_num = stnum;
		}
		StudentVO stVO = studentService.findById(st_num);
		List<ScoreVO> scList = scoreService.findByStNum(st_num);
		log.debug("홈에서 {}", scList);
		model.addAttribute("ST", stVO);
		model.addAttribute("SC", scList);
		return "editlist/viewInfo";
	}
	
	@RequestMapping(value= {"/",""},method=RequestMethod.POST)
	public String update(@ModelAttribute StudentVO vo ) {
		log.debug("StudentVO {}",vo);
		studentService.update(vo);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/scinsert", method=RequestMethod.POST)
	public String insert(@ModelAttribute ScoreVO vo, RedirectAttributes rattr) {
		
		scoreService.insert(vo);
		rattr.addAttribute("stnum",vo.getSc_stnum());
		return "redirect:/editlist";
	}
	
	@RequestMapping(value="/stdelete", method=RequestMethod.GET)
	public String stDelete(Model model, String st_num) {
		log.debug("st_num",st_num);
		studentService.delete(st_num);
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/scdelete", method=RequestMethod.POST)
	public String scDelete(String sc_seq, String st_num, RedirectAttributes rattr) {
		log.debug("점수번호 {}",sc_seq);
		log.debug("학생번호 {}",st_num);
		scoreService.delete(sc_seq);
		rattr.addAttribute("stnum",st_num);
		
		return "redirect:/editlist";
	}
}

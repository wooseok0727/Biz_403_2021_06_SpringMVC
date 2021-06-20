package com.callor.score.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.model.StudentVO;
import com.callor.score.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="/stInfo")
public class StudentController {

	protected final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@RequestMapping(value = {"/",""}, method = RequestMethod.GET)
	public String home(Model model) {
		
		List<StudentVO> stVO = studentService.selectAll();
		
		model.addAttribute("ST",stVO);
		return "student/stInfo";
	}
	
	@RequestMapping(value="/stinsert", method=RequestMethod.POST)
	public String insert(@ModelAttribute StudentVO vo) {
		
		studentService.insert(vo);
		return "redirect:/stInfo";
	}
}

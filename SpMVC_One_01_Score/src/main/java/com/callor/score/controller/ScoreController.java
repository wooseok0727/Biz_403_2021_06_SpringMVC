package com.callor.score.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.callor.score.model.ScoreDTO;
import com.callor.score.service.ListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value="scInfo")
public class ScoreController {
	
	protected final ListService listService;
	
	public ScoreController(ListService listService) {
		this.listService = listService;
	}
	
	@RequestMapping(value= {"/",""}, method= RequestMethod.GET)
	public String home(Model model) {
		
		List<ScoreDTO> scDTO = listService.viewStudentAndScore2();
		
		model.addAttribute("SC",scDTO);
		return "score/scInfo";
	}

}

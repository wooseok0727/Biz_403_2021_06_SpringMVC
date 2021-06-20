package com.callor.score.service;

import java.util.List;

import com.callor.score.model.ListDTO;
import com.callor.score.model.ScoreDTO;

public interface ListService {

	public List<ListDTO> viewStudentAndScore();
	public List<ScoreDTO> viewStudentAndScore2();
}

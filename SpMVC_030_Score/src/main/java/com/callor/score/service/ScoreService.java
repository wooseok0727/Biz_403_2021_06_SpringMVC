package com.callor.score.service;

import java.util.List;

import com.callor.score.model.ScoreDTO;
import com.callor.score.model.ScoreVO;
import com.callor.score.model.SubjectAndScoreDTO;

public interface ScoreService {

	public List<ScoreVO> selectAll();
	public List<ScoreDTO> selectViewAll();
	
	public List<SubjectAndScoreDTO> selectScore(String st_num);
	
}

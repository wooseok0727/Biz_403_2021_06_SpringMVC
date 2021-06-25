package com.callor.score.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.score.dao.extend.ScoreDao;
import com.callor.score.model.ScoreDTO;
import com.callor.score.model.ScoreVO;
import com.callor.score.model.SubjectAndScoreDTO;
import com.callor.score.service.ScoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service("scoreServiceV1")
public class ScoreServiceImplV1 implements ScoreService {
	
	protected final ScoreDao scDao;
	
	@Override
	public List<ScoreVO> selectAll() {
		
		List<ScoreVO> scList = scDao.selectAll();
		return scList;
	}

	@Override
	public List<ScoreDTO> selectViewAll() {
		
		List<ScoreDTO> scViewList = scDao.selectViewAll();
		log.debug("scoreService {}",scViewList);
		return scViewList;
	}

	@Override
	public List<SubjectAndScoreDTO> selectScore(String st_num) {
		
		List<SubjectAndScoreDTO> ssList = scDao.selectSubjectAndScore(st_num);
		
		return ssList;
	}

}

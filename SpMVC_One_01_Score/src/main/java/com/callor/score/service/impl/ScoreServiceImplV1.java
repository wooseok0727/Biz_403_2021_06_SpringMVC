package com.callor.score.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.score.model.ScoreDTO;
import com.callor.score.model.ScoreVO;
import com.callor.score.persistence.ScoreDao;
import com.callor.score.service.ScoreService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("scoreServiceV1")

public class ScoreServiceImplV1 implements ScoreService {
	
	protected final ScoreDao scoreDao;
	
	public ScoreServiceImplV1(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}
	
	@Override
	public List<ScoreVO> selectAll() {
		
		return scoreDao.selectAll();
	}

	@Override
	public List<ScoreVO> findByStNum(String stNum) {
		
		return scoreDao.findByStNum(stNum);
	}

	@Override
	public int insert(ScoreVO vo) {
		String scSeq = scoreDao.findByMaxCode();
		log.debug("scSeq {}", scSeq);
		
		if(scSeq== null || scSeq.equals("")) {
			scSeq = String.format("%08d", 1);
		} else {
			Integer intCode = Integer.valueOf(scSeq) + 1;
			scSeq = String.format("%08d", intCode);
		}
		vo.setSc_seq(scSeq);
		scoreDao.insert(vo);
		
		return 0;
	}

	@Override
	public int update(ScoreVO vo) {
		
		scoreDao.update(vo);
		return 0;
	}

	@Override
	public int delete(String pk) {
		
		scoreDao.delete(pk);
		return 0;
	}

}

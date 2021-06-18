package com.callor.score.persistence;

import java.util.List;

import com.callor.score.model.ScoreDTO;
import com.callor.score.model.ScoreVO;

public interface ScoreDao extends GenericDao<ScoreVO, String> {

	public String findByMaxCode();
	public List<ScoreVO> findByStNum(String stNum);
}

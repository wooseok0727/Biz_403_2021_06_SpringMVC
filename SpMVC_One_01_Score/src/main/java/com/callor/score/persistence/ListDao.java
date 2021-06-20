package com.callor.score.persistence;

import java.util.List;

import com.callor.score.model.ListDTO;
import com.callor.score.model.ScoreDTO;

public interface ListDao extends GenericDao<ListDTO, String> {

	public List<ScoreDTO> selectAll2();
}

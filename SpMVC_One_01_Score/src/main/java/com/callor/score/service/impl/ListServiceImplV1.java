package com.callor.score.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.score.model.ListDTO;
import com.callor.score.model.ScoreDTO;
import com.callor.score.persistence.ListDao;
import com.callor.score.service.ListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("listServiceV1")
public class ListServiceImplV1 implements ListService{

	protected final ListDao listDao;
	
	public ListServiceImplV1(ListDao listDao) {
		this.listDao = listDao;
	}
	@Override
	public List<ListDTO> viewStudentAndScore() {
		
		return listDao.selectAll();
	}
	@Override
	public List<ScoreDTO> viewStudentAndScore2() {
		
		return listDao.selectAll2();
	}

}

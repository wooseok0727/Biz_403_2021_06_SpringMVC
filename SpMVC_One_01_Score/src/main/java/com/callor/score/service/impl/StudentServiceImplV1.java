package com.callor.score.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.score.model.StudentVO;
import com.callor.score.persistence.StudentDao;
import com.callor.score.service.StudentService;
import com.google.protobuf.Value;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("studentServiceV1")
public class StudentServiceImplV1 implements StudentService {
	
	protected final StudentDao studentDao;
	
	public StudentServiceImplV1(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	@Override
	public List<StudentVO> selectAll() {
		
		return studentDao.selectAll();
	}

	@Override
	public StudentVO findById(String pk) {
		
		return studentDao.findById(pk);
	}

	@Override
	public List<StudentVO> findByName(String name) {
		
		return studentDao.findByName(name);
	}

	@Override
	public int insert(StudentVO vo) {
		String stNum = studentDao.findByMaxCode();
		log.debug("stNum {}", stNum);
		
		if(stNum == null || stNum.equals("")) {
			stNum = String.format("2021%04d", 1);
		} else {
			Integer intCode = Integer.valueOf(stNum) + 1;
			stNum = String.valueOf(intCode);
		}
		log.debug("stNum {}", stNum);
		vo.setSt_num(stNum);
		studentDao.insert(vo);
		
		return 0;
	}

	@Override
	public int update(StudentVO vo) {
		
		studentDao.update(vo);
		return 0;
	}

	@Override
	public int delete(String pk) {
		studentDao.delete(pk);
		return 0;
	}

}

package com.callor.score.service;

import java.util.List;

import com.callor.score.model.StudentVO;

public interface StudentService {

	public List<StudentVO> selectAll();
	public StudentVO findById(String pk);
	public List<StudentVO> findByName(String name);
	public int insert(StudentVO vo);
	public int update(StudentVO vo);
	public int delete(String pk);
}

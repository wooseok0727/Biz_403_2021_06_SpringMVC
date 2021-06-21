package com.callor.jdbc.service;

import java.util.List;

import com.callor.jdbc.model.CompanyVO;

public interface CompService {
	
	public int insert(CompanyVO vo);
	public List<CompanyVO> findByCName(String cp_title);
	public List<CompanyVO> selectAll();
	public CompanyVO findByCCode(String cp_code);
}

package com.callor.jdbc.persistence;

import java.util.List;

import com.callor.jdbc.model.CompanyVO;

public interface CompDao extends GenericDao<CompanyVO, String>{
	
	public String findByMaxCode();
	public List<CompanyVO> findByCName(String ctitle);
	public List<CompanyVO> findByTel(String tel);
	public List<CompanyVO> findByCeo(String ceo);
}

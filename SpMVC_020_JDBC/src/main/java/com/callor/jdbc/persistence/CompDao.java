package com.callor.jdbc.persistence;

import java.util.List;

import com.callor.jdbc.model.CompanyVO;

public interface CompDao extends GenericDao<CompanyVO, String>{
	
	public List<CompanyVO> findByCName(String cname);
	public List<CompanyVO> findByTel(String tel);
	public List<CompanyVO> findByCeo(String ceo);
}

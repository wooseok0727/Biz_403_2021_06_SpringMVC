package com.callor.jdbc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.callor.jdbc.model.CompanyVO;
import com.callor.jdbc.persistence.CompDao;
import com.callor.jdbc.service.CompService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("compServiceV1")
public class CompServiceImplV1 implements CompService {

	protected final CompDao compDao;
	public CompServiceImplV1(CompDao compDao) {
		this.compDao = compDao;
	}
	
	@Override
	public int insert(CompanyVO vo) {
		// TODO Auto-generated method stub
		String cpCode = compDao.findByMaxCode();
		log.debug("Cpcode {}", cpCode);
		
		if(cpCode == null || cpCode.equals("")) {
			// C0001
			cpCode = String.format("C%04d", 1);
		} else {
			// 영문 접두사 C를 자르고 숫자만 추출
			String _code = cpCode.substring(1);
			Integer intCode = Integer.valueOf(_code) + 1;
			
			cpCode = String.format("C%04d", intCode);
		}
		vo.setCp_code(cpCode);
		compDao.insert(vo);
		
		return 0;
	}

	@Override
	public List<CompanyVO> findByCName(String cp_title) {
		
		// 전달받은 출판사 이름에서 앞뒤의 빈칸을 제거하고
		// Dao에게 Toss한 후 
		// 출판사 리스트를 받아 다시 return하기
		return compDao.findByCName(cp_title.trim());
	}

	@Override
	public List<CompanyVO> selectAll() {
		return compDao.selectAll();
	}

	@Override
	public CompanyVO findByCCode(String cp_code) {
		return compDao.findById(cp_code.trim());
	}

	@Override
	public List<CompanyVO> findByTitleAndCeoAndTel(String text) {
		// TODO Auto-generated method stub
		List<CompanyVO> mainList = compDao.findByCName(text);
		List<CompanyVO> ceoList = compDao.findByCeo(text);
		List<CompanyVO> telList = compDao.findByTel(text);
		
		mainList.addAll(ceoList);
		mainList.addAll(telList);
		
		return mainList;
	}

}

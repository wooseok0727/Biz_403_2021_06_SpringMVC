package com.callor.jdbc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.callor.jdbc.model.AuthorVO;
import com.callor.jdbc.persistence.AuthorDao;
import com.callor.jdbc.service.AuthorService;

@Service("authorServiceV1")
public class AuthorServiceImplV1 implements AuthorService {
	
	@Autowired
	protected AuthorDao authorDao;
	
	@Override
	public List<AuthorVO> selectAll() {
		return authorDao.selectAll();
	}

	@Override
	public AuthorVO findByACode(String au_code) {
		return authorDao.findById(au_code.trim());
	}

	@Override
	public List<AuthorVO> findByAName(String au_name) {
		return authorDao.findByAName(au_name.trim());
	}

	@Override
	public List<AuthorVO> findByATel(String au_tel) {
		return authorDao.findByATel(au_tel.trim());
	}

}

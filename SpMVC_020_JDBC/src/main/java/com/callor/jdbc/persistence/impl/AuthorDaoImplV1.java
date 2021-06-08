package com.callor.jdbc.persistence.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.AuthorVO;
import com.callor.jdbc.persistence.AuthorDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("authorDaoV1")
public class AuthorDaoImplV1 implements AuthorDao {
	
	protected final JdbcTemplate jdbcTemplate;
	
	public AuthorDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<AuthorVO> selectAll() {
		// TODO 여기는 작가 전체 조회
		String sql = " SELECT * FROM tbl_author ";
		
		List<AuthorVO> authorList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<AuthorVO>(AuthorVO.class));
		log.debug("Author SELECT {}",authorList.toString());
		return authorList;
	}

	@Override
	public AuthorVO findById(String pk) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(AuthorVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AuthorVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AuthorVO> findByAName(String aname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthorVO> findByATel(String atel) {
		// TODO Auto-generated method stub
		return null;
	}

}

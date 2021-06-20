package com.callor.score.persistence.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.callor.score.model.ScoreDTO;
import com.callor.score.model.ScoreVO;
import com.callor.score.persistence.ScoreDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("scoreDaoV1")
public class ScoreDaoImplV1 implements ScoreDao {
	
	protected JdbcTemplate jdbcTemplate;
	
	public ScoreDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<ScoreVO> selectAll() {
		
		String sql = " SELECT * FROM tbl_score ";
		RowMapper<ScoreVO> scoreMapper = new BeanPropertyRowMapper<>(ScoreVO.class);
		List<ScoreVO> scList = jdbcTemplate.query(sql, scoreMapper);
		log.debug("list {} ", scList);
		return scList;
	}
	
	public List<ScoreVO> findByStNum(String stNum) {
		
		String sql = " SELECT * FROM tbl_score ";
		sql += " WHERE sc_stnum = ? ";
		RowMapper<ScoreVO> studentMapper = new BeanPropertyRowMapper<ScoreVO>(ScoreVO.class);
		List<ScoreVO> sclist = jdbcTemplate.query(sql, studentMapper, stNum);
		log.debug("list {}",sclist);
		return sclist;
	}

	@Override
	public ScoreVO findById(String stnum) {
		return null;
	}

	@Override
	public int insert(ScoreVO vo) {
		String sql = " INSERT INTO tbl_score ";
		sql += " (sc_seq,sc_stnum,sc_subject,sc_score) ";
		sql += " VALUES( ?, ?, ?, ?) ";
		
		Object[] params = new Object[] {
				vo.getSc_seq(),
				vo.getSc_stnum(),
				vo.getSc_subject(),
				vo.getSc_score()
		};
		return jdbcTemplate.update(sql,params);
	}

	@Override
	public int update(ScoreVO vo) {
		
		String sql = " UPDATE tbl_score SET ";
		sql += " sc_stnum = ?, ";
		sql += " sc_subject = ?, ";
		sql += " sc_score = ? ";
		sql += " WHERE sc_seq = ? ";
		
		Object[] params = new Object[] {
				vo.getSc_seq(),
				vo.getSc_stnum(),
				vo.getSc_subject(),
				vo.getSc_score()
		};
		return jdbcTemplate.update(sql,params);
		
	}
	@Override
	public int delete(String pk) {
		String sql = " DELETE FROM tbl_score ";
		sql += " WHERE sc_seq = ? ";
		return jdbcTemplate.update(sql,pk);
	}
	
	public String findByMaxCode() {
		String sql = " SELECT MAX(sc_seq) FROM tbl_score ";
			
		String scSeq = (String) jdbcTemplate.queryForObject(sql, String.class);
		return scSeq;
	}

}

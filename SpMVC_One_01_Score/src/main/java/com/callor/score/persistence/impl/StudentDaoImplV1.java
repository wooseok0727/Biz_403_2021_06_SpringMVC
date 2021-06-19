package com.callor.score.persistence.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.callor.score.model.StudentVO;
import com.callor.score.persistence.StudentDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("studentDaoV1")
public class StudentDaoImplV1 implements StudentDao {
	
	protected JdbcTemplate jdbcTemplate;
	
	public StudentDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<StudentVO> selectAll() {
		String sql = " SELECT * FROM tbl_student ";
		RowMapper<StudentVO> studentMapper = new BeanPropertyRowMapper<StudentVO>(StudentVO.class);
		List<StudentVO> stlist = jdbcTemplate.query(sql, studentMapper);
		log.debug("list {}",stlist);
		return stlist;
	}

	@Override
	public StudentVO findById(String pk) {
		
		String sql = " SELECT * FROM tbl_student ";
		sql += " WHERE st_num = ? ";
		RowMapper<StudentVO> studentMapper = new BeanPropertyRowMapper<>(StudentVO.class);
		StudentVO stVO = jdbcTemplate.queryForObject(sql, studentMapper,pk);
		log.debug("stVO {}",stVO);
		return stVO;
	}
	
	@Override
	public List<StudentVO> findByName(String name) {
		String sql = " SELECT * FROM tbl_student ";
		sql += " WHERE st_name = ? ";
		RowMapper<StudentVO> studentMapper = new BeanPropertyRowMapper<StudentVO>(StudentVO.class);
		List<StudentVO> stlist = jdbcTemplate.query(sql, studentMapper, name);
		log.debug("list {}",stlist);
		return stlist;
	}

	@Override
	public int insert(StudentVO vo) {
		String sql = " INSERT INTO tbl_student ";
		sql += " (st_num,st_name,st_dept,st_grade,st_tel,st_addr) ";
		sql += " VALUES( ?, ?, ?, ?, ?, ?) ";
		
		Object[] params = new Object[] {
				vo.getSt_num(),
				vo.getSt_name(),
				vo.getSt_dept(),
				vo.getSt_grade(),
				vo.getSt_tel(),
				vo.getSt_addr()
		};
		return jdbcTemplate.update(sql,params);
	}

	@Override
	public int update(StudentVO vo) {
		
		String sql = " UPDATE tbl_student SET ";
		sql += " st_name = ?,";
		sql += " st_dept = ?,";
		sql += " st_grade = ?,";
		sql += " st_tel = ?,";
		sql += " st_addr = ?";
		sql += " WHERE st_num = ? ";
		
		Object[] params = new Object[] {
				vo.getSt_name(),
				vo.getSt_dept(),
				vo.getSt_grade(),
				vo.getSt_tel(),
				vo.getSt_addr(),
				vo.getSt_num()
		};
		return jdbcTemplate.update(sql,params);
	}

	@Override
	public int delete(String pk) {
		String sql = " DELETE FROM tbl_student ";
		sql += " WHERE st_num = ? ";

		return jdbcTemplate.update(sql,pk);
	}
	
	public String findByMaxCode() {
		String sql = " SELECT MAX(st_num) FROM tbl_student ";
			
		String st_Num = (String) jdbcTemplate.queryForObject(sql, String.class);
		return st_Num;
	}
}

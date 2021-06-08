package com.callor.jdbc.persistence.impl;

import java.util.List;import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.CompanyVO;
import com.callor.jdbc.persistence.CompDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("compDaoV1")
public class CompDaoImplV1 implements CompDao {

	protected final JdbcTemplate jdbcTemplate;
	
	public CompDaoImplV1(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<CompanyVO> selectAll() {
		// TODO 여기는 출판사 전체 조회
		String sql = " SELECT * FROM tbl_company ";
		
		List<CompanyVO> companyList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<CompanyVO>(CompanyVO.class));
		log.debug("Company SELECT {}",companyList.toString());
		return companyList;
	}

	@Override
	public CompanyVO findById(String pk) {
		// TODO Auto-generated method stub
		String sql = " SELECT * FROM tbl_company ";
		sql += " WHERE cp_code = ? ";
		Object[] params = new Object[] {pk};
		CompanyVO vo = (CompanyVO) jdbcTemplate.query(sql,params,new BeanPropertyRowMapper<CompanyVO>(CompanyVO.class));
		log.debug("Comp PK SELECT {}",vo.toString() );
		return null;
	}

	@Override
	public int insert(CompanyVO vo) {
		// TODO Auto-generated method stub
		String sql = " INSERT INTO tbl_company ";
		sql += "(cp_code, cp_title, cp_ceo, cp_tel, cp_addr) ";
		sql += " VALUES( ?,?,?,?,? ) ";
		
		Object[] params = new Object[] {
				vo.getCp_code(),
				vo.getCp_title(),
				vo.getCp_ceo(),
				vo.getCp_tel(),
				vo.getCp_addr()
		};
		return jdbcTemplate.update(sql,params);
	}

	@Override
	public int update(CompanyVO vo) {
		// TODO Auto-generated method stub
		String sql = " UPDATE tbl_company SET ";
		sql += " cp_title = ?,";
		sql += " cp_ceo = ?,";
		sql += " cp_tel = ?,";
		sql += " cp_addr = ?";
		sql += " WHERE cp_code = ? ";
		
		Object[] params = new Object[] {
			vo.getCp_title(),
			vo.getCp_ceo(),
			vo.getCp_tel(),
			vo.getCp_addr(),
			vo.getCp_code()
		};
		return jdbcTemplate.update(sql,params);
	}

	@Override
	public int delete(String pk) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CompanyVO> findByCName(String cname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanyVO> findByCeo(String ceo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanyVO> findByTel(String tel) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.callor.jdbc.persistence.impl;

import java.util.List;import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.callor.jdbc.model.CompanyVO;
import com.callor.jdbc.persistence.CompDao;

import lombok.extern.slf4j.Slf4j;

/*
 * @Component
 * 모든 Component 를 대표하는 Annotation
 * 컴파일 과정에서 다소 비용이 많이 소요된다
 * 
 * Component Annotation
 * @Controller, @Service, @Repository 이러한 Annotation을 사용한다
 * Spring Container 에게 이 표식이 부착된 클래스를 bean으로 생성하여
 * 보관해 달라 라는 지시어
 * 
 * CompVo c = new CompVO()
 * Object o = new CompVO()
 * 
 * CompServiceImplV1 cs = new CompServiceImplV1();
 * CompService cs1 = new CompServiceImplV1();
 * 
 */

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
	public CompanyVO findById(String cp_code) {
		// TODO Auto-generated method stub
		String sql = " SELECT * FROM tbl_company ";
		sql += " WHERE cp_code = ? ";
		Object[] params = new Object[] {cp_code};
		CompanyVO vo = jdbcTemplate.queryForObject(sql,params,BeanPropertyRowMapper.newInstance(CompanyVO.class));
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
	
	/*
	 * jdbcTemplate을 사용하여 QUERY를 실행할때
	 * 각 method에서 매개변수를 받아
	 * QUERY에 전달할텐데
	 * 이때 매개변수는 primitive로 받으면 값을 변환시키는 어려움이 있다.
	 * 권장사항으로 매개변수는 wrapper class type으로 설정
	 * 즉 숫자형일 경우 int, long 대신 Integer, Long 형으로 선언
	 * 
	 * vo에 담겨서 전달된 값은 Object[] 배열로 변환한 후
	 * jdbcTemplate에 전달해 주어야 한다
	 * 
	 * 하지만, 1 ~ 2개 정도의 값을 전달할 때 Object[] 배열로 변환 후
	 * 전달을 하면 Object 객체 저장소가 생성되고 메모리를 사용한다
	 * 
	 * 이때 전달되는 변수가 wrapper class type 이면
	 * Object[] 배열로 만들지 않고 바로 주입할 수 있다
	 */
	@Override
	public int delete(String cpcode) {
		// 출판사 정보 삭제
		String sql = " DELETE FROM tbl_company ";
		sql += " WHERE cp_code = ? ";
		
		// cpcode가 String wrapper class type 이므로
		// Object[] 배열로 변환하지 않고 바로 전달이 가능하다
		// Object[] params = new Object[] {cpcode};
		jdbcTemplate.update(sql,cpcode);
		return 0;
	}

	/*
	 * 출판사 이름으로 검색하기
	 */
	@Override
	public List<CompanyVO> findByCName(String ctitle) {
		// TODO Auto-generated method stub
		String sql = " SELECT * FROM tbl_company ";
		// WHERE cp_code LIKE '%' || '%' // oracle
		sql += " WHERE cp_title LIKE CONCAT('%', ? , '%' )"; // mysql
		
		// SELECT를 수행한 후 각각의 데이터를 CompVO에 담고
		// List에 add하여 return 한 후 compList에 받기
		List<CompanyVO> compList = jdbcTemplate.query(sql, new Object[] { ctitle },
				new BeanPropertyRowMapper<CompanyVO>(CompanyVO.class));
		
		return compList;
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
	
	/*
	 * tbl_company table에서 cpcode(출판사코드) 중
	 * 가장 큰값을 추출하기
	 */
	@Override
	public String findByMaxCode() {
		String sql = " SELECT MAX(cp_code) FROM tbl_company ";
			
		String cpCode = (String) jdbcTemplate.queryForObject(sql, String.class);
		return cpCode;
	}

}

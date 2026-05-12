package kr.or.ddit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.mapper.CrudMapper;
import kr.or.ddit.vo.CrudVO;

@SpringBootTest
public class CrudMapperTest {
	
	@Autowired
	CrudMapper crudMapper;
	
	@Transactional
	@Test
	@DisplayName("CRUD맵퍼테스통")
	public void insertTest(){
		 CrudVO crudVO = new CrudVO();
		 crudVO.setCrudContent("그냥 웃지용");
		 crudVO.setCrudName("수진");
		 crudVO.setCrudFile("sujin.jpg");
		 assertEquals(1, crudMapper.insertCrud(crudVO));
	}
	
}

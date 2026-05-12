package kr.or.ddit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.service.CrudService;
import kr.or.ddit.vo.CrudVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class CrudController {
	@Autowired
	private CrudService crudService;
	
	// 1. Get List
	@GetMapping(value="/listCrud",produces="application/json;charset=utf-8")
	public List<CrudVO> listCrud() {
		log.info("listCrud");
		return this.crudService.listCrud();
	}

	// 2. Get One
	@GetMapping(value="/getCrud/{crudNum}",produces="application/json;charset=utf-8")
	public CrudVO getCrud(CrudVO crudVO,
			@PathVariable(value="crudNum") int crudNum) {
		log.info("getCrud->crudNum : " + crudNum);
		log.info("getCrud->crudVO : " + crudVO);
		return this.crudService.getCrud(crudVO);
	}

	// 3. insert
	@PostMapping(value="/insertCrud",produces="application/json;charset=utf-8")
	public int insertCrud(@RequestBody CrudVO crudVO) {
		log.info("insertCrud->crudVO : " + crudVO);
		
		int result = this.crudService.insertCrud(crudVO);
		log.info("insertCrud->result : " + result);
		
		return result;
	}

	// 4. update
	@PutMapping(value="/updateCrud",produces ="application/json;charset=utf-8")
	public int updateCrud(@RequestBody CrudVO crudVO) {
		log.info("updateCrud->crudVO : " + crudVO);
		return this.crudService.updateCrud(crudVO);
	}

	// 5. delete
	@DeleteMapping(value="/deleteCrud/{crudNum}",produces ="application/json;charset=utf-8")
	public int deleteCrud(CrudVO crudVO,
			@PathVariable(value="crudNum") int crudNum) {
		//deleteCrud->crudNum : 26
		log.info("deleteCrud->crudNum : " + crudNum);
		//deleteCrud->crudVO : CrudVO(crudNum=26, crudContent=null, crudName=null, crudFile=null)
		log.info("deleteCrud->crudVO : " + crudVO);
		return this.crudService.deleteCrud(crudVO);
	}
}

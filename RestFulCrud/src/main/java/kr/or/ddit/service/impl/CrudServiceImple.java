package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.CrudMapper;
import kr.or.ddit.service.CrudService;
import kr.or.ddit.vo.CrudVO;

@Service
public class CrudServiceImple implements CrudService {

	@Autowired
	private CrudMapper crudMapper;
	
	// Get List
	@Override
	public List<CrudVO> listCrud() {
		return this.crudMapper.listCrud();
	}

	// Get One
	@Override
	public CrudVO getCrud(CrudVO crudVO) {
		return this.crudMapper.getCrud(crudVO);
	}

	// insert
	@Override
	public int insertCrud(CrudVO crudVO) {
		return this.crudMapper.insertCrud(crudVO);
	}

	// update
	@Override
	public int updateCrud(CrudVO crudVO) {
		return this.crudMapper.updateCrud(crudVO);
	}

	// delete
	@Override
	public int deleteCrud(CrudVO crudVO) {
		return this.crudMapper.deleteCrud(crudVO);
	}
}

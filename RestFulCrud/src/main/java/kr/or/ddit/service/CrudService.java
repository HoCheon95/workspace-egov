package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.CrudVO;

public interface CrudService {
	// Get List
	List<CrudVO> listCrud();
	// Get One
	CrudVO getCrud(CrudVO sujinVO);
	// insert
	int insertCrud(CrudVO sujinVO);
	// update
	int updateCrud(CrudVO sujinVO);
	// delete
	int deleteCrud(CrudVO sujinVO);
}

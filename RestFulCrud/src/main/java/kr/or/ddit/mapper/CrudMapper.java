package kr.or.ddit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.CrudVO;

@Mapper
public interface CrudMapper {
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

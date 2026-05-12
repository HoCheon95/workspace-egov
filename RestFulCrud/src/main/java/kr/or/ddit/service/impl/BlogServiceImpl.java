package kr.or.ddit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.mapper.BlogMapper;
import kr.or.ddit.service.BlogService;
import kr.or.ddit.vo.ArticleVO;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogMapper blogMapper;
	
	//블로그 글 추가 메서드
	@Override
	public int save(ArticleVO articleVO) {
		return this.blogMapper.save(articleVO);
	}
	
	//ARTICLE 테이블에 저장되어 있는 모든 데이터를 조회함
	@Override
	public List<ArticleVO> findAll(){
		return this.blogMapper.findAll();
	}
	
	//글의 ID를 이용해 글을 조회함
	@Override
	public ArticleVO findById(Long id) {
		return this.blogMapper.findById(id);
	}
	
	/**id에 해당하는 블로그 글을 삭제
	 *블로그 글의 id를 받은 뒤 데이터베이스에서 해당 데이터를 삭제 
	 * @param id
	 * @return
	 */
	@Override
	public int delete(Long id) {
		return this.blogMapper.delete(id);
	}
	

	/** id에 해당하는 블로그 글 수정
	 * @param id, articleVO
	 * @return int
	 */
	@Override
	public int update(Long id, ArticleVO articleVO) {
		return this.blogMapper.update(articleVO);
	}
	
	//테스트 데이터를 입력하기 전에 모든 데이터 삭제
	@Override
	public int deleteTestDatas() {
		return this.blogMapper.deleteTestDatas();
	}
	
	//테스트 데이터를 3행 insert
	@Override
	public int insertTestDatas() {
		return this.blogMapper.insertTestDatas();
	}

	//글 상세
	@Override
	public ArticleVO getArticle(Long id) {
		return this.blogMapper.getArticle(id);
	}




}

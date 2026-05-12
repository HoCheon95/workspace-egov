package kr.or.ddit.service;

import java.util.List;

import kr.or.ddit.vo.ArticleVO;

public interface BlogService {
	
	//블로그 글 추가 메서드
	public int save(ArticleVO articleVO);

	//ARTICLE 테이블에 저장되어 있는 모든 데이터를 조회함
	public List<ArticleVO> findAll();
	
	//글의 ID를 이용해 글을 조회함
	public ArticleVO findById(Long id);

	/**id에 해당하는 블로그 글을 삭제
	 *블로그 글의 id를 받은 뒤 데이터베이스에서 해당 데이터를 삭제 
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	
	//글 수정
	public int update(Long id, ArticleVO articleVO);
	
	//글 상세
	public ArticleVO getArticle(Long id);
	
	//*** 테스트 데이터 시작 ***/
	//테스트 데이터를 3행 insert
	public int insertTestDatas();

	//테스트 데이터를 입력하기 전에 모든 데이터 삭제
	public int deleteTestDatas();
	//*** 테스트 데이터 끝 ***/

}

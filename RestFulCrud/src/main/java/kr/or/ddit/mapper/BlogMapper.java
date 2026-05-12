package kr.or.ddit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.ArticleVO;

@Mapper
public interface BlogMapper {

	//블로그 글 추가 메서드
	public int save(ArticleVO articleVO);

	//블로그 글 목록
	public List<ArticleVO> findAll();
	
	//글의 ID를 이용해 글을 조회함
	public ArticleVO findById(Long id);
	
	/**id에 해당하는 블로그 글을 삭제
	 *블로그 글의 id를 받은 뒤 데이터베이스에서 해당 데이터를 삭제 
	 * @param id
	 * @return
	 */
	public int delete(Long id);
	
	/** id에 해당하는 블로그 글 수정
	 * @param id, articleVO
	 * @return int
	 */
	public int update(ArticleVO articleVO);
	
	//테스트 데이터를 입력하기 전에 모든 데이터 삭제
	public int deleteTestDatas();
	
	//테스트 데이터를 3행 insert
	public int insertTestDatas();

	//글 상세
	public ArticleVO getArticle(Long id);



}

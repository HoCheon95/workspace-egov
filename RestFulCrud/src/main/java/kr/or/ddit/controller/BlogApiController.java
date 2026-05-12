package kr.or.ddit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.ddit.service.BlogService;
import kr.or.ddit.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;

//RestController : HTTP Response Body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
@RequestMapping("/api")
@Slf4j
@RestController
public class BlogApiController {
	
	@Autowired
	BlogService blogService;
	
	//HTTP 메서드가 POST일 때 전달받은 URL과 동일하면 메서드로 매핑
	@PostMapping("/articles")
	public ResponseEntity<ArticleVO> addArticle(@RequestBody ArticleVO articleVO){
		
		log.info("addArticle->articleVO : " + articleVO);
		
		int result = this.blogService.save(articleVO);
		log.info("addArticle->result : " + result);
		
		//요청한 자원이 성공적으로 생성되었으면 저장된 블로그 글 정보를 응답 객체에 담아 전송
		return ResponseEntity.status(HttpStatus.CREATED).body(articleVO);
	}
	
	//ARTICLE 테이블에 저장되어 있는 모든 데이터를 조회함
	@GetMapping("/articles")
	public ResponseEntity<List<ArticleVO>> findAllArticles(){
		
		//테스트 데이터를 입력하기 전에 모든 데이터 삭제
		int result = this.blogService.deleteTestDatas();
		log.info("findAllArticles->result(1) : " + result);
		
		//테스트 데이터를 3행 insert
		result += this.blogService.insertTestDatas();
		log.info("findAllArticles->result(2) : " + result);
		
		List<ArticleVO> articles = this.blogService.findAll();
		log.info("findAllArticles->articles : " + articles);
		
		//응답용 객체인 articles 객체를 body에 담아 클라이언트에 전송
		return ResponseEntity.ok().body(articles);
	}
	
	//글의 ID를 이용해 글을 조회함
	//PathVariable 애너테이션은 URL에서 값을 가져오는 애너테이션. 
	// 이 애너테이션이 붙은 메서드의 동작 원리는 /api/articles/3으로써 get 방식 요청을 받으면 id에 3이 들어옴.
	// 이 값은 앞서 만든 서비스 클래스의 findbyId() 메서드로 넘어가 3번 블로그의 글을 찾음.
	// 글을 찾으면 3번 글의 정보를 body에 담아 웹 브라우저(크롬)로 전송함
	@GetMapping("/articles/{id}")
	public ResponseEntity<ArticleVO> findArticle(@PathVariable Long id){
		log.info("findArticle->id : " + id);
		
		ArticleVO articleVO = this.blogService.findById(id);
		log.info("findArticle->articleVO : " + articleVO);
		
		return ResponseEntity.ok().body(articleVO);
	}
	
	/*
	id에 해당하는 블로그 글을 삭제
	 *블로그 글의 id를 받은 뒤 데이터베이스에서 해당 데이터를 삭제
	 *중괄호id중괄호에 해당하는 값이 PathVariable 애너테이션을 통해 들어옴 
	 */
	@DeleteMapping("/articles/{id}")
	public ResponseEntity<ArticleVO> deleteArticle(@PathVariable Long id){
		log.info("deleteArticle->id : " + id);
		
		int result = this.blogService.delete(id);
		log.info("deleteArticle->result : " + result);
		
		return ResponseEntity.ok().build();
	}
	
	//id에 해당하는 블로그 글 수정
	@PutMapping("/articles/{id}")
	public ResponseEntity<Integer> updateArticle(@PathVariable Long id,
			@RequestBody ArticleVO articleVO){
		int result = this.blogService.update(id, articleVO);
		
		return ResponseEntity.ok().body(result);
	}
}







package kr.or.ddit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.service.BlogService;
import kr.or.ddit.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/articles")
@Slf4j
@Controller
public class BlogViewController {
	
	@Autowired
	BlogService blogService;
	
	@ModelAttribute
	public void home(Model model) {
		model.addAttribute("mainTitle", "게시글 목록");
	}
	
	//게시글 목록
	@GetMapping("/list")
	public String getArticles(Model model) {
		List<ArticleVO> articleVOList = this.blogService.findAll();
		log.info("getArticles->articleVOList : " + articleVOList);
		
		//블로그 글 리스트 저장
		//addAttribute() 메서드를 사용해 모델에 값을 저장함.
		//	articlesVOList 키에 블로그 글들을, 즉, 글 리스트를 저장함
		model.addAttribute("articleVOList", articleVOList);
		
		//forwarding -> articleList.jsp라는 뷰 조회
		return "article/articleList";
	}
	
	//게시글 상세
	/*
	 요청URI : /articles/1
	 경로변수 : id
	 요청방식 : get
	 
	 경로변수 id -> 파라미터변수 id -> 매개변수 id
	 id에 URL로 넘어온 값을 받아 getArticle() 메서드를 호출하여
	 	글을 조회하고, 화면에서 사용할 모델에 데이터를 저장한 다음,
	 	보여줄 화면의 jsp 경로를 반환함
	 */
	@GetMapping("/{id}")
	public String getArticle(@PathVariable(value="id") Long id, Model model) {
		log.info("article->id : " + id);
		
		ArticleVO articleVO = this.blogService.getArticle(id);
		log.info("article->articleVO : " + articleVO);
		
		//model객체의 속성명인 articleVO에 articleVO 객체가 데이터로써 추가됨
		model.addAttribute("articleVO", articleVO);
		
		//article.jsp라는 뷰 조회
		return "article/article";
	}
	
	//게시글 수정/생성 기능
	//id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑(id는 없을 수도 있음)
	//쿼리 파라미터로 넘어온 id값은 newArticle() 메서드의 Long 타입의 id 인자에 매핑함
	//	id값은 없을 수도 있으므로, 즉, id가 있으면 수정 기능, 없으면 생성 기능	
	@GetMapping("/new-article")
	public String newArticle(@RequestParam(value="id", required=false) Long id,
			Model model) {
		
		if(id==null) {//id가 없으면 새 글 기능 실행
			//기본 생성자를 이용해 빈 ArticleVO 객체 생성
			model.addAttribute("articleVO", new ArticleVO(null, null));
		}else {//id가 있으면 수정 기능 실행. 기존 값을 가져오는 findById() 메서드를 호출함
			ArticleVO articleVO = this.blogService.findById(id);
			model.addAttribute("articleVO", articleVO);
		}
		
		return "article/newArticle";
	}
}






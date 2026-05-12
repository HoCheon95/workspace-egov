package kr.or.ddit.vo;

import java.util.Date;

import lombok.Data;

//엔티티에 생성 시간과 수정 시간을 추가해 글이 언제 생성되었는지 뷰에서 확인해보자
/*
 CREATE TABLE ARTICLE(
 	ID NUMBER,
 	TITLE VARCHAR2(150),
 	CONTENT VARCHAR2(900),
 	CREATED_AT DATE,
 	CONSTRAINT PK_ARTICLE PRIMARY KEY(ID)
 );
 */
@Data
public class ArticleVO {
	private Long id;
	private String title;
	private String content;
	private Date   createdAt;
	
	public ArticleVO(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
}

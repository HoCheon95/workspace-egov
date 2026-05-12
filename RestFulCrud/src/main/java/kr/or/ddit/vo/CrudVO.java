package kr.or.ddit.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 CREATE TABLE CRUD(
 	CRUD_NUM NUMBER,
 	CRUD_CONTENT VARCHAR2(900),
 	CRUD_NAME VARCHAR2(90),
 	CURD_FILE VARCHAR2(90),
 	CONSTRAINT PK_CRUD PRIMARY KEY(CRUD_NUM)
 );
 */
@Setter
@Getter
@ToString
public class CrudVO {
	private int crudNum;
	private String crudContent;
	private String crudName;
	private String crudFile;
}

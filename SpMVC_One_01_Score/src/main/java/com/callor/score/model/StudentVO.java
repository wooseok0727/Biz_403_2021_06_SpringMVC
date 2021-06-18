package com.callor.score.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentVO {
	
	private String st_num;			//	CHAR(8)		PK
	private String st_name;			//	VARCHAR(20)	NOT NULL	
	private String st_dept;			//	VARCHAR(20)	NOT NULL	
	private Integer st_grade = 0;	//	INT	NOT NULL	
	private String st_tel;			//	VARCHAR(15)	NOT NULL	
	private String st_addr;			//	VARCHAR(125)		

}

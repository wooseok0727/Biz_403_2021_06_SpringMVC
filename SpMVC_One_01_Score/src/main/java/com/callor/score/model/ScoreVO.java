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
public class ScoreVO {

	private String sc_seq;			//	CHAR(8)		PK
	private String sc_stnum;		//	CHAR(8)	NOT NULL	
	private String sc_subject;		//	VARCHAR(20)	NOT NULL	
	private Integer sc_score = 0;	//	INT	NOT NULL	

}

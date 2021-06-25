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
public class SubjectAndScoreDTO {

	private String ss_code; 
	private String ss_sbname;
	private String ss_prof;
	private String ss_stnum; 
	private int ss_score;
}

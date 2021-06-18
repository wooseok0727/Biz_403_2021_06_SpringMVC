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
public class ScoreDTO {
	
	private String sc_seq;
	private String sc_stnum;
	private String sc_subject;
	private Integer sc_score = 0;
	private Long sc_subcount = 0L;
	private Integer sc_sum = 0;
}

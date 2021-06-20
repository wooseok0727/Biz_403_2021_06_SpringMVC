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
	
	private String st_num;
	private String st_name;
	private Integer sc_kor = 0; 
	private Integer sc_eng = 0;
	private Integer sc_math = 0;
	private Integer sc_sci = 0;
	private Integer sc_scores = 0;
	private Float sc_avg = 0F;
}

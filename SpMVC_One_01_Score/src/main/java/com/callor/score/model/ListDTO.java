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
public class ListDTO {
	
	private String st_num;
	private String st_name;
	private String st_dept;
	private Integer st_grade = 0;
	private Long sc_subject = 0L;
	private Integer sc_scores = 0;
	private Float sc_avg = 0F;
	
}

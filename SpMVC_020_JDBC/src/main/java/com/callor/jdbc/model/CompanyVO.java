package com.callor.jdbc.model;

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
public class CompanyVO {

	private String cp_code;  	// CHAR(5)		PRIMARY KEY
	private String cp_title;	// VARCHAR(125) NOT NULL
	private String cp_ceo;		// VARCHAR(20)
	private String cp_tel;		// VARCHAR(20)
	private String cp_addr;		// VARCHAR(125)
	private String cp_genre;	// VARCHAR(30)
}

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
public class AuthorVO {

	private String au_code;		// CHAR(5)		PRIMARY KEY
	private String au_name;		// VARCHAR(50)	NOT NULL
	private String au_tel;		// VARCHAR(20)
	private String au_addr;		// VARCHAR(125)
	private String au_genre;	// VARCHAR(30)
}

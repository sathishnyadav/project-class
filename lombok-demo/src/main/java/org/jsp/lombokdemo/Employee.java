package org.jsp.lombokdemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {
	private int id;
	private String name;
	private String desg;
	private long phone;
	private String email;
	private String password;
}

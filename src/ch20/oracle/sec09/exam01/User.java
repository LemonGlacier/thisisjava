package ch20.oracle.sec09.exam01;

import lombok.Data;

@Data
public class User {         //DTO class
	private String userId;
	private String userName;
	private String userPassword;
	private int userAge;
	private String userEmail;         //Source-generate Getter and Setter
	
}
